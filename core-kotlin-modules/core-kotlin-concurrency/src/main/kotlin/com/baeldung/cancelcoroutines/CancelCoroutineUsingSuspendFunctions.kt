package com.baeldung.cancelcoroutines

import kotlinx.coroutines.*

fun main() {
    cancelUsingDelay()
    cancelUsingYield()
    cancelByActiveCheck()
    cancelUsingEnsureActive()
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

private fun cancelByActiveCheck() = runBlocking {
    val jobWithActiveCheck = launch(Dispatchers.Default) {
        while (isActive) {
            println("Executing the job with active check")
        }
    }

    delay(1L)
    println("Let's cancel the job")
    jobWithActiveCheck.cancelAndJoin()
    println("Done with cancellation")
}

private fun cancelUsingEnsureActive() = runBlocking {
    val jobWithEnsureActive = launch(Dispatchers.Default) {
        while (true) {
            ensureActive()
            println("Executing the job with ensureActive")
        }
    }

    delay(1L)
    println("Let's cancel the job")
    jobWithEnsureActive.cancelAndJoin()
    println("Done with cancellation")
}