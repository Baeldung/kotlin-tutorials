package com.baeldung.kotlin.jsontomap

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.ToNumberPolicy
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import org.json.JSONObject

@Serializable
data class MyData(val name: String, val age: Int, val city: String)

fun jsonStringToMap(json: String): Map<String, Any> {
    val jsonObj = JSONObject(json)
    return jsonObj.toMap()
}

fun jsonStringToMapKotlinx(json: String): Map<out Any?, Any?>? {

    val myData: MyData = Json.decodeFromString(json)

    val gsonMapper = GsonBuilder().serializeNulls().setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create()
    return gsonMapper.fromJson(gsonMapper.toJson(myData), Map::class.java)
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