package com.baeldung.coroutines.cancellation

import kotlinx.coroutines.*

fun main() = runBlocking {
    val scope = CoroutineScope(Dispatchers.Default).launch {
        val job1 = launch {
            repeat(1000) { i ->
                println("Job #1: Working on task #$i ...")
                delay(400L)
            }
        }
        val job2 = launch {
            repeat(1000) { i ->
                println("Job #2: Working on task #$i ...")
                delay(600L)
            }
        }
    }
    delay(1100L)
    println("Cancel work")
    scope.cancel()
    println("Done")
}
