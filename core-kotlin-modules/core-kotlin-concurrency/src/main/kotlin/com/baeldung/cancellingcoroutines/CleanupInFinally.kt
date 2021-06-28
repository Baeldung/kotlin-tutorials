package com.baeldung.cancellingcoroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch(Dispatchers.Default) {
        try {
            somethingLongRunning()
        } finally {
            println("Dusting the memory disks")
        }
    }
    delay(100)
    println("Cancelling job")
    job.cancel()
    println("Cancelled job")
}

suspend fun somethingLongRunning() {
    for (i in 1..50) {
        delay(40)
        println("This loop ran $i times")
    }
}
