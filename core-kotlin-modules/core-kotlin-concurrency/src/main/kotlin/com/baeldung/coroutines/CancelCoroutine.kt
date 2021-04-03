package com.baeldung.coroutines

import kotlinx.coroutines.*

fun cancelCoroutine() = runBlocking {
    val job = launch {
        repeat(10000) { i ->
            println("job: Hello World! $i ...")
            delay(500L)
        }
    }
    delay(1000L)
    println("main: Let us cancel this job")
    job.cancelAndJoin()
    println("main: Job is canceled.")
}

fun uncancelledCoroutine() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var subsequentTime = startTime
        var i = 0
        while (i < 7) {
            if (System.currentTimeMillis() >= subsequentTime) {
                println("job: Sleepy World ${i++} ...")
                subsequentTime += 500L
            }
        }
    }
    delay(1000L)
    println("main: Let us cancel this job!")
    job.cancelAndJoin()
    println("main: Job is canceled.")
}

fun cancelComputationCode() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var subsequentTime = startTime
        var i = 0
        while (isActive) {
            if (System.currentTimeMillis() >= subsequentTime) {
                println("job: Sleepy World ${i++} ...")
                subsequentTime += 500L
            }
        }
    }
    delay(1000L)
    println("main: Let us cancel this job!")
    job.cancelAndJoin()
    println("main: Job is canceled.")
}

fun cancelUsingTimeOut() = runBlocking {
    withTimeout(500L) {
        repeat(100) { i ->
            println("Sleepy World $i ...")
            delay(50L)
        }
    }
}


fun main() {
    cancelCoroutine()
    uncancelledCoroutine()
    cancelComputationCode()
    cancelUsingTimeOut()
}


