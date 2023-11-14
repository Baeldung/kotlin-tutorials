package com.baeldung.maxvalue


import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class MaxValueArrayUnitTest {
    @Test
    fun givenNonEmptyArray_whenGetMaxOrNull_thenMaxValueIsReturned() {
        val array = listOf(5, 6, 7, 8, 9, 4, 3, 2, 1)
        val max = array.maxOrNull()

        assertThat(max).isEqualTo(9)
    }

    @Test
    fun givenNonEmptyArray_whenGetMaxBy_thenMaxValueIsReturned() {
        val array = listOf(
            Person("Alan", 34, Height(5, 10)),
            Person("Jason", 45, Height(5, 11)),
            Person("Joe", 28, Height(5, 9))
        )

        val max = array.maxByOrNull { p -> p.age }

        assertNotNull(max)
        assertThat(max.name).isEqualTo("Jason")
    }

    @Test
    fun givenNonEmptyArray_whenGetMaxWith_thenMaxValueIsReturned() {
        val array = listOf(
            Person("Alan", 34, Height(5, 10)),
            Person("Jason", 45, Height(5, 13)),
            Person("Joe", 45, Height(5, 11))
        )

        val max = array.maxWith(compareBy({ it.height.feet }, { it.height.inches }))

        assertThat(max).isNotNull()
        assertThat(max.name).isEqualTo("Jason")
    }

    @Test
    fun givenNonEmptyArrayWithMultipleMaxCandidate_whenGetMaxWith_thenMaxValueIsReturned() {
        val array = listOf(
            Person("Alan", 34, Height(5, 10)),
            Person("Jason", 45, Height(5, 11)),
            Person("Joe", 45, Height(5, 11))
        )

        val max = array.maxWith(compareBy({ it.height.feet }, { it.height.inches }))

        assertThat(max).isNotNull()
        assertThat(max.name).isEqualTo("Jason")
    }

    @Test
    fun givenNonEmptyArray_whenGetBySorting_thenMaxValueShouldBeReturned() {
        val array = listOf(
            Person("Alan", 34, Height(5, 10)),
            Person("Jason", 45, Height(5, 11)),
            Person("Joe", 28, Height(5, 9))
        )

        val max = array.sortedByDescending { p -> p.age }.first()

        assertThat(max).isNotNull()
        assertThat(max.name).isEqualTo("Jason")
    }

    @Test
    fun givenEmptyArray_whenGetMax_thenExceptionIsThrown() {
        assertThatExceptionOfType(NoSuchElementException::class.java).isThrownBy {
            val array = emptyArray<Int>()
            val max = array.max()
        }
    }

    @Test
    fun givenEmptyArray_whenGetMaxBy_thenExceptionIsThrown() {
        assertThatExceptionOfType(NoSuchElementException::class.java).isThrownBy {
            val array = emptyArray<Person>()
            val max = array.maxBy { p -> p.age }
        }
    }
}

