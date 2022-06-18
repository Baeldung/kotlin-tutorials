package com.baeldung.listandset

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class ListAndSetConversionUnitTest {

    @Test
    fun `Given a list, when convert to set using HashSet constructor, the set should contain expected elements`() {
        val inputList = listOf("one", "two", "three", "four", "one", "three", "five")
        val expectedSet = setOf("one", "two", "three", "four", "five")
        assertThat(HashSet(inputList)).isInstanceOf(HashSet::class.java).isEqualTo(expectedSet)
    }

    @Test
    fun `Given a list, when convert to set using toSet(), the set should contain expected elements`() {
        val inputList = listOf("one", "two", "three", "four", "one", "three", "five")
        val expectedSet = setOf("one", "two", "three", "four", "five")
        assertThat(inputList.toSet()).isInstanceOf(LinkedHashSet::class.java).isEqualTo(expectedSet)
    }

    @Test
    fun `Given a set, when convert to list by calling ArrayList constructor, the list should contain expected elements`() {
        val inputSet = setOf("one", "two", "three", "four", "five")
        val expectedList = listOf("one", "two", "three", "four", "five")
        assertThat(ArrayList(inputSet)).isEqualTo(expectedList)
    }

    @Test
    fun `Given a set, when convert to list by calling toList(), the list should contain expected elements`() {
        val inputSet = setOf("one", "two", "three", "four", "five")
        val expectedList = listOf("one", "two", "three", "four", "five")
        assertThat(inputSet.toList()).isEqualTo(expectedList)
    }
}