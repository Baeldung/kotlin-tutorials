package com.baeldung.cancelcoroutines

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

fun main() = runBlocking {
    try {
        withTimeout(130) {
            repeat(100) {
                println(it)
                delay(50)
            }
        }
    } catch (e: TimeoutCancellationException) {
        println(e)
    }
}