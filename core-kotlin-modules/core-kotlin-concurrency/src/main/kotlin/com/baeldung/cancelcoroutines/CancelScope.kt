package com.baeldung.cancelcoroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    val scope = CoroutineScope(Dispatchers.Default)
    val parent = scope.launch {
        repeat(2) { child ->
            scope.launch {
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
    scope.cancel()
    parent.join()
}