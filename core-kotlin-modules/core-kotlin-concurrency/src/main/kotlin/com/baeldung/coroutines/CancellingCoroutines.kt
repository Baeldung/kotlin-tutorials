package com.baeldung.coroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
    printPagesPeriodicCheck(100)
    printPagesEnsureActive(100)
    printPagesIsActive(100)
}

private suspend fun CoroutineScope.printPagesPeriodicCheck(count: Int) {
    println("This function uses yield to periodically check if Job is cancelled.")
    val job = launch {
        repeat(count) {
            yield()
            try {
                println("Printing page number $it")
                delay(1000L)
                println("Page $it printed")
            } catch (e: CancellationException) {
                println("Print job cancel triggered")
            }
        }
    }
    delay(2000L)
    job.cancelAndJoin()
    println("Print job cancelled")
}

private suspend fun CoroutineScope.printPagesEnsureActive(count: Int) {
    println("This function uses ensureActive function call to check if Job is active.")
    val job = launch {
        repeat(count) {
            ensureActive()
            try {
                println("Printing page number $it")
                delay(1000L)
                println("Page $it printed")
            } catch (e: CancellationException) {
                println("Print job cancel triggered")
            }
        }
    }
    job.start()
    delay(2000L)
    job.cancelAndJoin()
    println("Print job cancelled")
}

private suspend fun CoroutineScope.printPagesIsActive(count: Int) {
    println("This function uses isActive field to check if Job is active.")
    val job = launch {
        repeat(count) {
            if (isActive) {
                try {
                    println("Printing page number $it")
                    delay(1000L)
                    println("Page $it printed")
                } catch (e: CancellationException) {
                    println("Print job cancel triggered")
                }
            }
        }
    }
    job.start()
    delay(2000L)
    job.cancelAndJoin()
    println("Print job cancelled")
}