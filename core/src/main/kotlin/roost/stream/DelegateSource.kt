package roost.stream

import okhttp3.sse.EventSource
import java.util.concurrent.atomic.AtomicReference

internal class DelegateSource: EventSource {
    private var source: AtomicReference<EventSource?> = AtomicReference()

    fun set(source: EventSource) = this.source.set(source)

    override fun cancel() = source.get()!!.cancel()
    override fun request() = source.get()!!.request()
}
