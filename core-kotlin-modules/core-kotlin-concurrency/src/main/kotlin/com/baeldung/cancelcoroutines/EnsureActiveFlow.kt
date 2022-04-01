package com.baeldung.cancelcoroutines

import kotlinx.coroutines.cancel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    (1..5).asFlow()
        .onEach { ensureActive() }
        .collect {
            if (it == 3) cancel()
            println("collected $it")
        }
}