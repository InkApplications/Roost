package roost.models

import com.squareup.moshi.Json

data class Where(
    @Json(name = "where_id") override val id: String,
    val name: String
): Identifiable
