package com.baeldung.comparecollectandcollectlatest

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        flowOf(1, 2, 3).collectLatest { value ->
            println(value)
        }
    }
}

