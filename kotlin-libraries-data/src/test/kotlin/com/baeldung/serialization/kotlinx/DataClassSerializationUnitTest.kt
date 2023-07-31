package com.baeldung.serialization.kotlinx

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@Serializable
data class SerializablePerson(val firstName: String, val lastName: String, val age: Int) {
    init {
        require(firstName.isNotEmpty()) { "First name can't be empty" }
    }

    val fullName: String get() = "$firstName $lastName"

    val id by::lastName
}

class DataClassSerializationUnitTest {

    @Test
    fun `serializes object to string`() {
        val person = SerializablePerson("John", "Doe", 30)
        val json = Json.encodeToString(person)

        assertEquals("""{"firstName":"John","lastName":"Doe","age":30}""", json)
    }
}