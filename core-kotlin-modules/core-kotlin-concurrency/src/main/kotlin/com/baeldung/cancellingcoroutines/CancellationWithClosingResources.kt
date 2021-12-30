package com.baeldung.cancellingcoroutines

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val job = launch {
            try {
                println("Started coroutine")
                delay(1000)
                println("Finished coroutine")
            } catch (e: CancellationException) {
                println("Caught CancellationException")
            } finally {
                println("Closing resources")
            }
        }
        delay(100)
        job.cancelAndJoin()
        println("Finished Block")
    }
}