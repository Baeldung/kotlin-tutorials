package com.baeldung.cancelcoroutines

import kotlinx.coroutines.*

fun main() {
    cancelUsingDelay()
    cancelUsingYield()
}

private fun cancelUsingDelay() = runBlocking {
    val jobWithDelay = launch(Dispatchers.Default) {
        while (true) {
            println("Executing the job with delay")
            delay(100L)
        }
    }

    delay(500L)
    println("Let's cancel the job")
    jobWithDelay.cancel()
    jobWithDelay.join()
    println("Done with cancellation")
}

private fun cancelUsingYield() = runBlocking {
    val jobWithYield = launch {
        while (true) {
            println("Executing the job with yield")
            yield()
        }
    }

    delay(1L)
    println("Let's cancel the job")
    jobWithYield.cancel()
    jobWithYield.join()
    println("Done with cancellation")
}