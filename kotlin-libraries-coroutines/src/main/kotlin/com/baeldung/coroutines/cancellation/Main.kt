package com.baeldung.coroutines.cancellation

import kotlinx.coroutines.*

fun main() {
    println("cancellation")
    println("------------")
    simple()
    println()

    println("cleaning up")
    println("-----------")
    exception()
    println()

    println("timeout")
    println("-------")
    timeout()
}

fun simple() = runBlocking {
    val job = launch {
        repeat(100) { i ->
            println("job: iteration: #$i")
            delay(400L)
        }
    }

    delay(1500L)

    println("main: cancelling")
    job.cancel()
    job.join()

    println("main: exiting")
}

fun exception() = runBlocking {
    val job = launch {
        try {
            repeat(100) { i ->
                println("job: iteration: #$i")
                delay(400L)
            }
        } catch (e: CancellationException) {
            println("job: $e.message")
        } finally {
            println("job: cleaning up")
        }
    }

    delay(1500L)

    println("main: cancelling")
    job.cancel()
    job.join()

    println("main: exiting")
}

fun timeout() = runBlocking {
    withTimeout(1500L) {
        repeat(100) { i ->
            println("job: iteration: #$i")
            delay(400L)
        }
    }

    println("main: exiting")
}