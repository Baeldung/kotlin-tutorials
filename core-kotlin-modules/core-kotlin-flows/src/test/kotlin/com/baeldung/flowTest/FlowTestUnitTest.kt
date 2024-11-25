package com.baeldung.flowTest

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.concurrent.CancellationException

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
    fun `errorFlow should emit values and recover from exception`() = runTest {
        val emittedValues = errorFlow().toList()

        assertEquals(listOf(1, 2, -1), emittedValues)
    }

    @Test
    fun `implicitCancellationFlow stops on cancellation`() = runTest {
        val emittedValues = mutableListOf<Int>()
        val job = launch {
            implicitCancellationFlow().collect { emittedValues.add(it) }
        }

        advanceTimeBy(600)
        job.cancelAndJoin()

        assertEquals(listOf(1,2), emittedValues)
    }

    @Test
    fun `cancellableFlow stops emitting after external cancellation`() = runTest {
        val emittedValues = mutableListOf<Int>()
        val job = launch {
            cancellableFlow().collect { emittedValues.add(it) }
        }

        advanceTimeBy(2000)
        job.cancelAndJoin()

        assertEquals(listOf(0, 1), emittedValues)
    }

    @Test
    fun `uncancellableFlow ensures cleanup occurs`() = runTest {
        val emittedValues = mutableListOf<Int>()
        val job = launch {
            uncancellableFlow().collect { emittedValues.add(it) }
        }

        advanceTimeBy(600)
        job.cancelAndJoin()

        assertEquals(listOf(1, -1), emittedValues)
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
    emit(2)
    throw Exception("Test Exception")
}.catch { e ->
    emit(-1)
}

fun cancellableFlow(): Flow<Int> = flow {
    try {
        repeat(5) {
            emit(it)
            delay(1000)
        }
    } finally {
        println("Cleanup: Emitting -1")
        emit(-1)
    }
}.onEach { value ->
    if (value == 2) throw CancellationException("Flow was canceled at value 2")
}.onCompletion { cause ->
    if (cause is CancellationException) {
        println("Flow canceled: Releasing resources")
    }
}

fun delayedFlow(): Flow<Int> = flow {
    delay(500)
    emit(1)
    delay(500)
    emit(2)
}

fun implicitCancellationFlow(): Flow<Int> = flow {
    emit(1)
    delay(500)
    emit(2)
    delay(500)
    emit(3)
}

fun uncancellableFlow(): Flow<Int> = flow {
    try {
        emit(1)
        delay(500)
        emit(2)
    } finally {
        withContext(NonCancellable) {
            println("Releasing resources")
            emit(-1)
        }
    }
}
