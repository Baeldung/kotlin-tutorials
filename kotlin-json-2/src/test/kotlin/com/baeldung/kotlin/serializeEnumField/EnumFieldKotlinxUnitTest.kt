package com.baeldung.kotlin.serializeEnumField

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class EnumFieldKotlinxUnitTest {
    @Serializable
    enum class Language(val description: String) {
        @SerialName("Kotlin_is_awesome")
        KOTLIN("Kotlin_is_awesome"),

        @SerialName("Java_is_great")
        JAVA("Java_is_great"),

        @SerialName("Go_is_nice")
        GO("Go_is_nice")
    }

    @Serializable
    data class Dev(val name: String, val language: Language)

    @Test
    fun `when serialize enum field with kotlinx @SerialName, then get expected result`() {
        val dev = Dev("Kai", Language.JAVA)
        val json = Json.encodeToString(dev)
        assertEquals("""{"name":"Kai","language":"Java_is_great"}""", json)
    }

}

class EnumFieldKotlinx2UnitTest {
    class LanguageSerializer : KSerializer<Language> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Language", PrimitiveKind.STRING)

        override fun deserialize(decoder: Decoder): Language {
            val desc = decoder.decodeString()
            return Language.entries.first { desc == it.description }
        }

        override fun serialize(encoder: Encoder, value: Language) {
            encoder.encodeString(value.description)
        }
    }

    @Serializable(with = LanguageSerializer::class)
    enum class Language(val description: String) {
        KOTLIN("Kotlin_is_awesome"),
        JAVA("Java_is_great"),
        GO("Go_is_nice")
    }

    @Serializable
    data class Dev(val name: String, val language: Language)

    @Test
    fun `when serialize enum field with a custom serializer, then get expected result`() {
        val dev = Dev("Kai", Language.JAVA)
        val json = Json.encodeToString(dev)
        assertEquals("""{"name":"Kai","language":"Java_is_great"}""", json)
    }
}