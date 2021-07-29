package com.baeldung.coroutinescancellation

import kotlinx.coroutines.*

fun main() {
    val job = GlobalScope.launch(Dispatchers.Default) {
        longRunningTask()
        println("This will not get printed")
    }
    job.cancel()
    println("This is printed immediately")
}

suspend fun longRunningTask() = withContext(Dispatchers.Default) {
    delay(500L)
}
