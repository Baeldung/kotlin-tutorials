package com.baeldung.cancelcoroutines

import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    (1..5).asFlow()
        .cancellable()
        .collect {
            if (it == 3) cancel()
            println("collected $it")
        }
}