package com.baeldung.kotlin.serializeEnumField

import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class EnumFieldInJsonIntroUnitTest {
    enum class Language(val description: String) {
        KOTLIN("Kotlin_is_awesome"),
        JAVA("Java_is_great"),
        GO("Go_is_nice")
    }

    data class Dev(val name: String, val language: Language)

    @Test
    fun `when serialize enum field, then enum name is taken in json`() {
        val mapper = ObjectMapper().registerModule(KotlinModule.Builder().build())
        val dev = Dev("Kai", Language.KOTLIN)
        val json = mapper.writeValueAsString(dev)
        assertEquals("""{"name":"Kai","language":"KOTLIN"}""", json)
    }
}

class EnumFieldJacksonUnitTest {
    enum class Language(@JsonValue val description: String) {
        KOTLIN("Kotlin_is_awesome"),
        JAVA("Java_is_great"),
        GO("Go_is_nice")
    }

    data class Dev(val name: String, val language: Language)

    @Test
    fun `when serialize enum field with jackson @JsonValue, then get expected result`() {
        val mapper = ObjectMapper().registerModule(KotlinModule.Builder().build())
        val dev = Dev("Kai", Language.KOTLIN)
        val json = mapper.writeValueAsString(dev)
        assertEquals("""{"name":"Kai","language":"Kotlin_is_awesome"}""", json)
    }
}