package com.baeldung.coroutines.cancellation

import kotlinx.coroutines.*

fun main() = runBlocking {
    val start = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var next = start
        var i = 0
        while (i < 5) {
            if (System.currentTimeMillis() >= next) {
                println("Working on task #${i++} ...")
                next += 400L
            }
        }
    }
    delay(1100L)
    println("Cancel work")
    job.cancel()
    job.join()
    println("Done")
}
