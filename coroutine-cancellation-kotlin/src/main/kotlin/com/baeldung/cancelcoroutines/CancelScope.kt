package com.baeldung.cancelcoroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
    val scope = CoroutineScope(Job() + Dispatchers.Default)
    scope.launch {
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
    scope.cancel()
}