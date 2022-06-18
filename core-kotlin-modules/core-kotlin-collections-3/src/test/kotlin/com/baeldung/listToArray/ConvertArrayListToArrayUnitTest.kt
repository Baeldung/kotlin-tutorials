package com.baeldung.listToArray

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ConvertArrayListToArrayUnitTest {
    @Test
    fun `Given a list, when convert into Kotlin Array, the array should hold the same elements in the same order`() {
        val myList = listOf("one", "two", "three", "four", "five")
        val expectedArray = arrayOf("one", "two", "three", "four", "five")
        assertThat(myList.toTypedArray()).isEqualTo(expectedArray)
    }

    @Test
    fun `Given a list with primitive type, when convert into Kotlin primitive Array, the array should hold the same elements in the same order`() {
        val myList = listOf(1L, 2L, 3L, 4L, 5L)
        val expectedArray = longArrayOf(1L, 2L, 3L, 4L, 5L)
        assertThat(myList.toLongArray()).isEqualTo(expectedArray)
    }
}