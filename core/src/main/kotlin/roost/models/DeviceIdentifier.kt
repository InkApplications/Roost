package roost.models

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson

@JsonClass(generateAdapter = true)
data class DeviceIdentifier(
    val company: String,
    val type: String,
    val id: String
)

internal object DeviceIdentifierAdapter {
    @FromJson fun fromJson(json: Map<String, Map<String, List<String>>>): Set<DeviceIdentifier> = json
        .flatMap { (company, products) ->
            products.flatMap { (productType, devices) ->
                devices.map { device ->
                    DeviceIdentifier(company, productType, device)
                }
            }
        }
        .toSet()

    @ToJson fun toJson(devices: Set<DeviceIdentifier>): Map<String, Map<String, List<String>>> = devices
        .groupBy { it.company }
        .mapValues { (_, devices) ->
            devices.groupBy { it.type }
                .mapValues { (_, value) ->
                    value.map { it.id }
                }
        }
}
