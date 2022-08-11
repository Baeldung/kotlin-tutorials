package com.baeldung.coroutines.cancellation

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        repeat(1000) { i ->
            println("Working on task #$i ...")
            delay(400L)
        }
    }
    delay(1100L)
    println("Cancel work")
    job.cancel()
    println("Done")
}
