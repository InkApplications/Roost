package roost.threeten

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.Instant
import org.threeten.bp.format.DateTimeFormatter

object InstantAdapter {
    @ToJson fun toJson(instant: Instant) = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(instant)
    @FromJson fun fromJson(instant: String) = DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(instant).let(Instant::from)
}
