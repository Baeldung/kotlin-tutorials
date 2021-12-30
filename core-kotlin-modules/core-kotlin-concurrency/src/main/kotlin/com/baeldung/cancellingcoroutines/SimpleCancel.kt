package com.baeldung.cancellingcoroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val job = launch {
            println("Started coroutine")
            delay(1000)
            println("Finished coroutine")
        }
        delay(100)
        job.cancel()
        job.join()
        println("Finished Block")
    }
}