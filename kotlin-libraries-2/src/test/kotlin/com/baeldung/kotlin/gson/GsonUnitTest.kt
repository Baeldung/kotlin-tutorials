package com.baeldung.kotlin.gson

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.assertj.core.api.Assertions.assertThat
import org.checkerframework.checker.units.qual.A
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class GsonUnitTest {

    @Test
    fun serializeObjectTest() {
        val author = Author("John", "Technical Author", listOf("Kotlin", "Java"))
        val serialized = Gson().toJson(author)

        val json = """{"name":"John","type":"Technical Author","topics":["Kotlin","Java"]}"""
        assertEquals(serialized, json)
    }

    @Test
    fun deserializeObjectTest() {
        val json = """{"name":"John","type":"Technical Author","topics":["Kotlin","Java"]}"""
        val author = Gson().fromJson(json, Author::class.java)

        assertEquals(author.name, "John")
        assertThat(author.topics).isNotEmpty
    }

    @Test
    fun deserializeObjectWithMissingFieldsTest() {
        val json = """{"name":"John","topics":["Kotlin","Java"]}"""
        val author = Gson().fromJson(json, Author::class.java)

        assertEquals(author.name, "John")
        assertThat(author.type).isNull()
        assertThat(author.topics).isNotEmpty
    }

    @Test
    fun serializeObjectWithNonMatchingKeysTest() {
        val article = Article("Streams in Kotlin", "Kotlin", 0)
        val serialized = Gson().toJson(article)

        val json = """{"article_title":"Streams in Kotlin","article_category":"Kotlin","article_views":0}"""
        assertEquals(serialized, json)
    }

    @Test
    fun deserializeArticleWithNotMatchingKeysTest() {

        val json = """{"article_title":"Streams in Kotlin","article_category":"Kotlin","article_views":0}"""
        val article = Gson().fromJson(json, Article::class.java)

        assertEquals(article.title, "Streams in Kotlin")
        assertEquals(article.category, "Kotlin")
        assertEquals(article.views, 0)
    }

    @Test
    fun serializeWithNestedObjectsTest() {
        val author =
            Author("John", "Technical Author", listOf("Kotlin", "Java"), Contact("john@example.com", "+1234567"))
        val serialized = Gson().toJson(author)

        val json =
            """{"name":"John","type":"Technical Author","topics":["Kotlin","Java"],"contact":{"email":"john@example.com","phone":"+1234567"}}"""
        assertEquals(serialized, json)
    }

    @Test
    fun deserializeWithNestedObjectsTest() {

        val json =
            """{"name":"John","type":"Technical Author","topics":["Kotlin","Java"],"contact":{"email":"john@example.com","phone":"+1234567"}}"""

        val author = Gson().fromJson(json, Author::class.java)

        assertEquals(author.name, "John")
        assertEquals(author.type, "Technical Author")
        assertThat(author.contact).isNotNull;
    }

    @Test
    fun serializeObjectListTest() {
        val authors = listOf(Author("John"), Author("Jane"), Author("William"))
        val serialized = Gson().toJson(authors)

        val json = """[{"name":"John"},{"name":"Jane"},{"name":"William"}]"""
        assertEquals(serialized, json)
    }

    @Test
    fun deserializeObjectListTest() {
        val json = """[{"name":"John"},{"name":"Jane"},{"name":"William"}]"""
        val typeToken = object : TypeToken<List<Author>>() {}.type
        val authors = Gson().fromJson<List<Author>>(json, typeToken)

        assertThat(authors).isNotEmpty
        assertThat(authors).anyMatch { a -> a.name == "John" }
    }
}
