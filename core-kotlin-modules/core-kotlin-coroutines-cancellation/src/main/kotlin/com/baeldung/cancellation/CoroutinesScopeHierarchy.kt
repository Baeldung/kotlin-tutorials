package com.baeldung.cancellation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    val parentJob = Job()
    val scope = CoroutineScope(parentJob)
    /*
    *                   Parent Job(Scope Job)
    *                           |
    *                           |
    * ---------------------------------------------------------
    * |                         |                             |
    * Job 1                    Job 2                        Job 3
    *  x                         ✓                             ✓
    * */

    val job1 = scope.launch {
        delay(500)
    }

    val job2 = scope.launch {
        delay(600)
    }

    val job3 = scope.launch {
        delay(700)
    }

    job1.cancel()
    println("[JOB1] is active: ${job1.isActive}")
    println("[JOB2] is active: ${job2.isActive}")
    println("[JOB3] is active: ${job3.isActive}")

}