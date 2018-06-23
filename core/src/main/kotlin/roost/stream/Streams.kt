package roost.stream

import okhttp3.sse.EventSource
import roost.models.GlobalIndex
import roost.models.Structure

/**
 * Events that can be streamed from the API
 */
interface Streams {
    /**
     * Listen to events on a specific Nest Structure.
     */
    fun structure(token: String, structure: String, event: (Structure) -> Unit): EventSource

    /**
     * Listen to global changes on all structures and devices.
     */
    fun global(token: String, event: (GlobalIndex) -> Unit): EventSource
}
