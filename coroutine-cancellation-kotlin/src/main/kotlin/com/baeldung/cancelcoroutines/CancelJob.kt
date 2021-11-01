package com.baeldung.cancelcoroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        println("Start job 1")
        delay(2000)
        println("End job 1")
    }
    delay(1000L)
    job.cancel()
    job.join()
    assert(job.isCompleted)
}