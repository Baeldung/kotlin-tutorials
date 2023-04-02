package com.baeldung.kotlin.nodataclass

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

/**
 * Note: Moshi does not have a JsonElement generic class
 */
object KotlinxJsonObjectSerDes : JsonSerDes<JsonObject> {

    override fun toJson(input: JsonObject): String = Json.encodeToString(input)

    override fun fromJson(input: String): JsonObject = Json.decodeFromString(input)

}