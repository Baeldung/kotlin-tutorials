package com.baeldung.cancellingcoroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch(Dispatchers.Default) {
        for (i in 1..5) {
            Thread.sleep(40)
            println("This loop ran $i times")
        }
    }
    delay(100)
    job.cancel()
    println("Cancelled job")
}
