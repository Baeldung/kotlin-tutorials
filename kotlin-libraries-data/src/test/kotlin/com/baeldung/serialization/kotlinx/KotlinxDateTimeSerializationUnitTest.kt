package com.baeldung.serialization.kotlinx

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@Serializable
data class KotlinxLocalDateTimeWrapper(
    val dateTime: LocalDateTime
)

class KotlinxDateTimeSerializationUnitTest {

    @Test
    fun `serializes kotlinx LocalDateTime`() {
        val dateTimeWrapper = KotlinxLocalDateTimeWrapper(
            dateTime = LocalDateTime.parse("2023-04-24T15:30:00.123")
        )
        val serializedDateTimeWrapper = Json.encodeToString(dateTimeWrapper)

        assertEquals("{\"dateTime\":\"2023-04-24T15:30:00.123\"}", serializedDateTimeWrapper)
    }
}