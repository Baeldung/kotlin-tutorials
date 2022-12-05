package com.baeldung.maxvalue


import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MaxValueArrayTest {

    @Test
    fun givenNonEmptyArray_whenGetMaxOrNull_thenMaxValueIsReturned() {

        val array = listOf(5, 6, 7, 8, 9, 4, 3, 2, 1)
        val max = array.maxOrNull()

        assertThat(max).isEqualTo(9)
    }

    @Test
    fun givenNonEmptyArray_whenGetMaxBy_thenMaxValueIsReturned() {

        val array = listOf(Person("Alan", 34), Person("Jason", 45), Person("Joe", 28))
        val max = array.maxByOrNull { p -> p.age }

        assertThat(max).isNotNull()
        if (max != null) {
            assertThat(max.name).isEqualTo("Jason")
        }

    }

    @Test
    fun givenNonEmptyArray_whenGetMaxWith_thenMaxValueIsReturned() {

        val array = listOf(Person("Alan", 34), Person("Jason", 45), Person("Joe", 28))
        val max = array.maxWithOrNull(Comparator<Person> { a, b -> a.age.compareTo(b.age) })

        assertThat(max).isNotNull()
        if (max != null) {
            assertThat(max.name).isEqualTo("Jason")
        }

    }

    @Test
    fun givenNonEmptyArray_whenGetBySorting_thenMaxValueShouldBeReturned() {

        val array = listOf(Person("Alan", 34), Person("Jason", 45), Person("Joe", 28))
        val max = array.sortedByDescending { p -> p.age }[0]

        assertThat(max).isNotNull()
        assertThat(max.name).isEqualTo("Jason")
    }
}

