package roost.models

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlin.reflect.KClass

@JsonClass(generateAdapter = true)
internal data class NestResponse<T>(val path: String, val data: T)

internal fun <T: Any> Moshi.nestResponseAdapter(type: KClass<T>) =
    Types.newParameterizedType(NestResponse::class.java, type.java)
        .let { adapter<NestResponse<T>>(it) }
