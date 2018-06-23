package roost.stream

import com.squareup.moshi.JsonAdapter
import okhttp3.Response
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import roost.models.NestResponse

internal class NestDataListener<T>(
    private val adapter: JsonAdapter<NestResponse<T>>,
    private val onEvent: (T) -> Unit,
    private val onError: (EventSource, Throwable?, Response?) -> Unit = { _,_,_ -> },
    private val onClose: (EventSource) -> Unit = {}
): EventSourceListener() {
    override fun onEvent(eventSource: EventSource, id: String?, type: String?, data: String) {
        when (type) {
            "put" -> adapter.fromJson(data)?.data?.run(onEvent)
        }
    }

    override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
        println("failure: $t $response")
        onError(eventSource, t, response)
    }
    override fun onClosed(eventSource: EventSource) {
        println("closed")
        onClose(eventSource)
    }
}
