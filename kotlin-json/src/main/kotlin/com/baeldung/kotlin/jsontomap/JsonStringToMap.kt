package com.baeldung.kotlin.jsontomap

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

fun jsonStringToMap(json: String): Map<String, Any> {
    val jsonObj = JSONObject(json)
    return jsonObj.toMap()
}

fun jsonStringToMapWithGson(json: String): Map<String, Any> {
    val gson = Gson()
    val type = object : TypeToken<Map<String, Any>>() {}.type
    return gson.fromJson(json, type)
}

fun jsonStringToMapWithJackson(json: String): Map<String, Any> {
    val objectMapper = ObjectMapper()
    return objectMapper.readValue(json, Map::class.java) as Map<String, Any>
}