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
    fun serializeObjectListTest() {

        val authors = listOf(
            Author("John", "Technical Author"),
            Author("Jane", "Technical Author"),
            Author("William", "Technical Editor")
        )
        val serialized = Gson().toJson(authors)

        val json =
            """[{"name":"John","type":"Technical Author"},{"name":"Jane","type":"Technical Author"},{"name":"William","type":"Technical Editor"}]"""
        assertEquals(serialized, json)
    }

    @Test
    fun deserializeObjectListTest() {

        val json =
            """[{"name":"John","type":"Technical Author"},{"name":"Jane","type":"Technical Author"},{"name":"William","type":"Technical Editor"}]"""
        val typeToken = object : TypeToken<List<Author>>() {}.type
        val authors = Gson().fromJson<List<Author>>(json, typeToken)

        assertThat(authors).isNotEmpty
        assertThat(authors).hasSize(3)
        assertThat(authors).anyMatch { a -> a.name == "John" }
        assertThat(authors).anyMatch { a -> a.type == "Technical Editor" }
    }

    @Test
    fun deserializeObjectListWithMissingFieldsTest() {

        val json =
            """[{"name":"John"},{"name":"Jane"},{"name":"William"}]"""
        val typeToken = object : TypeToken<List<Author>>() {}.type
        val authors = Gson().fromJson<List<Author>>(json, typeToken)

        assertThat(authors).isNotEmpty
        assertThat(authors).hasSize(3)
        assertThat(authors).anyMatch { a -> a.name == "John" }
        assertThat(authors).allMatch { a -> a.type == null }
    }

    @Test
    fun serializeObjectListWithNonMatchingKeysTest() {

        val authors = listOf(
            Author(
                "John",
                "Technical Author",
                listOf(Article("Streams in Java", "Java", 3), Article("Lambda Expressions", "Java", 5))
            ),
            Author("Jane", "Technical Author", listOf(Article("Functional Interfaces", "Java", 2))),
            Author("William", "Technical Editor")
        )
        val serialized = Gson().toJson(authors)

        val json =
            """[{"name":"John","type":"Technical Author","articles":[{"article_title":"Streams in Java","article_category":"Java","article_views":3},{"article_title":"Lambda Expressions","article_category":"Java","article_views":5}]},{"name":"Jane","type":"Technical Author","articles":[{"article_title":"Functional Interfaces","article_category":"Java","article_views":2}]},{"name":"William","type":"Technical Editor"}]"""
        assertEquals(serialized, json)
    }

    @Test
    fun deserializeObjectListWithNonMatchingKeysTest() {

        val json =
            """[{"name":"John","type":"Technical Author","articles":[{"article_title":"Streams in Java","article_category":"Java","article_views":3},{"article_title":"Lambda Expressions","article_category":"Java","article_views":5}]},{"name":"Jane","type":"Technical Author","articles":[{"article_title":"Functional Interfaces","article_category":"Java","article_views":2}]},{"name":"William","type":"Technical Editor"}]"""
        val typeToken = object : TypeToken<List<Author>>() {}.type
        val authors = Gson().fromJson<List<Author>>(json, typeToken)


        assertThat(authors).isNotEmpty
        assertThat(authors).hasSize(3)
        assertThat(authors).anyMatch { a -> a.name == "John" }
        assertThat(authors).anyMatch { a -> a.type == "Technical Editor" }
    }
}
