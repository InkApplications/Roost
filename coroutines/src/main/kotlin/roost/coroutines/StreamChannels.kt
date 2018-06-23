package roost.coroutines

import kotlinx.coroutines.experimental.channels.RendezvousChannel
import kotlinx.coroutines.experimental.runBlocking
import roost.models.GlobalIndex
import roost.models.Structure
import roost.stream.Streams

suspend fun Streams.openGlobalChannel(token: String): RendezvousChannel<GlobalIndex> {
    val channel = RendezvousChannel<GlobalIndex>()
    global(token) { runBlocking { channel.send(it) } }

    return channel
}

suspend fun Streams.openStructureChannel(token: String, structureId: String): RendezvousChannel<Structure> {
    val channel = RendezvousChannel<Structure>()
    structure(token, structureId) { runBlocking { channel.send(it) } }

    return channel
}
