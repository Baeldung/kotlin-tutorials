package com.baeldung.cancelcoroutines

import kotlinx.coroutines.*

fun main() {
    cancelByActiveCheck()
    cancelUsingEnsureActive()
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