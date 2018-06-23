package roost.stream

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Follows redirects only for a list of trusted sources.
 *
 * Intercepts redirect responses and follows with an identical request
 * including sensitive information like auth headers.
 *
 * @param trustedHosts Regular expressions, any of which can be matched
 *        to be considered a trusted source. This is compared against the
 *        full URL of the redirect.
 */
class RedirectInterceptor(private val trustedHosts: Set<Regex>): Interceptor {
    constructor(trustedHost: Regex): this(setOf(trustedHost))

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        return when (response.code()) {
            307 -> {
                val location = response.header("Location")
                if (location == null || trustedHosts.none(location::matches)) return response
                chain.proceed(request.newBuilder().url(location.toString()).build())
            }
            else -> response
        }
    }
}
