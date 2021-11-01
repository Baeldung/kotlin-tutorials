package com.baeldung.cancelcoroutines

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val parentJob = launch {
        launch {
            println("Start child 1")
            delay(2000)
            println("End child 1")
        }
        launch {
            println("Start child 2")
            delay(2000)
            println("End child 2")
        }
    }
    delay(1000)
    parentJob.cancelAndJoin()
}