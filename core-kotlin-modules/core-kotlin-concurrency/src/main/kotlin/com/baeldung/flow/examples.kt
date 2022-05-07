package com.baeldung.flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow

fun createMinimalFlow() {
    val flowWithSingleValue = flow { emit(0) }
}

fun transformListIntoAFlow() {
    val flowFromList = listOf("mene", "mene", "tekel", "upharsin").asFlow()
}
