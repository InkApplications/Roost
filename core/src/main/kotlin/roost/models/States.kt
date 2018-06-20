package roost.models

import com.squareup.moshi.Json

enum class AwayState {
    @Json(name = "home") HOME,
    @Json(name = "away") AWAY
}

enum class AlarmState {
    @Json(name = "ok") OK,
    @Json(name = "warning") WARNING,
    @Json(name = "emergency") EMERGENCY,
}

enum class WwnSecurityState {
    @Json(name = "ok") OK,
    @Json(name = "deter") DETER
}
