package com.baeldung.schedulertimer

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.within
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

fun CoroutineScope.startTimer(delay: Long = 1000L, task: suspend () -> Unit): Job {
    return launch {
        delay(delay)
        task()
    }
}

fun CoroutineScope.startInfiniteScheduler(interval: Long = 1000L, task: suspend () -> Unit): Job {
    return launch {
        while (isActive) {
            task()
            delay(interval)
        }
    }
}

suspend fun runWithTimeout(timeout: Long, task: suspend () -> Unit) {
    withTimeout(timeout) {
        task()
    }
}


class SchedulerTimerUnitTest {

    @Test
    fun `timer executes task after delay`() = runBlocking {
        var taskExecuted = false
        val task: suspend () -> Unit = { taskExecuted = true }

        startTimer(delay = 1000L, task = task)
        delay(1500L)

        assertTrue(taskExecuted)
    }

    @Test
    fun `infinite scheduler stops when scope is canceled`(): Unit = runBlocking {
        var taskExecutionCount = 0
        val task: suspend () -> Unit = { taskExecutionCount++ }

        val schedulerJob = startInfiniteScheduler(interval = 500L, task = task)
        delay(1500L)

        schedulerJob.cancel()
        schedulerJob.join()

        assertThat(taskExecutionCount).isCloseTo(3, within(1))
    }

    @Test
    fun `runWithTimeout throws TimeoutCancellationException if task exceeds timeout`(): Unit = runBlocking {
        val task: suspend () -> Unit = { delay(2000L) }
        assertThrows<TimeoutCancellationException> {
            runWithTimeout(timeout = 1000L, task = task)
        }
    }

}