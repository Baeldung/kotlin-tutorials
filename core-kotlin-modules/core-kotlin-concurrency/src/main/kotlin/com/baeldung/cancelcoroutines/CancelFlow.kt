package com.baeldung.cancelcoroutines

import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

private fun intFlow(): Flow<Int> = flow {
    for (i in 1..5) {
        delay(100)
        println("Emitting $i")
        emit(i)
    }
}

fun main() = runBlocking {
    intFlow().collect {
        if (it == 3) cancel()
        println("collected $it")
    }
}