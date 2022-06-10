package com.baeldung.coroutines

import kotlinx.coroutines.*

suspend fun main(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        try {
            repeat(1000) { i ->
                delay(50)
                println("$i")
            }
        } catch (e: CancellationException) {
            println(e)
        } finally {
            println("Finally")
        }
    }
    delay(500)
    job.cancel()
    job.join()
    println("Cancelled successfully")

    CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable)
    }
}