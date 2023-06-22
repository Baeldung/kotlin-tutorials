package com.baeldung.serialization.kotlinx

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class KotlinxDateTimeSerializationUnitTest {

    @Test
    fun `serializes kotlinx LocalDateTime`() {
        val dateTime = LocalDateTime.parse("2023-04-24T15:30:00.123")
        val serializedDateTime = Json.encodeToString(dateTime)

        assertEquals("\"2023-04-24T15:30:00.123\"", serializedDateTime)
    }
}