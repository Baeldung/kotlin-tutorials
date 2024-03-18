package com.baeldung.schedulingrepeatingtask

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.transform
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class SchedulingRepeatingTaskUnitTest {

    @Test
    fun `using repeat and delay`() = runBlocking {
        var count = 0
        repeat(10) {
            count++
            println("Timer ticked! $count")
            delay(1000.milliseconds)
        }

        assertEquals(10, count)
    }

    @Test
    fun `using withTimeout`(): Unit = runBlocking {
        assertThrows<TimeoutCancellationException> {
            withTimeout(5000.milliseconds) {
                while (true) {
                    println("Waiting for timeout")
                    delay(1000.milliseconds)
                }
            }
        }
    }

    @Test
    fun `using flow collect take`() = runBlocking {

        val flow = flow {
            while (true) {
                emit(Unit)
                delay(1000.milliseconds)
            }
        }

        var count = 0

        flow.take(10).collect{
            count++
            println(count)
        }

        assertEquals(10, count)
    }

}