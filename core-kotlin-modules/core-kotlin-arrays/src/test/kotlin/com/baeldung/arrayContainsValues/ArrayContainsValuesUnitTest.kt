package com.baeldung.arrayContainsValues

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

infix fun <T> Array<T>.containsAnyFrom(elements: Collection<T>): Boolean {
    return any(elements.toSet()::contains)
}

class ArrayContainsValuesUnitTest {
    val myArray = arrayOf("Ruby", "Java", "Kotlin", "Go", "Python", "Perl")

    @Test
    fun `Given an Array, when check if it contains a value using 'any()' should get expected result`() {
        assertThat(myArray.any { it == "Kotlin" }).isTrue
        assertThat(myArray.any { it == "Php" }).isFalse
    }

    @Test
    fun `Given an Array, when check if it contains a value using 'in' should get expected result`() {
        assertThat("Kotlin" in myArray).isTrue
        assertThat("Php" in myArray).isFalse
    }

    @Test
    fun `Given an Array, when check if it contains at least one value using Java's disjoint() should get expected result`() {
        val toBeChecked1 = setOf("Scala", "Php", "whatever", "Kotlin")
        val toBeChecked2 = setOf("Scala", "Php", "Javascript", "whatever")

        val mySet = myArray.toSet()

        assertThat(!Collections.disjoint(mySet, toBeChecked1)).isTrue
        assertThat(!Collections.disjoint(mySet, toBeChecked2)).isFalse
    }

    @Test
    fun `Given an Array, when check if it contains at least one value using 'intersect()' should get expected result`() {
        val toBeChecked1 = setOf("Scala", "Php", "whatever", "Kotlin")
        val toBeChecked2 = setOf("Scala", "Php", "Javascript", "whatever")

        val mySet = myArray.toSet()

        assertThat((toBeChecked1 intersect mySet).isNotEmpty()).isTrue
        assertThat((toBeChecked2 intersect mySet).isNotEmpty()).isFalse
    }

    @Test
    fun `Given an Array, when check if it contains at least one value using 'any' and 'in' should get expected result`() {
        val toBeChecked1 = setOf("Scala", "Php", "whatever", "Kotlin")
        val toBeChecked2 = setOf("Scala", "Php", "Javascript", "whatever")

        assertThat(myArray.any { it in toBeChecked1 }).isTrue
        assertThat(myArray.any { it in toBeChecked2 }).isFalse

        assertThat(toBeChecked1.any { it in myArray }).isTrue
        assertThat(toBeChecked2.any { it in myArray }).isFalse

        assertThat(myArray.any(toBeChecked1::contains)).isTrue
        assertThat(myArray.any(toBeChecked2::contains)).isFalse
    }

    @Test
    fun `Given an Array, when check if it contains at least one value using created infix function should get expected result`() {
        val toBeChecked1 = setOf("Scala", "Php", "whatever", "Kotlin")
        val toBeChecked2 = setOf("Scala", "Php", "Javascript", "whatever")

        assertThat(myArray containsAnyFrom toBeChecked1).isTrue
        assertThat(myArray containsAnyFrom toBeChecked2).isFalse
    }
}