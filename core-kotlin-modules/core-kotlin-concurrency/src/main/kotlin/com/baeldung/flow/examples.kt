package com.baeldung.flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow

fun createMinimalFlow() {
    val flowWithSingleValue = flow { emit(0) }
    println("flowWithSingleValue is: $flowWithSingleValue")
}

fun transformListIntoAFlow() {
    val flowFromList = listOf("mene", "mene", "tekel", "upharsin").asFlow()
    println("flowFromList is: $flowFromList")
}
