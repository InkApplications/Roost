package roost.models

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.ToJson
import org.threeten.bp.Instant


data class Eta(val id: String, val estimatedArrival: ClosedRange<Instant>)

internal data class EtaJson(
    @Json(name = "trip_id") val id: String,
    @Json(name = "estimated_arrival_window_begin") val windowStart: Instant,
    @Json(name = "estimated_arrival_window_end") val windowEnd: Instant
)

internal object EtaAdapter {
    @FromJson fun fromJson(jsonModel: EtaJson) = Eta(
        id = jsonModel.id,
        estimatedArrival = jsonModel.windowStart..jsonModel.windowEnd
    )

    @ToJson fun toJson(jsonModel: Eta) = EtaJson(
        id = jsonModel.id,
        windowStart = jsonModel.estimatedArrival.start,
        windowEnd = jsonModel.estimatedArrival.endInclusive
    )
}
