package com.baeldung.runblockingvscoroutinescope

import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis


private val context = Executors.newFixedThreadPool(2).asCoroutineDispatcher()


private fun demoWithRunBlocking() = runBlocking {
    (1..10).forEach {
        launch(context) {
            runBlocking {
                println("Start No.$it in runBlocking on ${Thread.currentThread().name}")
                delay(500)
                println("End No.$it in runBlocking on ${Thread.currentThread().name}")
            }
        }
    }
}

private fun demoWithCoroutineScope() = runBlocking {
    (1..10).forEach {
        launch(context) {
            coroutineScope {
                println("Start No.$it in coroutineScope on ${Thread.currentThread().name}")
                delay(500)
                println("End No.$it in coroutineScope on ${Thread.currentThread().name}")
            }
        }
    }
}

fun main() {
    val runBlockingTimeInMills = measureTimeMillis {
        demoWithRunBlocking()
    }
    println("runBlockingTimeInMills = $runBlockingTimeInMills")

    val coroutineScopeTimeInMills = measureTimeMillis {
        demoWithCoroutineScope()
    }
    println("coroutineScopeTimeInMills = $coroutineScopeTimeInMills")

    context.close()
}
