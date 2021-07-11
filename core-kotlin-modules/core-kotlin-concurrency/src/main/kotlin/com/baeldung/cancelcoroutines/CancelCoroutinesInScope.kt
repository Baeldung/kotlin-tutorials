package com.baeldung.cancelcoroutines

import kotlinx.coroutines.*

fun main() {
    val scope = MainScope()
    runBlocking {
        scope.launch(Dispatchers.Default) {
            while (true) {
                println("Job 1")
                delay(100L)
            }
        }

        scope.launch(Dispatchers.Default) {
            while (true) {
                println("Job 2")
                delay(100L)
            }
        }

        delay(500L)
        println("Let's cancel the scope")
        scope.cancel()
        println("Done with cancellation")
    }
}

