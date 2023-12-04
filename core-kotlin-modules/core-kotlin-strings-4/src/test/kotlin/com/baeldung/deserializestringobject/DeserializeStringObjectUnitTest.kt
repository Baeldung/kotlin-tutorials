package com.baeldung.deserializestringobject

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DeserializeStringObjectUnitTest {
    @Test
    fun `deserialize Json object to string using Gson`(){
        val gson = Gson()
        val jsonString = "{\"name\":\"John\", \"age\":30, \"city\":\"New York\"}"
        val jsonObject = gson.fromJson(jsonString, JsonObject::class.java)
        val name = jsonObject.get("name").asString
        val city = jsonObject.get("city").asString
        val age = jsonObject.get("age").asString


        assertEquals("John", name)
        assertEquals("New York", city)
        assertEquals("30", age)
        assertEquals("{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}", jsonObject.toString())
    }

    @Test
    fun `deserialize Json object to string using Kotlinx serialization`(){
        val json = Json { }
        val jsonString = "{\"name\":\"John\", \"age\":30, \"city\":\"New York\"}"
        val jsonObject = json.parseToJsonElement(jsonString).jsonObject
        val name = jsonObject["name"]?.jsonPrimitive?.content
        val city = jsonObject["city"]?.jsonPrimitive?.content
        val age = jsonObject["age"]?.jsonPrimitive?.content


        assertEquals("John", name)
        assertEquals("New York", city)
        assertEquals("30", age)
        assertEquals("{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}", jsonObject.toString())
    }

    @Test
    fun `deserialize Json object to string using Jackson library`(){
        val objectMapper = jacksonObjectMapper()
        val jsonString = "{\"name\":\"John\", \"age\":30, \"city\":\"New York\"}"
        val jsonObject = objectMapper.readValue(jsonString, JsonNode::class.java)
        val name = jsonObject.get("name").asText()
        val city = jsonObject.get("city").asText()
        val age = jsonObject.get("age").asText()

        assertEquals("John", name)
        assertEquals("New York", city)
        assertEquals("30", age)
        assertEquals("{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}", jsonObject.toString())
    }
}