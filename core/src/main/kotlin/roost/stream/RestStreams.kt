package roost.stream

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.sse.EventSources
import roost.models.Structure
import roost.models.nestResponseAdapter

private const val NEST_API_BASEURL = "https://developer-api.nest.com"
val NEST_API_MATCHER = Regex("^https://(?:[a-zA-Z0-9_\\-]+\\.)*nest\\.com(?::\\d+)?(?:\$|/).*", RegexOption.IGNORE_CASE)

internal class RestStreams(
    httpClient: OkHttpClient,
    moshi: Moshi
): Streams {
    private val responseAdapter = moshi.nestResponseAdapter(Structure::class)
    private val factory = EventSources.createFactory(httpClient)

    override fun structure(token: String, structure: String, event: (Structure) -> Unit) {
        val structureRequest = Request.Builder()
            .url("$NEST_API_BASEURL/structures/$structure")
            .addHeader("Accept", "text/event-stream")
            .addHeader("Authorization", "Bearer $token")
            .addHeader("Cache-Control", "no-cache")
            .build()
        val listener =  NestDataListener(event, { structure(token, structure, event) }, responseAdapter)

        factory.newEventSource(structureRequest, listener)
    }
}
