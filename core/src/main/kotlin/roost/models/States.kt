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

enum class TrainingState {
    @Json(name = "training") TRAINING,
    @Json(name = "ready") READY
}

enum class HvacState {
    @Json(name = "heating") HEATING,
    @Json(name = "cooling") COOLING,
    @Json(name = "off") OFF
}

enum class HvacMode {
    @Json(name = "heat") HEAT,
    @Json(name = "cool") COOL,
    @Json(name = "heat-cool") HEAT_COOL,
    @Json(name = "eco") ECO,
    @Json(name = "off") OFF,
    @Json(name = "") NONE
}

enum class TemperatureScale {
    @Json(name = "F") CELSIUS,
    @Json(name = "C") FAHRENHEIT
}

enum class UiColor {
    @Json(name = "gray") GRAY,
    @Json(name = "green") GREEN,
    @Json(name = "yellow") YELLOW,
    @Json(name = "red") RED,
}

enum class BatteryHealthState {
    @Json(name = "ok") OK,
    @Json(name = "replace") REPLACE,
}
