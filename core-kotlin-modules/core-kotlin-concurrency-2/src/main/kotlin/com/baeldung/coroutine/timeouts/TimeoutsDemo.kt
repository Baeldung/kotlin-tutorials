package com.baeldung.coroutine.timeouts

import kotlinx.coroutines.*
import java.util.concurrent.Executors

class TimeoutsDemo(
    executor: CoroutineScope = CoroutineScope(Executors.newSingleThreadExecutor().asCoroutineDispatcher())
) : CoroutineScope by executor {

    private suspend fun performTask(): String {
        delay(2000) // Simulate a long-running task
        return "Task completed successfully!"
    }

    fun withTimeoutDemo() = runBlocking {
        try {
            val result = withTimeout(1000) {
                performTask()
            }
            println(result)
        } catch (ex: TimeoutCancellationException) {
            println("Task timed out!")
        }
    }

    fun withTimeoutOrNullDemo() = runBlocking {
        val result = withTimeoutOrNull(1000) {
            performTask()
        }
        if (result == null) {
            println("Task timed out!")
        } else {
            println(result)
        }
    }

    fun usingExceptionHandlingDemo() = runBlocking {
        try {
            val result = withTimeout(1000) {
                performTask()
            }
            println(result)
        } catch (ex: TimeoutCancellationException) {
            println("Task timed out! Retrying...")
            val result = withTimeout(2500) {
                performTask()
            }
            println(result)
        }
    }

    fun usingNullHandlingDemo() = runBlocking {
        val result = withTimeoutOrNull(1000) {
            performTask()
        }
        if (result == null) {
            // Handle the timeout case here
            println("Task timed out!")
        } else {
            // Handle the successful completion case here
            println(result)
        }
    }
}