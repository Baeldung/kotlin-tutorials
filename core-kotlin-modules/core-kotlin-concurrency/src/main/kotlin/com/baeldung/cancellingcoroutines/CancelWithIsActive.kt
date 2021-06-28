package com.baeldung.cancellingcoroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch(Dispatchers.Default) {
        for (i in 1..5) {
            Thread.sleep(40)
            if (!isActive) break
            println("This loop ran $i times")
        }
        println("Cleanup can happen here")
    }
    delay(100)
    job.cancelAndJoin()
    println("Cancelled job")
}
