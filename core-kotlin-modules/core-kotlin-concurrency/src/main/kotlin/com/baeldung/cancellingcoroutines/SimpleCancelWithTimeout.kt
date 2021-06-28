package com.baeldung.cancellingcoroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch(Dispatchers.Default) {
        withTimeout(60) {
            for (i in 1..5) {
                println("This loop ran $i times")
                delay(40)
            }
        }
    }
    delay(100)
    println("Cancelling job")
    job.cancel()
    println("Cancelled job")
}
