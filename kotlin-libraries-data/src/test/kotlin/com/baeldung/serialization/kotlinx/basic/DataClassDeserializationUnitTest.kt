package com.baeldung.serialization.kotlinx.basic

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@Serializable
data class DeserializablePerson(val firstName: String, val lastName: String, val age: Int) {
    init {
        require(firstName.isNotEmpty()) { "First name can't be empty" }
    }
}

class DataClassDeserializationUnitTest {

    @Test
    fun `fails to deserialize string to object with delegated property`() {
        val json = """{"firstName":"John","lastName":"Doe","age":30}"""

        val exception = assertFailsWith<NoSuchFieldError> {
            Json.decodeFromString<SerializablePerson>(json)
        }
        assertEquals("id\$delegate", exception.message)
    }

    @Test
    fun `deserializes string to object`() {
        val json = """{"firstName":"John","lastName":"Doe","age":30}"""
        val person = Json.decodeFromString<DeserializablePerson>(json)

        assertEquals(DeserializablePerson("John", "Doe", 30), person)
    }

    @Test
    fun `fails to deserialize string to object when json input is incorrect`() {
        val json = """{"firstName":"","lastName":"Doe","age":30}"""

        assertFailsWith<IllegalArgumentException> {
            Json.decodeFromString<DeserializablePerson>(json)
        }
    }
}