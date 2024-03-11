package com.baeldung.comparefirstandsingleUnitTests

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith


class FirstUnitTest {

    @Test
    fun testFirstValue() = runBlocking {
        val multipleValuesFlow = flowOf(1, 2, 3)
        val firstValue = multipleValuesFlow.first()
        assertEquals(1, firstValue)
    }

    @Test
    fun testFirstValueFromEmptyFlow() = runBlocking {
        val emptyFlow = emptyFlow<Int>()
        val exception = assertFailsWith<NoSuchElementException> {
            runBlocking {
                emptyFlow.first()
            }
        }
        assertEquals("Expected at least one element", exception.message)
    }

}
