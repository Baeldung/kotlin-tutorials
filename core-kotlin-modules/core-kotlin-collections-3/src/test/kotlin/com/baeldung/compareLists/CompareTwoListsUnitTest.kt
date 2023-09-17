package com.baeldung.compareLists

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue


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

    @Test
    fun `Given two list, when compare by length, should return true if length is equal`() {
        val names1 = listOf("Ron", "Roby", "Peter")
        val names2 = listOf("Bob", "John", "Betty")
        val areEqual = names1.zip(names2).all { (n1, n2) -> n1.length == n2.length }
        assertTrue(areEqual)
    }

    @Test
    fun `Given two list, when compare by length, should return false if length is not equal`() {
        val names1 = listOf("John", "Hagrid", "Rickson")
        val names2 = listOf("Abby", "Patrick", "Wren")
        val areEqual = names1.zip(names2).all { (n1, n2) -> n1.length == n2.length }
        assertFalse(areEqual)
    }

}