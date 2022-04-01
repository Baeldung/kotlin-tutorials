package com.baeldung.cancelcoroutines

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    val job = launch {
        try {
            repeat(100) {
                println(it)
                delay(50L)
            }
        } catch (e: CancellationException) {
            println(e)
        } finally {
            println("close resource")
        }
    }
    delay(130)
    job.cancelAndJoin()
}