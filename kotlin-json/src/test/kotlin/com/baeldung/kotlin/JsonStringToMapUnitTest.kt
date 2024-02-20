package com.baeldung.kotlin

import com.baeldung.kotlin.jsontomap.*
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonPrimitive
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.test.assertNotNull

class JsonStringToMapUnitTest {
    @Test
    fun testJsonStringToMapWithKotlinx() {
        val json = """{"name":"John","age":30,"city":"New York"}"""

        val expectedMap = mapOf("name" to "John", "age" to 30, "city" to "New York")
        val result = jsonStringToMapWithKotlinx(json)

        assertNotNull(result)
        assertEquals(expectedMap["name"], result["name"]?.jsonPrimitive?.content)
        assertEquals(expectedMap["age"], result["age"]?.jsonPrimitive?.int)
        assertEquals(expectedMap["city"], result["city"]?.jsonPrimitive?.content)
    }

    @Test
    fun testJsonStringToMapWithOrgJson() {
        val json = """{"name":"John","age":30,"city":"New York"}"""
        val expectedMap = mapOf("name" to "John", "age" to 30, "city" to "New York")
        val result = jsonStringToMapWithOrgJson(json)
        assertEquals(expectedMap, result)
    }

    @Test
    fun testJsonStringToMapWithGson() {
        val json = """{"name":"Jane","age":25,"city":"San Francisco"}"""
        val expectedMap = mapOf("name" to "Jane", "age" to 25.0, "city" to "San Francisco")
        val result = jsonStringToMapWithGson(json)

        assertEquals(expectedMap["name"], result["name"])
        assertEquals(expectedMap["age"], result["age"])
        assertEquals(expectedMap["city"], result["city"])
    }

    @Test
    fun testJsonStringToMapWithJackson() {
        val json = """{"name":"John","age":30,"city":"New York"}"""
        val expectedMap = mapOf("name" to "John", "age" to 30, "city" to "New York")
        val result = jsonStringToMapWithJackson(json)
        assertEquals(expectedMap, result)
    }

    @Test
    fun testEmptyJsonStringToMapWithKotlinx() {
        val json = "{}"
        val expectedMap = emptyMap<String, Any>()
        val result = jsonStringToMapWithKotlinx(json)
        assertEquals(expectedMap, result)
    }

    @Test
    fun testEmptyJsonStringToMapWithOrgJson() {
        val json = "{}"
        val expectedMap = emptyMap<String, Any>()
        val result = jsonStringToMapWithOrgJson(json)
        assertEquals(expectedMap, result)
    }

    @Test
    fun testEmptyJsonStringToMapWithGson() {
        val json = "{}"
        val expectedMap = emptyMap<String, Any>()
        val result = jsonStringToMapWithGson(json)
        assertEquals(expectedMap, result)
    }

    @Test
    fun testEmptyJsonStringToMapWithJackson() {
        val json = "{}"
        val expectedMap = emptyMap<String, Any>()
        val result = jsonStringToMapWithJackson(json)
        assertEquals(expectedMap, result)
    }

    @Test
    fun testInvalidJsonStringToMapWithKotlinx() {
        val json = """{"name":"Alice","age":30,"city"}"""
        val exception = assertThrows<IllegalArgumentException> {
            jsonStringToMapWithKotlinx(json)
        }
        exception.message?.let { assert(it.startsWith("Unexpected JSON token")) }
    }

    @Test
    fun testInvalidJsonStringToMapWithOrgJson() {
        val json = """{"name":"Alice","age":30,"city"}"""
        val exception = assertThrows<org.json.JSONException> {
            jsonStringToMapWithOrgJson(json)
        }
        assertEquals("Expected a ':' after a key at 32 [character 33 line 1]",
                exception.message)
    }

    @Test
    fun testInvalidJsonStringToMapWithGson() {
        val json = """{"name":,"age":null,"city":"Chicago"}"""
        val exception = assertThrows<com.google.gson.JsonSyntaxException> {
            jsonStringToMapWithGson(json)
        }
        assertEquals("com.google.gson.stream.MalformedJsonException: Unexpected value at line 1 column 10 path \$.", exception.cause?.toString())
    }

    @Test
    fun testInvalidJsonStringToMapWithJackson() {
        val json = """{"name":"Alice","age":30,"city"}"""
        assertThrows<com.fasterxml.jackson.core.JsonParseException> {
            jsonStringToMapWithJackson(json)
        }
    }
}