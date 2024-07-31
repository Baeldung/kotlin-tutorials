package com.baeldung.serialization

import com.baeldung.serialization.gson.StatusDeserializer
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.gson.GsonBuilder
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UnknownEnumSerializationUnitTest {

    val gson = GsonBuilder()
        .registerTypeAdapter(Status::class.java, StatusDeserializer())
        .create()

    val mapper = jacksonObjectMapper().apply {
        configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true)
    }

    @Test
    fun `test known status value deserialization gson`() {
        val json = """{"status": "SUCCESS"}"""
        val response = gson.fromJson(json, ApiResponse::class.java)
        assertEquals(Status.SUCCESS, response.status)
    }

    @Test
    fun `test unknown status value deserialization gson`() {
        val unknownJson = """{"status": "unknown_value"}"""
        val response = gson.fromJson(unknownJson, ApiResponse::class.java)
        assertEquals(Status.UNKNOWN, response.status)
    }

    @Test
    fun `test known status value deserialization jackson`() {
        val json = """{"status": "SUCCESS"}"""
        val response = mapper.readValue<ApiResponse>(json)
        assertEquals(Status.SUCCESS, response.status)
    }

    @Test
    fun `test unknown status value deserialization jackson`() {
        val unknownJson = """{"status": "unknown_value"}"""
        val response = mapper.readValue<ApiResponse>(unknownJson)
        assertEquals(Status.UNKNOWN, response.status)
    }

    @Test
    fun `test known status value deserialization kotlinx-serialization`() {
        val json = """{"status": "success"}"""
        val response = Json.decodeFromString<ApiResponse>(json)
        assertEquals(Status.SUCCESS, response.status)
    }

    @Test
    fun `test unknown status value deserialization kotlinx-serialization`() {
        val unknownJson = """{"status": "unknown_value"}"""
        val unknownResponse = Json.decodeFromString<ApiResponse>(unknownJson)
        assertEquals(Status.UNKNOWN, unknownResponse.status)
    }
}