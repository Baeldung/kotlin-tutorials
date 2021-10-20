package com.baeldung.cancellationofcoroutines

import kotlinx.coroutines.*

fun main() {
    //callCancel()
    //cancelWithTimeOut()
    //cooperativeCoroutines()
    //cancelJobs()
}

fun callCancel() = runBlocking {
    val job = launch {
        repeat(1000) { index ->
            println("$index. Printing numbers...")
            delay(500L)
        }
    }
    delay(1300L) // adding delay
    println("main: Waiting!!!")
    job.cancel() // cancels the job
    job.join() // waits for job’s completion
    println("main: Now I can quit.")
}

fun cancelJobs() = runBlocking {
    val parentJob = launch {
        val childJob = launch {
            var count = 1
            val startTime = System.currentTimeMillis()
            var nextPrintTime = startTime
            while (count <= 5) {
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("Count: $count")
                    nextPrintTime += 100L
                    count++
                }
            }
        }
    }

    delay(250)
    println("Cancelling parent job")
    parentJob.cancel()

    parentJob.join()
    println("Parent job completed")
}

fun cancelWithTimeOut() = runBlocking {
    withTimeout(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
}

fun cooperativeCoroutines() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) { // computation loop, just wastes CPU
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L)
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}
