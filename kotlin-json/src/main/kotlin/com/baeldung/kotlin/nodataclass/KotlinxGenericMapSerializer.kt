package com.baeldung.kotlin.nodataclass

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import kotlinx.serialization.serializer

object KotlinxGenericMapSerializer : KSerializer<Map<String, Any?>> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("GenericMap")

    override fun serialize(encoder: Encoder, value: Map<String, Any?>) {
        val jsonObject = JsonObject(value.mapValues { it.value.toJsonElement() })
        val jsonObjectSerializer = encoder.serializersModule.serializer<JsonObject>()
        jsonObjectSerializer.serialize(encoder, jsonObject)
    }

    override fun deserialize(decoder: Decoder): Map<String, Any?> {
        val jsonDecoder = decoder as? JsonDecoder ?: throw SerializationException("Can only deserialize Json content to generic Map")
        val root = jsonDecoder.decodeJsonElement()
        return if (root is JsonObject) root.toMap() else throw SerializationException("Cannot deserialize Json content to generic Map")
    }

    private fun Any?.toJsonElement(): JsonElement = when (this) {
        null -> JsonNull
        is String -> JsonPrimitive(this)
        is Number -> JsonPrimitive(this)
        is Boolean -> JsonPrimitive(this)
        is Map<*, *> -> toJsonObject()
        is Iterable<*> -> toJsonArray()
        else -> throw SerializationException("Cannot serialize value type $this")
    }

    private fun Map<*, *>.toJsonObject(): JsonObject = JsonObject(this.entries.associate { it.key.toString() to it.value.toJsonElement() })

    private fun Iterable<*>.toJsonArray(): JsonArray = JsonArray(this.map { it.toJsonElement() })

    private fun JsonElement.toAnyNullableValue(): Any? = when (this) {
        is JsonPrimitive -> toScalarOrNull()
        is JsonObject -> toMap()
        is JsonArray -> toList()
    }

    private fun JsonObject.toMap(): Map<String, Any?> = entries.associate {
        when (val jsonElement = it.value) {
            is JsonPrimitive -> it.key to jsonElement.toScalarOrNull()
            is JsonObject -> it.key to jsonElement.toMap()
            is JsonArray -> it.key to jsonElement.toAnyNullableValueList()
        }
    }

    private fun JsonPrimitive.toScalarOrNull(): Any? = when {
        this is JsonNull -> null
        this.isString -> this.content
        else -> listOfNotNull(booleanOrNull, longOrNull, doubleOrNull).firstOrNull()
    }

    private fun JsonArray.toAnyNullableValueList(): List<Any?> = this.map {
        it.toAnyNullableValue()
    }
}