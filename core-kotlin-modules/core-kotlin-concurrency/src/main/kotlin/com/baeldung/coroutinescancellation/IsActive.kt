package com.baeldung.coroutinescancellation

import kotlinx.coroutines.*

fun main() {
    val job = GlobalScope.launch(Dispatchers.Default) {
        while (true) {
            if (isActive) {
                println("the job is still running")
                delay(500L)
            } else {
                println("the job is not active. Quitting!")
            }
        }
    }

    GlobalScope.launch(Dispatchers.Default) {
        delay(1300L)
        job.cancel()
    }
}
