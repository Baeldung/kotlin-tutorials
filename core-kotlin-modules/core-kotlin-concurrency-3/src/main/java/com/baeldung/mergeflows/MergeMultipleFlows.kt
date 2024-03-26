package com.baeldung.mergeflows

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun mergeFlowsSequentially() = runBlocking {
    val flow1 = flowOf(1, 2, 3)
    val flow2 = flowOf(4, 5, 6)
    flow1.flatMapConcat { value1 ->
        flow2.map { value2 -> value1 + value2 }
    }.collect { result ->
        println("Result: $result")
    }
}


fun mergeFlowsConcurrently() = runBlocking {
    val flow1 = flowOf(1, 2, 3)
    val flow2 = flowOf(4, 5, 6)
    flow1.flatMapMerge { value1 ->
        flow2.map { value2 -> value1 + value2 }
    }.collect { result ->
        println("Result: $result")
    }
}

fun mergeFlowsWithZip() = runBlocking {
    val flow1 = flowOf(1, 2, 3)
    val flow2 = flowOf("A", "B", "C")
    flow1.zip(flow2) { num, letter ->
        "$num$letter"
    }.collect { result ->
        println("Result: $result")
    }
}


fun mergeFlowsWithCombine() = runBlocking {
    val flow1 = MutableStateFlow(0)
    val flow2 = flowOf(1, 2, 3)
    flow1.combine(flow2) { num1, num2 ->
        num1 + num2
    }.collect { result ->
        println("Result: $result")
    }
    flow1.value = 10
}
