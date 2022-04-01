package com.baeldung.cancelcoroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main() = runBlocking {
    val result = withTimeoutOrNull(130) {
        repeat(100) {
            println(it)
            delay(50)
        }
        1
    }
    println("result = $result")
}