package roost.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.Instant

@JsonClass(generateAdapter = true)
data class SmokeCoAlarm(
    @Json(name="device_id") override val id: String,
    val locale: String?,
    @Json(name = "software_version") val softwareVersion: String,
    @Json(name = "structure_id") val structureId: String,
    val name: String,
    @Json(name = "name_long") val longName: String,
    @Json(name = "last_connection") val lastConnection: Instant?,
    @Json(name = "is_online") val isOnline: Boolean,
    @Json(name = "where_id") val whereId: String,
    @Json(name = "where_name") val whereName: String,
    @Json(name = "battery_health") val batteryHealth: BatteryHealthState,
    @Json(name = "co_alarm_state") val coAlarmState: AlarmState,
    @Json(name = "smoke_alarm_state") val smokeAlarmState: AlarmState,
    @Json(name = "is_manual_test_active") val manualTestActive: Boolean,
    @Json(name = "last_manual_test_time") val lastManualTestTime: Instant?,
    @Json(name = "ui_color_state") val uiColorState: String?
): Identifiable
