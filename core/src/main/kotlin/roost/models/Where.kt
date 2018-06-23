package roost.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Where(
    @Json(name = "where_id") override val id: String,
    val name: String
): Identifiable
