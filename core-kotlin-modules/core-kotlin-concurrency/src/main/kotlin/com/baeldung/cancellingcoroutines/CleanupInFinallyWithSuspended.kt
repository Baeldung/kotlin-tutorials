package com.baeldung.cancellingcoroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch(Dispatchers.Default) {
        try {
            somethingElseLongRunning()
        } finally {
            withContext(NonCancellable) {
                println("Dusting the memory disks")
                delay(20)
                println("Finished dusting")
            }
        }
    }
    delay(100)
    println("Cancelling job")
    job.cancel()
    println("Cancelled job")
}

suspend fun somethingElseLongRunning() {
    for (i in 1..50) {
        delay(40)
        println("This loop ran $i times")
    }
}
