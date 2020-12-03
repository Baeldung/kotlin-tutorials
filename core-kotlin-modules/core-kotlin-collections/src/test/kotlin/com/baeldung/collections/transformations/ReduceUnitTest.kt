package com.baeldung.collections.transformations

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ReduceUnitTest {
    @Test
    fun testJoinToStringAsReduce() {
        val inputs = listOf("Jan", "Feb", "Mar", "Apr", "May")

        val result = inputs.reduce { acc, next -> "$acc, $next" }
        assertEquals("Jan, Feb, Mar, Apr, May", result)
    }

    @Test
    fun testFoldToLength() {
        val inputs = listOf("Jan", "Feb", "Mar", "Apr", "May")

        val result = inputs.fold(0) { acc, next -> acc + next.length }
        assertEquals(15, result)
    }
}