package com.baeldung.serialization.defaultValueParameters

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@Serializable
data class Car1(val type: String, val color: String)

@Serializable
data class Car2(val type: String, val color: String = "Blue")

@Serializable
data class Car3(val type: String, @EncodeDefault val color: String = "Blue")

class DefaultValueSerializationUnitTest {

    @Test
    fun `normal serialization of data class with no default values`() {
        val car = Car1("Ford", "Grey")
        val jsonString = Json.encodeToString(car)
        val str = "{\"type\":\"Ford\",\"color\":\"Grey\"}"
        assertEquals(str, jsonString)
    }

    @Test
    fun `normal deserialization of Json to data class object`() {
        val jsonString = "{\"type\":\"Ford\",\"color\":\"Grey\"}"
        val car = Json.decodeFromString<Car1>(jsonString)
        assertEquals("Ford", car.type)
        assertEquals("Grey", car.color)
    }

    @Test
    fun `normal serialization of data class with default value parameter`(){
        val car = Car2("Ford")
        val jsonString = Json.encodeToString(car)
        assertEquals("{\"type\":\"Ford\"}", jsonString)
        assertFalse(jsonString.contains("color"))
    }

    @Test
    fun `serialize data class with default value parameter using format setting`(){
        val format = Json { encodeDefaults = true }
        val car = Car2("Ford")
        val jsonString = format.encodeToString(car)
        assertEquals("{\"type\":\"Ford\",\"color\":\"Blue\"}", jsonString)
        assertTrue(jsonString.contains("color"))
    }

    @Test
    fun `serialize data class with default paramter using anotation method`(){
        val car = Car3("Ford")
        val jsonString = Json.encodeToString(car)
        assertEquals("{\"type\":\"Ford\",\"color\":\"Blue\"}", jsonString)
    }

}