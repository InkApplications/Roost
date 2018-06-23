package roost

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import roost.models.DeviceAdapter
import roost.models.EtaAdapter
import roost.models.KeyedSetAdapterFactory
import roost.models.StructureDeserializer
import roost.stream.NEST_API_MATCHER
import roost.stream.RestStreams
import roost.stream.RedirectInterceptor
import roost.stream.Streams
import roost.threeten.InstantAdapter
import roost.threeten.ZoneIdAdapter
import java.util.concurrent.TimeUnit

class Roost(
    httpClient: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .followRedirects(false)
        .addInterceptor(RedirectInterceptor(NEST_API_MATCHER))
        .build()
) {
        private val moshi = Moshi.Builder()
            .add(DeviceAdapter)
            .add(EtaAdapter)
            .add(KeyedSetAdapterFactory)
            .add(StructureDeserializer)
            .add(ZoneIdAdapter)
            .add(InstantAdapter)
            .build()

        val streams: Streams = RestStreams(httpClient, moshi)
}
