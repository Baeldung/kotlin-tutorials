package com.baeldung.kotlin

import com.baeldung.kotlin.jsontomap.jsonStringToMap
import com.baeldung.kotlin.jsontomap.jsonStringToMapWithGson
import com.baeldung.kotlin.jsontomap.jsonStringToMapWithJackson
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class JsonStringToMapTest {

    @Test
    fun testJsonStringToMap() {
        val json = """{"name":"John","age":30,"city":"New York"}"""
        val expectedMap = mapOf("name" to "John", "age" to 30, "city" to "New York")
        val result = jsonStringToMap(json)
        assertEquals(expectedMap, result)
    }

    @Test
    fun testJsonStringToMapWithGson() {
        val json = """{"name":"Jane","age":25,"city":"San Francisco"}"""
        val expectedMap = mapOf("name" to "Jane", "age" to 25, "city" to "San Francisco")
        val result = jsonStringToMapWithGson(json)
        assertEquals(expectedMap, result)
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
        val json = """{"name":"Bob","age":null,"city":"Chicago"}""" // Null value for "age"
        val exception = assertThrows<com.google.gson.JsonSyntaxException> {
            jsonStringToMapWithGson(json)
        }
        assertEquals("java.lang.NullPointerException", exception.cause?.toString())
    }

    @Test
    fun testInvalidJsonStringToMapWithJackson() {
        val json = """{"name":"Alice","age":30,"city"}""" // Missing colon in "city" entry
        val exception = assertThrows<com.fasterxml.jackson.databind.JsonMappingException> {
            jsonStringToMapWithJackson(json)
        }
        assertEquals("Unexpected character ('}' (code 125)): was expecting a colon to separate field name and value\n at [Source: (String)\"{\"name\":\"Alice\",\"age\":30,\"city\"}\"; line: 1, column: 27]",
                exception.message)
    }
}