package com.ebdz.data.remote.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

/**
 * Thin wrapper over Ktor that returns typed [Result]s and turns HTTP/transport failures into
 * [ApiException]s. Transient faults (connection errors, timeouts, 5xx) are retried with
 * exponential backoff; 4xx responses and parse failures are terminal and surface right away.
 *
 * @param enableLogging installs Ktor's [Logging] plugin at [LogLevel.ALL]. Off by default so
 *   release builds stay quiet unless a caller opts in.
 * @param engine lets tests swap in a `MockEngine` for scripted responses; in production this is
 *   null and Ktor picks the platform engine (CIO on Android, Darwin on iOS - CIO has no TLS
 *   support on Kotlin/Native).
 * @param maxRetries retries after the first attempt, so the default 3 allows up to 4 tries.
 * @param totalRetryTimeoutMillis caps the total time spent sleeping between retries.
 * @param retryDelay delay used between attempts; injectable so tests can assert the backoff
 *   schedule without actually waiting.
 */
class ApiClient(
    internal val baseUrl: String,
    internal val enableLogging: Boolean = false,
    engine: HttpClientEngine? = null,
    internal val maxRetries: Int = DEFAULT_MAX_RETRIES,
    internal val totalRetryTimeoutMillis: Long = DEFAULT_TOTAL_RETRY_TIMEOUT_MILLIS,
    internal val retryDelay: suspend (Long) -> Unit = { delay(it) }
) {

    private val clientConfig: HttpClientConfig<*>.() -> Unit = {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = false
                    coerceInputValues = true
                }
            )
        }

        if (enableLogging) {
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println("HTTP: $message")
                    }
                }
            }
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 30_000
            connectTimeoutMillis = 10_000
            socketTimeoutMillis = 30_000
        }
    }

    internal val client: HttpClient =
        if (engine != null) HttpClient(engine, clientConfig) else HttpClient(clientConfig)

    /**
     * GET [path] (relative to [baseUrl]) and decode the body to [T]. GET is idempotent, so
     * transient failures are retried with backoff until [maxRetries] is hit or the
     * [totalRetryTimeoutMillis] budget runs out, at which point the last error is returned.
     */
    internal suspend inline fun <reified T> get(path: String): Result<T> {
        var attempt = 0
        var accumulatedBackoffMillis = 0L
        while (true) {
            val result: Result<T> = try {
                val response: HttpResponse = client.get("$baseUrl$path")
                if (response.status.isSuccess()) {
                    Result.success(response.body<T>())
                } else {
                    Result.failure(mapHttpError(response.status.value))
                }
            } catch (e: SerializationException) {
                Result.failure(ApiException.ParseError(e.message ?: "Failed to parse response"))
            } catch (e: Exception) {
                Result.failure(ApiException.NetworkError(e.message ?: "Network error"))
            }

            if (result.isSuccess) return result

            if (attempt >= maxRetries || !isRetryable(result.exceptionOrNull())) {
                return result
            }

            val backoffMillis = retryBackoffMillis(attempt + 1)
            if (accumulatedBackoffMillis + backoffMillis > totalRetryTimeoutMillis) {
                return result
            }

            retryDelay(backoffMillis)
            accumulatedBackoffMillis += backoffMillis
            attempt++
        }
    }

    /** True for transient faults worth retrying: 5xx and connection/timeout errors. */
    internal fun isRetryable(error: Throwable?): Boolean = when (error) {
        is ApiException.ServerError -> true
        is ApiException.NetworkError -> true
        is ApiException.Timeout -> true
        else -> false
    }

    /** Exponential backoff: 1s, 2s, 4s, ... for retry 1, 2, 3, ... */
    internal fun retryBackoffMillis(retryNumber: Int): Long =
        BASE_RETRY_DELAY_MILLIS shl (retryNumber - 1)

    internal fun mapHttpError(statusCode: Int): ApiException = when (statusCode) {
        400 -> ApiException.BadRequest()
        401 -> ApiException.Unauthorized()
        404 -> ApiException.NotFound()
        in 500..599 -> ApiException.ServerError(statusCode)
        else -> ApiException.NetworkError("HTTP $statusCode")
    }

    fun close() {
        client.close()
    }

    companion object {
        /** Retries after the initial attempt (4 total attempts by default). */
        const val DEFAULT_MAX_RETRIES: Int = 3

        /** First backoff step; doubles on each retry via [retryBackoffMillis]. */
        internal const val BASE_RETRY_DELAY_MILLIS: Long = 1_000L

        /** Cap on total backoff across retries so a caller can't hang indefinitely. */
        const val DEFAULT_TOTAL_RETRY_TIMEOUT_MILLIS: Long = 20_000L
    }
}
