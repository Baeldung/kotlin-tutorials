package com.baeldung.flowTest

import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class FlowTestUnitTest {

        @Test
        fun `simpleFlow should emit 1 2 3`() = runTest {
            val flow = simpleFlow().toList()

            assertEquals(listOf(1, 2, 3), flow)
        }

    @Test
    fun `transformedFlow should multiply values by 2`() = runTest {
        val result = transformedFlow().toList()
        assertEquals(listOf(2, 4, 6), result)
    }

    @Test
    fun `errorFlow should throw Test Exception`() = runTest {
        val flow = errorFlow()

        val exception = assertThrows<Exception> {
            flow.collect()
        }
        assertEquals("Test Exception", exception.message)

    }

    @Test
    fun `cancellableFlow should stop after cancellation`() = runTest {
        val emittedValues = mutableListOf<Int>()

        val job = launch {
            cancellableFlow().collect {
                emittedValues.add(it)
                if (it == 2) cancel()
            }
        }
        job.join()

        assertEquals(listOf(0, 1, 2), emittedValues)
    }

    @Test
    fun `delayedFlow should handle delayed emissions`() = runTest {
        val result = delayedFlow().toList()
        assertEquals(listOf(1, 2), result)
    }
}

fun simpleFlow(): Flow<Int> = flow {
    emit(1)
    emit(2)
    emit(3)
}

fun transformedFlow(): Flow<Int> = flow {
    emit(1)
    emit(2)
    emit(3)
}.map { it * 2 }

fun errorFlow(): Flow<Int> = flow {
    emit(1)
    throw Exception("Test Exception")
}

fun cancellableFlow(): Flow<Int> = flow {
    repeat(5) {
        emit(it)
        delay(1000)
    }
}

fun delayedFlow(): Flow<Int> = flow {
    delay(500)
    emit(1)
    delay(500)
    emit(2)
}


