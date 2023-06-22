package com.baeldung.serialization.kotlinx

import java.time.LocalDateTime
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

object LocalDateTimeSerializer : KSerializer<LocalDateTime> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): LocalDateTime {
        return LocalDateTime.parse(decoder.decodeString())
    }
}

class CustomSerializerUnitTest {

    @Test
    fun `fails to serialize LocalDateTime if serializer is not registered`() {
        val dateTime = LocalDateTime.parse("2023-04-24T15:30:00.123")

        assertThrows<SerializationException> {
            Json.encodeToString(dateTime)
        }
    }

    @Test
    fun `serializes LocalDateTime with registered serializer`() {
        val json = Json {
            serializersModule = SerializersModule {
                contextual(LocalDateTimeSerializer)
            }
        }

        val dateTime = LocalDateTime.parse("2023-04-24T15:30:00.123")
        val serializedDateTime = json.encodeToString(dateTime)

        assertEquals("\"2023-04-24T15:30:00.123\"", serializedDateTime)
    }

    @Test
    fun `deserializes LocalDateTime with registered serializer`() {
        val json = Json {
            serializersModule = SerializersModule {
                contextual(LocalDateTimeSerializer)
            }
        }

        val dateTime = LocalDateTime.parse("2023-04-24T15:30:00.123")
        val deserializedDateTime = json.decodeFromString<LocalDateTime>("\"2023-04-24T15:30:00.123\"")

        assertEquals(dateTime, deserializedDateTime)
    }
}