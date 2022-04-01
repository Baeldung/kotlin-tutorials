package com.baeldung.cancelcoroutines

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.delay

fun main() = runBlocking {
    val job = launch {
        repeat(100) {
            println(it)
            delay(50)
        }
    }
    delay(300)
    job.cancel()
    job.join()
}