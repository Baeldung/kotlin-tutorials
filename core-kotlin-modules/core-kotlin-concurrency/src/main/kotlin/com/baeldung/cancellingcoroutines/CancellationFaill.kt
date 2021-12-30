package com.baeldung.cancellingcoroutines

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val job = launch {
            println("Started coroutine")
            var i = 0
            while (i < 5) {
                intensiveComputation()
                println("Looping ${++i}")
            }
            println("Finished coroutine")
        }
        delay(100)
        job.cancelAndJoin()
        println("Finished Block")
    }
}
