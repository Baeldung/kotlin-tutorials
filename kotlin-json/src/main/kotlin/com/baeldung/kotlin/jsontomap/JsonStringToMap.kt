package com.baeldung.kotlin.jsontomap

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.json.*
import org.json.JSONObject

fun jsonStringToMapWithKotlinx(json: String): Map<String, JsonElement> {
    val data = Json.parseToJsonElement(json)
    require(data is JsonObject) { "Only Json Objects can be converted to a Map!" }
    return data
}

fun jsonStringToMapWithOrgJson(json: String): Map<String, Any> {
    val jsonObj = JSONObject(json)
    return jsonObj.toMap()
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