package roost.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Metadata(
    @Json(name = "access_token") val accessToken: String,
    @Json(name = "client_version") val clientVersion: Int
)
