package com.baeldung.combiningflows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

// Sample Flow Creation
suspend fun sampleFlow1(): Flow<Int> = flow {
    repeat(3) {
        delay(1000)
        emit(it)
    }
}

suspend fun sampleFlow2(): Flow<Int> = flow {
    repeat(3) {
        delay(1500)
        emit(it * it)
    }
}

// Method 1: zip
suspend fun method1() {
    val combinedFlow = sampleFlow1().zip(sampleFlow2()) { first, second ->
        "($first, $second)"
    }
    combinedFlow.collect { println(it) }
}

// Method 2: combine
suspend fun method2() {
    val combinedFlow = sampleFlow1().combine(sampleFlow2()) { first, second ->
        "($first, $second)"
    }
    combinedFlow.collect { println(it) }
}

// Method 3: flatMapConcat
suspend fun method3() {
    val combinedFlow = sampleFlow1().flatMapConcat { value1 ->
        sampleFlow2().map { value2 ->
            "($value1, $value2)"
        }
    }
    combinedFlow.collect { println(it) }
}

// Method 4: flatMapMerge
suspend fun method4() {
    val combinedFlow = sampleFlow1().flatMapMerge { value1 ->
        sampleFlow2().map { value2 ->
            "($value1, $value2)"
        }
    }
    combinedFlow.collect { println(it) }
}

// Method 5: flattenConcat
suspend fun method5() {
    val combinedFlow = flowOf(sampleFlow1(), sampleFlow2()).flattenConcat()
    combinedFlow.collect { println(it) }
}

// Method 6: merge
suspend fun method6() {
    val combinedFlow = merge(sampleFlow1(), sampleFlow2())
    combinedFlow.collect { println(it) }
}

fun main() = runBlocking {
    println("Method 1: zip")
    method1()

    println("\nMethod 2: combine")
    method2()

    println("\nMethod 3: flatMapConcat")
    method3()

    println("\nMethod 4: flatMapMerge")
    method4()

    println("\nMethod 5: flattenConcat")
    method5()

    println("\nMethod 6: merge")
    method6()
}