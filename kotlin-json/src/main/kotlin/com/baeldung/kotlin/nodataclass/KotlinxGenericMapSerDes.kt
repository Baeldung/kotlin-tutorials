package com.baeldung.kotlin.nodataclass

import kotlinx.serialization.json.Json

/**
 * Note: Moshi does not have a JsonElement generic class
 */
object KotlinxGenericMapSerDes : JsonSerDes<Map<String, Any?>> {

    override fun toJson(input: Map<String, Any?>): String = Json.encodeToString(KotlinxGenericMapSerializer, input)

    override fun fromJson(input: String): Map<String, Any?> = Json.decodeFromString(KotlinxGenericMapSerializer, input)
}