package com.baeldung.cancelcoroutines

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

private fun intFlow(): Flow<Int> = flow {
    for (i in 1..5) {
        delay(100)
        println("Emitting $i")
        emit(i)
    }
}

fun main() = runBlocking {
    try {
        withTimeout(250) {
            intFlow().collect { println("collected $it") }
        }
    } catch (e: TimeoutCancellationException) {
        println(e)
    }

    val result = withTimeoutOrNull(250) {
        intFlow().collect { println("collected $it") }
    }

    println("result = $result")
}