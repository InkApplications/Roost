package roost.models

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import com.squareup.moshi.ToJson

data class Structure(
    val id: String,
    val thermostats: Set<String>?,
    val smokeCoAlarms: Set<String>?,
    val cameras: Set<String>?,
    val devices: Set<DeviceIdentifier>?,
    val away: AwayState?,
    val name: String?,
    val countryCode: String?,
    val postalCode: String?,
    val timeZone: ZoneId?,
    val peakPeriod: ClosedRange<Instant>?,
    val eta: Eta?,
    val etaBegin: Instant?,
    val coAlarmState: AlarmState?,
    val smokeAlarmState: AlarmState?,
    val rhrEnrollment: Boolean?,
    val wwnSecurityState: WwnSecurityState?,
    val wheres: Set<Where>?
)

@JsonClass(generateAdapter = true)
internal data class StructureJson(
    @Json(name = "structure_id") val id: String,
    val thermostats: Set<String>?,
    @Json(name = "smoke_co_alarms") val smokeCoAlarms: Set<String>?,
    val cameras: Set<String>?,
    val devices: Set<DeviceIdentifier>?,
    val away: AwayState?,
    val name: String?,
    @Json(name = "country_code") val countryCode: String?,
    @Json(name = "postal_code") val postalCode: String?,
    @Json(name = "peak_period_start_time") val peakPeriodStart: Instant?,
    @Json(name = "peak_period_end_time") val peakPeriodEnd: Instant?,
    @Json(name = "time_zone") val timeZone: ZoneId?,
    val eta: Eta?,
    @Json(name = "eta_begin") val etaBegin: Instant?,
    @Json(name = "co_alarm_state") val coAlarmState: AlarmState?,
    @Json(name = "smoke_alarm_state") val smokeAlarmState: AlarmState?,
    @Json(name = "rhr_enrollment") val rhrEnrollment: Boolean?,
    @Json(name = "wwn_security_state") val wwnSecurityState: WwnSecurityState?,
    @field:KeyedSet val wheres: Set<Where>?
)

internal object StructureDeserializer {
    @FromJson fun fromJson(jsonModel: StructureJson) = Structure(
        id = jsonModel.id,
        thermostats = jsonModel.thermostats,
        smokeCoAlarms = jsonModel.smokeCoAlarms,
        cameras = jsonModel.cameras,
        devices = jsonModel.devices,
        away = jsonModel.away,
        name = jsonModel.name,
        countryCode = jsonModel.countryCode,
        postalCode = jsonModel.postalCode,
        timeZone = jsonModel.timeZone,
        peakPeriod =
        if (jsonModel.peakPeriodStart == null || jsonModel.peakPeriodEnd == null) null
        else jsonModel.peakPeriodStart..jsonModel.peakPeriodEnd,
        eta = jsonModel.eta,
        etaBegin = jsonModel.etaBegin,
        coAlarmState = jsonModel.coAlarmState,
        smokeAlarmState = jsonModel.smokeAlarmState,
        rhrEnrollment = jsonModel.rhrEnrollment,
        wwnSecurityState = jsonModel.wwnSecurityState,
        wheres = jsonModel.wheres
    )

    @ToJson fun toJson(structure: Structure) = StructureJson(
        id = structure.id,
        thermostats = structure.thermostats,
        smokeCoAlarms = structure.smokeCoAlarms,
        cameras = structure.cameras,
        devices = structure.devices,
        away = structure.away,
        name = structure.name,
        countryCode = structure.countryCode,
        postalCode = structure.postalCode,
        timeZone = structure.timeZone,
        peakPeriodStart = structure.peakPeriod?.start,
        peakPeriodEnd = structure.peakPeriod?.endInclusive,
        eta = structure.eta,
        etaBegin = structure.etaBegin,
        coAlarmState = structure.coAlarmState,
        smokeAlarmState = structure.smokeAlarmState,
        rhrEnrollment = structure.rhrEnrollment,
        wwnSecurityState = structure.wwnSecurityState,
        wheres = structure.wheres
    )
}
