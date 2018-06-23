package roost.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeviceGroups(
    @field:KeyedSet val thermostats: Set<Thermostat>,
    @Json(name = "smoke_co_alarms") @field:KeyedSet val smokeCoAlarms: Set<SmokeCoAlarm>,
    @field:KeyedSet val cameras: Set<Camera>
)


