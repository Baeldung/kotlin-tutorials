package com.baeldung.cancelcoroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) {
            yield()
            if (System.currentTimeMillis() >= nextPrintTime) {
                println(i++)
                nextPrintTime += 50L
            }
        }
    }
    delay(100)
    job.cancelAndJoin()
}