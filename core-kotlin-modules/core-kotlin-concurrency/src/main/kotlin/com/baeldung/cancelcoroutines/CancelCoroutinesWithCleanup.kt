package com.baeldung.cancelcoroutines

import kotlinx.coroutines.*

fun main() {
    cleanupWithNoSuspendFunction()
    cleanupWithSuspendFunction()
}

private fun cleanupWithNoSuspendFunction() {
    runBlocking {
        val jobWithDelay = launch(Dispatchers.Default) {
            try {
                while (true) {
                    println("Executing the job with delay")
                    delay(100L)
                }
            } catch (exception: CancellationException) {
                println("I am cancelled")
            } finally {
                println("Cleaning up")
            }
        }

        delay(500L)
        println("Let's cancel the job")
        jobWithDelay.cancel()
        jobWithDelay.join()
        println("Done with cancellation")
    }
}

private fun cleanupWithSuspendFunction() {
    runBlocking {
        val jobWithDelay = launch(Dispatchers.Default) {
            try {
                while (true) {
                    println("Executing the job with delay")
                    delay(100L)
                }
            } catch (exception: CancellationException) {
                println("I am cancelled")
            } finally {
                withContext(NonCancellable) {
                    delay(100L)
                    println("Cleaning up")
                }
            }
        }

        delay(500L)
        println("Let's cancel the job")
        jobWithDelay.cancel()
        jobWithDelay.join()
        println("Done with cancellation")
    }
}