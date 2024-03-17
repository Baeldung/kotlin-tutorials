package com.baeldung.schedulingrepeatingtask

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.transform
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.time.Duration.Companion.milliseconds

class SchedulingRepeatingTaskUnitTest {

    @Test
    fun `using repeat and delay`() = runBlocking {
        repeat(10) {
            println("Timer ticked!")
            delay(1000)
        }
    }

    @Test
    fun `using withTimeout`(): Unit = runBlocking {
        assertThrows<TimeoutCancellationException> {
            withTimeout(5000) {
                while (true) {
                    println("Waiting for timeout")
                    delay(1000.milliseconds)
                }
            }
        }
    }

    // Using flow & collect
    @Test
    fun `using flow collect`() = runBlocking {
        var count = 0
        val job = launch {
            flow {
                while (true) {
                    emit(Unit)
                    delay(1000.milliseconds)
                }
            }.collect {
                count++
                println("Task executed")
                if (count == 10) cancel() // cancel after 10 executions
            }
        }
        job.join()
    }

    @Test
    fun `using flow and take`() = runBlocking {
        flow {
            while (true) {
                emit(Unit)
                delay(1000.milliseconds)
            }
        }.take(10).collect {
            println("Task executed times")
        }
    }

    // using flowOf & collect
    @Test
    fun `using flowOf`() = runBlocking {
        flowOf(1, 2, 3, 4, 5).collect {
            println("Received value: $it")
            delay(1000.milliseconds)
        }
    }

    // using flowOf, transform and collect
    @Test
    fun `using flowOf with transform`() = runBlocking {
        flowOf(1, 2, 3, 4, 5).transform { value ->
            emit("Task executed $value times")
        }.collect {
            println(it)
            delay(1000.milliseconds)
        }
    }

    // Using flow, take & collect


}