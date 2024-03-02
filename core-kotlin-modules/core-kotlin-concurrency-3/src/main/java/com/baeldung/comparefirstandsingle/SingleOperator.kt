package com.baeldung.comparefirstandsingle

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val singleValueFlow = flowOf(42,43,44)
    val singleValue = singleValueFlow.single()
    println(singleValue)
}
