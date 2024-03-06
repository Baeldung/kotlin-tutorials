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

    suspend fun getFirstValue(flow: Flow<Int>): Int {
        return flow.first()
    }

    @Test
    fun testFirstValue() = runBlocking {
        val multipleValuesFlow = flowOf(1, 2, 3)
        val firstValue = getFirstValue(multipleValuesFlow)
        assertEquals(1, firstValue)
    }

    @Test
    fun testFirstValueFromEmptyFlow() = runBlocking {
        val emptyFlow = emptyFlow<Int>()
        val exception = assertFailsWith<NoSuchElementException> {
            runBlocking {
                getFirstValue(emptyFlow)
            }
        }
        assertEquals("Expected at least one element", exception.message)
    }

}