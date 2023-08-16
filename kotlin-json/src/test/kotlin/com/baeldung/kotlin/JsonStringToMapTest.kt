package com.baeldung.kotlin

import com.baeldung.kotlin.jsontomap.jsonStringToMap
import com.baeldung.kotlin.jsontomap.jsonStringToMapKotlinx
import com.baeldung.kotlin.jsontomap.jsonStringToMapWithGson
import com.baeldung.kotlin.jsontomap.jsonStringToMapWithJackson
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertNotNull

class JsonStringToMapTest {

    @Test
    fun testJsonStringToMap() {
        val json = """{"name":"John","age":30,"city":"New York"}"""
        val expectedMap = mapOf("name" to "John", "age" to 30, "city" to "New York")
        val result = jsonStringToMap(json)
        assertEquals(expectedMap, result)
    }

    @Test
    fun testJsonStringToMapKotlinx() {
        val json = """{"name":"John","age":30,"city":"New York"}"""
        val expectedMap = mapOf("name" to "John", "age" to 30L, "city" to "New York")
        val result = jsonStringToMapKotlinx(json)

        assertNotNull(result)
        assertEquals(expectedMap["name"], result["name"])
        assertEquals(expectedMap["age"], result["age"])
        assertEquals(expectedMap["city"], result["city"])
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
    fun testEmptyJsonStringToMap() {
        val json = "{}"
        val expectedMap = emptyMap<String, Any>()
        val result = jsonStringToMap(json)
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
    fun testInvalidJsonStringToMap() {
        val json = """{"name":"Alice","age":30,"city"}""" // Missing colon in "city" entry
        val exception = assertThrows<org.json.JSONException> {
            jsonStringToMap(json)
        }
        assertEquals("Expected a ':' after a key at 32 [character 33 line 1]",
                exception.message)
    }

    @Test
    fun testInvalidJsonStringToMapWithGson() {
        val json = """{"name":,"age":null,"city":"Chicago"}""" // Null value for "age"
        val exception = assertThrows<com.google.gson.JsonSyntaxException> {
            jsonStringToMapWithGson(json)
        }
        assertEquals("com.google.gson.stream.MalformedJsonException: Unexpected value at line 1 column 10 path \$.", exception.cause?.toString())
    }

    @Test
    fun testInvalidJsonStringToMapWithJackson() {
        val json = """{"name":"Alice","age":30,"city"}""" // Missing colon in "city" entry
        val exception = assertThrows<com.fasterxml.jackson.core.JsonParseException> {
            jsonStringToMapWithJackson(json)
        }
    }
}