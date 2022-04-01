package com.baeldung.cancelcoroutines

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    val parent = launch {
        repeat(2) { child ->
            launch {
                repeat(100) {
                    println("[child-$child] $it")
                    delay(50)
                }
            }
        }
        repeat(100) {
            println("[parent] $it")
            delay(50)
        }
    }
    delay(200)
    parent.cancelAndJoin()
}