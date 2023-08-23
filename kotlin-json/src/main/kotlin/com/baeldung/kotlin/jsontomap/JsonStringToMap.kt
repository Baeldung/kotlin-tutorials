package com.baeldung.kotlin.jsontomap

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.json.*
import org.json.JSONObject

fun jsonStringToMap(json: String): Map<String, Any> {
    val jsonObj = JSONObject(json)
    return jsonObj.toMap()
}

fun jsonStringToMapKotlinx(json: String): Map<*, *> {

    val myData = Json.parseToJsonElement(json)
    return jsonElementToMap(myData) as Map<*, *>
}

fun jsonStringToMapWithGson(json: String): Map<String, Any> {
    val gson = Gson()
    val type = object : TypeToken<Map<String, Any>>() {}.type
    return gson.fromJson(json, type)
}

fun jsonStringToMapWithJackson(json: String): Map<String, Any> {
    val objectMapper = jacksonObjectMapper()
    return objectMapper.readValue<Map<String, Any>>(json)
}

private fun jsonElementToMap(jsonElement: JsonElement): Any {
    return when (jsonElement) {
        is JsonObject -> {
            jsonElement.keys.associateWith { key ->
                jsonElementToMap(jsonElement[key]!!)
            }
        }
        is JsonPrimitive -> {
            when {
                jsonElement.isString -> jsonElement.content
                jsonElement.booleanOrNull != null -> jsonElement.boolean
                jsonElement.intOrNull != null -> jsonElement.int
                jsonElement.floatOrNull != null -> jsonElement.float
                jsonElement.doubleOrNull != null -> jsonElement.double
                else -> error("Unsupported JsonPrimitive type")
            }
        }
        else -> error("Unsupported JsonElement type")
    }
}
