package com.baeldung.stateflow

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val mutableStateFlow = MutableStateFlow("Initial Value")
    val job = launch {
        mutableStateFlow.collect { value ->
            println("Received value: $value")
        }
    }
    mutableStateFlow.value = "Updated Value 1"
    mutableStateFlow.value = "Updated Value 2"
    job.cancel()
}