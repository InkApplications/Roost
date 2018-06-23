package roost.threeten

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.ZoneId

internal object ZoneIdAdapter {
    @FromJson fun fromJson(zone: String) = ZoneId.of(zone)
    @ToJson fun fromJson(zone: ZoneId) = zone.id
}
