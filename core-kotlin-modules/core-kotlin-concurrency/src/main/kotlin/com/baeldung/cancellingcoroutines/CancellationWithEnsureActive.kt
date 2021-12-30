package com.baeldung.cancellingcoroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val job = launch(Dispatchers.Default) {
            println("Started coroutine")
            var i = 0
            while (i < 10) {
                ensureActive()
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
