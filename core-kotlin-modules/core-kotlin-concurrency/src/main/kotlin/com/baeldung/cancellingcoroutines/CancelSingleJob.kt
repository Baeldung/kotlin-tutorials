package com.baeldung.cancellingcoroutines

import kotlinx.coroutines.*

fun main() : Unit = runBlocking {
    val job = launch {
            repeat(10) { i ->
                delay(300L)
                println("Fetching album no.$i")
            }
    }

    delay(1000L)
    println("Canceling fetching albums job")
    job.cancel()
    job.join()
    println("Fetching albums job completed")
}


