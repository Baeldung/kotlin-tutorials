package com.baeldung.cancelcoroutines

import kotlinx.coroutines.*

fun main() {
    cancelWithTimeoutException()
    cancelWithoutException()
}

private fun cancelWithTimeoutException() {
    runBlocking {
        try {
            withTimeout(1000L) {
                while (true) {
                    println("Executing the job with delay")
                    delay(100L)
                }
            }
        } catch (exception: TimeoutCancellationException) {
            println("I am cancelled")
        }
    }
}

private fun cancelWithoutException() {
    runBlocking {
        withTimeoutOrNull(1000L) {
            try {
                while (true) {
                    println("Executing the job with delay")
                    delay(100L)
                }
            } catch (exception: TimeoutCancellationException) {
                println("I am cancelled")
            } finally {
                println("No exception thrown here")
            }
        }
    }
}
