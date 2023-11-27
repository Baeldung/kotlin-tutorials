package com.baeldung.gsonWithTypetoken

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.jupiter.api.Test
import java.lang.reflect.Type
import kotlin.test.assertEquals
import kotlin.test.assertFalse


data class Book(val id: Int, val title: String, val author: String)

class GsonWithTypeTokenUnitTest {
    val jsonStr: String = """
        [
          {"id": 1,"title": "First title","author": "Micheal L"},
          {"id": 2,"title": "Second title","author": "Enombe N"},
          {"id": 1,"title": "Third title","author": "Steve P"}
        ]
    """.trimIndent()

    @Test
    fun `explicit deserialization of JSON to Kotlin object`(){
        val books = Gson().fromJson(jsonStr, Array<Book>::class.java)
        assertEquals(3, books.size)
        assertFalse(books.isEmpty())
        assertEquals("First title", books[0].title)
    }

    @Test
    fun `get list of Books with Gson extension function`() {
        val books = Gson().fromJsonList<Book>(jsonStr)
        assertEquals(3, books.size)
        assertFalse(books.isEmpty())
        assertEquals("Second title", books[1].title)
    }

    @Test
    fun `get list of Books with explicit TypeToken object`(){
        val bookType = object : TypeToken<List<Book>>() {}.type
        val books = Gson().fromJson<List<Book>>(jsonStr, bookType)
        assertEquals(3, books.size)
        assertFalse(books.isEmpty())
        assertEquals("Second title", books[1].title)
    }

    @Test
    fun `get list of Books with generic helper function`(){
        val bookType = genericType<List<Book>>()
        val books = Gson().fromJson<List<Book>>(jsonStr, bookType)
        assertEquals(3, books.size)
        assertFalse(books.isEmpty())
        assertEquals("Third title", books[2].title)
    }
}

inline fun <reified T> Gson.fromJsonList(json: String) = fromJson<List<T>>(json, object : TypeToken<List<T>>() {}.type)
inline fun <reified T> genericType(): Type = object: TypeToken<T>() {}.type
