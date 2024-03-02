package com.baeldung.comparefirstandsingle

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val multipleValuesFlow = flowOf(1, 2, 3)
    val firstValue = multipleValuesFlow.first()
    println(firstValue)
}
