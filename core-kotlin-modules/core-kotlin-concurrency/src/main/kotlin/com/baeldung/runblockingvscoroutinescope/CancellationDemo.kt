package com.baeldung.runblockingvscoroutinescope

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val cancelCoroutineScopeTime = measureTimeMillis {
        cancelCoroutineScope()
    }
    println("cancelCoroutineScopeTime = $cancelCoroutineScopeTime")

    val cancelRunBlockingTime = measureTimeMillis {
        cancelRunBlocking()
    }
    println("cancelRunBlockingTime = $cancelRunBlockingTime")
}

private fun cancelCoroutineScope() = runBlocking {
    val job = launch {
        coroutineScope {
            println("Start coroutineScope...")
            delay(500)
            println("End coroutineScope...")
        }
    }
    delay(100)
    job.cancel()
    job.join()
}

private fun cancelRunBlocking() = runBlocking {
    val job = launch {
        runBlocking {
            println("Start runBlocking...")
            delay(500)
            println("End runBlocking...")
        }
    }
    delay(100)
    job.cancel()
    job.join()
}
