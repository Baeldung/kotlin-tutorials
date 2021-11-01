package com.baeldung.cancelcoroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val defferedJob = async {
        println("Start job 1")
        delay(2000)
        println("End job 1")
        1
    }
    delay(1000L)
    defferedJob.cancel()
    //defferedJob.await()// Will throw a jobCancellationException
}