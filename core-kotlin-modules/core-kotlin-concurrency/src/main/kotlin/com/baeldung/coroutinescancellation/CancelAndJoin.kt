package com.baeldung.coroutinescancellation

import kotlinx.coroutines.*

fun main() {
    val job = GlobalScope.launch(Dispatchers.Default) {
        longRunningTask()
    }
    GlobalScope.launch(Dispatchers.Default) {
        job.cancelAndJoin()
        println("This is printed after the cancelled job ends")
    }
}

suspend fun longRunningTask() = withContext(Dispatchers.Default) {
    try {
        delay(500)
    } catch (e: CancellationException) {
        withContext(NonCancellable) {
            println("Job is cancelled, but i need more time.")
            delay(2500)
        }
    }
}
