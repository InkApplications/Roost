package roost.models

import com.squareup.moshi.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

@Retention(AnnotationRetention.RUNTIME) @JsonQualifier internal annotation class KeyedSet

internal interface Identifiable {
    val id: String
}

internal class KeyedSetAdapter<T: Identifiable>(private val adapter: JsonAdapter<Map<String, T>>): JsonAdapter<Set<T>>() {
    override fun toJson(writer: JsonWriter, values: Set<T>?) {
        return adapter.toJson(writer, values?.map { it.id to it }?.toMap())
    }

    override fun fromJson(reader: JsonReader): Set<T>? {
        return adapter.fromJson(reader)?.values?.toSet()
    }
}

internal object KeyedSetAdapterFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
        val rawType = Types.getRawType(type)
        if (rawType == Set::class.java && type is ParameterizedType) {
            val delegateAnnotations = Types.nextAnnotations(annotations, KeyedSet::class.java) ?: return null
            val subType = type.actualTypeArguments.first()
            val mapType = Types.newParameterizedType(Map::class.java, String::class.java, subType)
            val adapter: JsonAdapter<Map<String, Identifiable>> = moshi.adapter(mapType, delegateAnnotations)
            return KeyedSetAdapter(adapter)

        }
        return null
    }
}
