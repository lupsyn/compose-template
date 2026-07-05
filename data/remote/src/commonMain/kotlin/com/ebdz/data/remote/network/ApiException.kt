package com.ebdz.data.remote.network

/**
 * Typed errors surfaced by [ApiClient]. 4xx errors and [ParseError] are terminal; the others
 * are retried by [ApiClient] with backoff before being surfaced.
 */
sealed class ApiException(message: String) : Exception(message) {
    class NetworkError(message: String) : ApiException(message)
    class Timeout(message: String) : ApiException(message)
    class ServerError(val statusCode: Int) : ApiException("Server error: HTTP $statusCode")
    class BadRequest : ApiException("Bad request")
    class Unauthorized : ApiException("Unauthorized")
    class NotFound : ApiException("Not found")
    class ParseError(message: String) : ApiException(message)
}
