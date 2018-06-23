package roost.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GlobalIndex(
    val metadata: Metadata,
    val devices: DeviceGroups,
    @field:KeyedSet val structures: Set<Structure>
)
