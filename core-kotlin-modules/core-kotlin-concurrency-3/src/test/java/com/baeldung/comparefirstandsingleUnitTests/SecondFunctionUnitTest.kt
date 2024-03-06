package com.baeldung.comparefirstandsingleUnitTests

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class SecondFunctionUnitTest {

    suspend fun getSingleValue(flow: Flow<Int>): Int? {
        return try {
            flow.single()
        } catch (e: IllegalArgumentException) {
            null
        }
    }

    @Test
    fun testSingleValue() = runBlocking {
        val multipleValuesFlow = flowOf(42)
        val singleValue = getSingleValue(multipleValuesFlow)
        assertEquals(42, singleValue)
    }

    @Test
    fun testIllegalArgumentException() = runBlocking {
        val emptyFlow = flowOf<Int>()
        val exception = assertFailsWith<NoSuchElementException> {
            runBlocking {
                emptyFlow.single()
            }
        }
        assertEquals("Flow is empty", exception.message)
    }
}