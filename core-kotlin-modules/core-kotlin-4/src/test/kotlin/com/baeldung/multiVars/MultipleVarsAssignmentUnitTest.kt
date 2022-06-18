package com.baeldung.multiVars

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

data class Article(val title: String, val author: String, val words: Long, val published: Boolean)

class MultipleVarsAssignmentUnitTest {
    private val expectedString = "Kotlin is awesome!"
    private val expectedLong = 42L
    private val expectedList = listOf("I", "am", "a", "list", ".")

    @Test
    fun `Given assignments sep by semicolons, when read variables, should have expected values`() {
        //@formatter:off
        val aLong = 42L; val aString = "Kotlin is awesome!"; val aList = listOf("I", "am", "a", "list", ".")
        //@formatter:on
        assertThat(aLong).isEqualTo(expectedLong)
        assertThat(aString).isEqualTo(expectedString)
        assertThat(aList).isEqualTo(expectedList)
    }

    @Test
    fun `Given a pair instance, when assign two vars by the pair, two vars should have expected values`() {
        val (aLong, aString) = 42L to "Kotlin is awesome!"
        assertThat(aLong).isEqualTo(expectedLong)
        assertThat(aString).isEqualTo(expectedString)
    }

    @Test
    fun `Given assignments by an array, when read variables, should have expected values`() {
        val (aLong, aString, aList) = arrayOf(42L, "Kotlin is awesome!", listOf("I", "am", "a", "list", "."))
        assertThat(aLong).isEqualTo(expectedLong)
        assertThat(aString).isEqualTo(expectedString)
        assertThat(aList).isEqualTo(expectedList)
    }

    @Test
    fun `Given assignments by a list, when read variables, should have expected value`() {
        val (aLong, aString, aList) = listOf(42L, "Kotlin is awesome!", listOf("I", "am", "a", "list", "."))
        assertThat(aLong).isEqualTo(expectedLong)
        assertThat(aString).isEqualTo(expectedString)
        assertThat(aList).isEqualTo(expectedList)
    }

    @Test
    fun `Given an article instance, when using destructuring declarations, variables should have expected value`() {
        val anArticle = Article("Define multiple variables at once in Kotlin", "Kai", 4200L, false)
        val (title, author, noOfWords, publishedAlready) = anArticle
        assertThat(title).isEqualTo(anArticle.title)
        assertThat(author).isEqualTo(anArticle.author)
        assertThat(noOfWords).isEqualTo(anArticle.words)
        assertThat(publishedAlready).isFalse
    }

    @Test
    fun `Given an article instance, when using destructuring declarations, only two variables should have expected value`() {
        val anArticle = Article("Define multiple variables at once in Kotlin", "Kai", 4200L, false)
        val (title, _, _, publishedAlready) = anArticle
        assertThat(title).isEqualTo(anArticle.title)
        assertThat(publishedAlready).isFalse
    }

}