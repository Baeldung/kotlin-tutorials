package com.baeldung.cancellation

import kotlinx.coroutines.*

fun main() = runBlocking {
    checkJobWithCompleteState()
    checkJobWithCancelState()
}

fun checkJobWithCompleteState() = runBlocking {
    val job1 = launch {
        // doing some staff
        delay(500)
    }
    delay(1000)
    println("[JOB1] isCompleted state is ${job1.isCompleted}")
}


fun checkJobWithCancelState() = runBlocking{
    val job2 = launch  {
        // doing some staff
        delay(2000L)
    }
    delay(1000L)
    // Cancel while executing job2
    job2.cancel()
    println("[JOB2] isCancelled state is ${job2.isCancelled}")
    delay(1000L)
    println("[JOB2] isCompleted state is ${job2.isCompleted}")

}