package com.baeldung.coroutine.timeouts

import kotlinx.coroutines.*
import java.util.concurrent.Executors

class TimeoutsDemo(
    executor: CoroutineScope = CoroutineScope(Executors.newSingleThreadExecutor().asCoroutineDispatcher())
) : CoroutineScope by executor {

    suspend fun performTask(): String {
        delay(2000) // Simulate a long-running task
        return "Task completed successfully!"
    }

    fun withTimeoutDemo(): String = runBlocking {
        val result = withTimeout(1000) {
            performTask()
        }
        result
    }

    fun withTimeoutOrNullDemo(): String? = runBlocking {
        withTimeoutOrNull(1000) {
            performTask()
        }
    }

    fun usingExceptionHandlingDemo(): String = runBlocking {
        try {
            val result = withTimeout(1000) {
                performTask()
            }
            result
        } catch (ex: TimeoutCancellationException) {
            println("Task timed out!")
            val result = withTimeout(2500) {
                performTask()
            }
            "Retrying... $result"
        }
    }

    fun usingNullHandlingDemo() = runBlocking {
        val result = withTimeoutOrNull(1000) {
            performTask()
        }
        if (result == null) {
            // Handle the timeout case here
            "Task timed out with null!"
        } else {
            // Handle the successful completion case here
            result
        }
    }
}
