package com.baeldung.compareLists

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


infix fun <T> List<T>.equalsIgnoreOrder(other: List<T>) = this.size == other.size && this.toSet() == other.toSet()

class CompareTwoListsUnitTest {
    private val targetList = listOf("one", "two", "three", "four", "five")
    private val listExactlySame = listOf("one", "two", "three", "four", "five")
    private val listInDiffOrder = listOf("two", "one", "three", "five", "four")
    private val listInDiffSizeButWithSameElements = listOf("one", "two", "three", "four", "five", "five", "five")
    private val listInDiffSizeAndElements = listOf("one", "two", "three", "four", "five", "five", "five", "I am a new element")
    private val listInDiffElement = listOf("ONE", "two", "three", "four", "FIVE")

    @Test
    fun `Given two lists, when compare elements and order using == should have expected result`() {
        targetList.let {
            assertThat(listExactlySame == it).isTrue
            assertThat(listInDiffOrder == it).isFalse
            assertThat(listInDiffSizeButWithSameElements == it).isFalse
            assertThat(listInDiffSizeAndElements == it).isFalse
            assertThat(listInDiffElement == it).isFalse
        }
    }

    @Test
    fun `Given two lists, when compare ignore the order only, should have expected result`() {
        targetList.let {
            assertThat(listExactlySame equalsIgnoreOrder it).isTrue
            assertThat(listInDiffOrder equalsIgnoreOrder it).isTrue
            assertThat(listInDiffSizeButWithSameElements equalsIgnoreOrder it).isFalse
            assertThat(listInDiffSizeAndElements equalsIgnoreOrder it).isFalse
            assertThat(listInDiffElement equalsIgnoreOrder it).isFalse
        }
    }
}