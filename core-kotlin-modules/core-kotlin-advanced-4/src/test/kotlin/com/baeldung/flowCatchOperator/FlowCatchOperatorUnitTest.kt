package com.baeldung.flowCatchOperator

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FlowCatchOperatorUnitTest {

    @Test
    fun `catch operator logs exception`() = runTest {
        val emittedValues = mutableListOf<Int>()
        val flow = flow {
            emit(1)
            emit(2)
            throw IllegalStateException("Test exception")
        }

        flow.catch { e ->
            assertEquals("Test exception", e.message)
        }.collect { emittedValues.add(it) }

        assertEquals(listOf(1, 2), emittedValues)
    }

    @Test
    fun `catch operator emits fallback value`() = runTest {
        val emittedValues = mutableListOf<Int>()
        val flow = flow {
            emit(1)
            throw IllegalArgumentException("Simulated error")
        }
        flow.catch {
            emit(-1)
        }.collect {
            emittedValues.add(it)
        }
        assertEquals(listOf(1, -1), emittedValues)
    }

    @Test
    fun `catch operator works with retries`() = runTest {
        var attempt = 0
        val emittedValues = mutableListOf<Int>()
        val flow = flow {
            if (attempt++ < 3) {
                throw RuntimeException("Network error")
            }
            emit(1)
        }
        flow.retry(3) { it is RuntimeException }
            .catch {
                emit(-1)
            }
            .collect { emittedValues.add(it) }

        assertEquals(listOf(1), emittedValues)
    }


    @Test
    fun `catch operator handles specific exceptions correctly`() = runTest {
        val emittedValues = mutableListOf<Int>()
        val flow = flow {
            emit(1)
            throw IllegalArgumentException("Recoverable error")
        }

        flow.catch { e ->
            if (e is IllegalArgumentException) {
                emit(-1)
            } else {
                throw e
            }
        }.collect {
            emittedValues.add(it)
        }

        assertEquals(listOf(1, -1), emittedValues)
    }

}