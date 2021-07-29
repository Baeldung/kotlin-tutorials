package com.baeldung.coroutinescancellation

import kotlinx.coroutines.*

fun main() {
    val job = GlobalScope.launch(Dispatchers.Default) {
        try {
            while (true) {
                println("the job is still running")
                delay(500L)
                yield()
            }
        } catch (e: CancellationException) {
            println("the job was cancelled")
            // handle cancellation here
        }
    }

    GlobalScope.launch(Dispatchers.Default) {
        delay(1300)
        job.cancel()
    }
}
