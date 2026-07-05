package com.ebdz.data.remote.network

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue

class ApiClientTest {

    @Test
    fun `successful response is decoded`() = runTest {
        val engine = MockEngine { request ->
            respond(
                content = """[{"id":"1","title":"First"}]""",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val client = ApiClient(baseUrl = "https://example.test", engine = engine)

        val result = client.get<List<TemplateItemDto>>("/template-items")

        assertEquals(listOf(TemplateItemDto("1", "First")), result.getOrThrow())
    }

    @Test
    fun `a 404 response is not retried and maps to NotFound`() = runTest {
        var callCount = 0
        val engine = MockEngine {
            callCount++
            respond(content = "", status = HttpStatusCode.NotFound)
        }
        val client = ApiClient(baseUrl = "https://example.test", engine = engine)

        val result = client.get<List<TemplateItemDto>>("/template-items")

        assertTrue(result.isFailure)
        assertIs<ApiException.NotFound>(result.exceptionOrNull())
        assertEquals(1, callCount)
    }

    @Test
    fun `a transient server error is retried until it succeeds`() = runTest {
        var callCount = 0
        val engine = MockEngine {
            callCount++
            if (callCount < 2) {
                respond(content = "", status = HttpStatusCode.InternalServerError)
            } else {
                respond(
                    content = """[]""",
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
        }
        val client = ApiClient(
            baseUrl = "https://example.test",
            engine = engine,
            retryDelay = { /* skip real delay in tests */ }
        )

        val result = client.get<List<TemplateItemDto>>("/template-items")

        assertTrue(result.isSuccess)
        assertEquals(2, callCount)
    }
}
