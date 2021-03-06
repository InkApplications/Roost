package roost.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.Instant

@JsonClass(generateAdapter = true)
data class Camera(
    @Json(name="device_id") override val id: String,
    @Json(name = "software_version") val softwareVersion: String,
    @Json(name = "structure_id") val structureId: String,
    val name: String,
    @Json(name = "name_long") val longName: String,
    @Json(name = "is_online") val isOnline: Boolean,
    @Json(name = "where_id") val whereId: String,
    @Json(name = "where_name") val whereName: String,
    @Json(name = "last_connection") val lastConnection: Instant?,
    val locale: String?,
    @Json(name = "is_streaming") val isStreaming: Boolean?,
    @Json(name = "is_audio_input_enabled") val isAudioInputEnabled: Boolean?,
    @Json(name = "last_is_online_change") val lastIsOnlineChange: Instant?,
    @Json(name = "is_video_history_enabled") val isVideoHistoryEnabled: Boolean?,
    @Json(name = "web_url") val webUrl: String?,
    @Json(name = "app_url") val appUrl: String?,
    @Json(name = "is_public_share_enabled") val isPublicShareEnabled: Boolean?,
    @Json(name = "activity_zones") val activityZones: List<Where>?,
    @Json(name = "public_share_url") val publicShareUrl: String?,
    @Json(name = "snapshot_url") val snapshotUrl: String?,
    @Json(name = "last_event") val lastEvent: Event?
): Identifiable {
    @JsonClass(generateAdapter = true)
    data class Event(
        @Json(name = "has_sound") val hasSound: Boolean,
        @Json(name = "has_motion") val hasMotion: Boolean,
        @Json(name = "has_person") val hasPerson: Boolean,
        @Json(name = "start_time") val startTime: Instant,
        @Json(name = "end_time") val endTime: Instant?,
        @Json(name = "urls_expire_time") val urlsExpireTime: Instant,
        @Json(name = "web_url") val webUrl: String,
        @Json(name = "app_url") val appUrl: String,
        @Json(name = "imageUrl") val imageUrl: String?,
        @Json(name = "animated_image_url") val animatedImageUrl: String?,
        @Json(name = "activity_zone_ids") val activityZoneIds: List<String>?
    )
}
