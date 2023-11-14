package com.baeldung.kotlin.handlejson


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNames
import org.junit.Assert.assertEquals
import org.junit.Test

@Serializable
data class User(@JsonNames("uid") val id: String)

@Serializable
data class Car(@SerialName("which_model") val model: String)

class HandleJsonUnitTest {
    @Test
    fun testHandleJsonWithJsonNames() {
        val json = """{"uid":"1"}"""
        val user = Json.decodeFromString<User>(json)
        assertEquals("1", user.id)
    }

    @Test
    fun testHandleJsonWithSerialName() {
        val carJson = """{"which_model":"Altima"}"""
        val car = Json.decodeFromString<Car>(carJson)
        assertEquals("Altima", car.model)
    }
}
