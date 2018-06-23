package roost.stream

import com.squareup.moshi.JsonAdapter
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import roost.models.NestResponse

internal class NestDataListener<T>(
    private val onEvent: (T) -> Unit,
    private val onClose: () -> Unit,
    private val adapter: JsonAdapter<NestResponse<T>>
): EventSourceListener() {
    override fun onEvent(eventSource: EventSource, id: String?, type: String?, data: String) {
        when (type) {
            "put" -> adapter.fromJson(data)?.data?.run(onEvent)
        }
    }

    override fun onClosed(eventSource: EventSource) { onClose() }
}
