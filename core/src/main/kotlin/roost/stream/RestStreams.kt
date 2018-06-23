package roost.stream

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.sse.EventSourceListener
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.sse.EventSource
import okhttp3.sse.EventSources
import roost.models.GlobalIndex
import roost.models.NestResponse
import roost.models.Structure
import roost.models.nestResponseAdapter

private const val NEST_API_BASEURL = "https://developer-api.nest.com"
val NEST_API_MATCHER = Regex("^https://(?:[a-zA-Z0-9_\\-]+\\.)*nest\\.com(?::\\d+)?(?:\$|/).*", RegexOption.IGNORE_CASE)

internal class RestStreams(
    private val httpClient: OkHttpClient,
    moshi: Moshi
): Streams {
    private val structureAdapter = moshi.nestResponseAdapter(Structure::class)
    private val globalAdapter = moshi.nestResponseAdapter(GlobalIndex::class)
    private val factory = EventSources.createFactory(httpClient)

    override fun global(token: String, event: (GlobalIndex) -> Unit): EventSource {
        val structureRequest = Request.Builder()
            .url("$NEST_API_BASEURL")
            .addHeader("Accept", "text/event-stream")
            .addHeader("Authorization", "Bearer $token")
            .addHeader("Cache-Control", "no-cache")
            .build()

        val delegate = DelegateSource()

        factory.newEventSource(structureRequest, createListener(globalAdapter, event, delegate))
            .also(delegate::set)

        return delegate
    }

    override fun structure(token: String, structure: String, event: (Structure) -> Unit): EventSource {
        val structureRequest = Request.Builder()
            .url("$NEST_API_BASEURL/structures/$structure")
            .addHeader("Accept", "text/event-stream")
            .addHeader("Authorization", "Bearer $token")
            .addHeader("Cache-Control", "no-cache")
            .build()

        val delegate = DelegateSource()

        factory.newEventSource(structureRequest, createListener(structureAdapter, event, delegate))
            .also(delegate::set)

        return delegate
    }

    private fun <T> createListener(adapter: JsonAdapter<NestResponse<T>>, event: (T) -> Unit, delegateSource: DelegateSource): EventSourceListener = NestDataListener(adapter, event, { eventSource, _, _ ->
        Thread.sleep(httpClient.connectTimeoutMillis().toLong())
        factory.newEventSource(eventSource.request(), createListener(adapter, event, delegateSource))
            .also(delegateSource::set)
    })
}
