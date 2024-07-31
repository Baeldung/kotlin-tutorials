package com.baeldung.kotlin.serializeEnumField

import com.google.gson.*
import com.google.gson.annotations.SerializedName
import org.junit.jupiter.api.Test
import java.lang.reflect.Type
import kotlin.test.assertEquals


class EnumFieldGsonUnitTest {

    enum class Language(val description: String) {
        @SerializedName("Kotlin_is_awesome")
        KOTLIN("Kotlin_is_awesome"),

        @SerializedName("Java_is_great")
        JAVA("Java_is_great"),

        @SerializedName("Go_is_nice")
        GO("Go_is_nice")
    }

    data class Dev(val name: String, val language: Language)

    @Test
    fun `when serialize enum field with gson @SerializedName, then get expected result`() {
        val dev = Dev("Kai", Language.GO)
        val json = Gson().toJson(dev)
        assertEquals("""{"name":"Kai","language":"Go_is_nice"}""", json)
    }
}

class EnumFieldGson2UnitTest {
    class LanguageSerializer : JsonSerializer<Language> {
        override fun serialize(src: Language?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
            return JsonPrimitive(requireNotNull(src).description)
        }
    }

    enum class Language(val description: String) {
        KOTLIN("Kotlin_is_awesome"),
        JAVA("Java_is_great"),
        GO("Go_is_nice")
    }

    data class Dev(val name: String, val language: Language)

    @Test
    fun `when serialize enum field with gson @SerializedName, then get expected result`() {
        val gson = GsonBuilder().registerTypeAdapter(Language::class.java, LanguageSerializer()).create()
        val dev = Dev("Kai", Language.GO)
        val json = gson.toJson(dev)
        assertEquals("""{"name":"Kai","language":"Go_is_nice"}""", json)
    }
}