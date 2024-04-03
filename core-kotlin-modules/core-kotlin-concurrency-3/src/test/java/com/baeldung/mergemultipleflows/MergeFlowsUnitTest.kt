package com.baeldung.mergemultipleflows

import junit.framework.Assert
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FlowMergingTests {

    @Test
    fun testMerge() = runBlocking {
        val flow1 = flowOf(1, 2, 3, 4)
        val flow2 = flowOf(5, 6, 7, 8)
        val mergedFlow = merge(flow1, flow2)
        val result = mutableListOf<Int>()
        mergedFlow.collect { value ->
            result.add(value)
        }
        assertEquals(setOf(1, 2, 3, 4, 5, 6, 7, 8), result.toSet())
    }

    @Test
    fun testZip() = runBlocking {
        val flow1 = flowOf(1, 2, 3)
        val flow2 = flowOf("A", "B", "C")
        val result = mutableListOf<String>()
        flow1.zip(flow2) { num, letter ->
            "$num$letter"
        }.collect { mergedValue ->
            result.add(mergedValue)
        }
        assertEquals(listOf("1A", "2B", "3C"), result)
    }

    @Test
    fun testCombine() = runBlocking {
        val flow1 = MutableStateFlow(0)
        val flow2 = flowOf(1, 2, 3)
        val result = mutableListOf<Int>()
        flow1.combine(flow2) { num1, num2 ->
            num1 + num2
        }.collect { mergedValue ->
            result.add(mergedValue)
        }
        assertEquals(listOf(1, 2, 3, 4, 5, 6), result)
    }

    @Test
    fun testFlatMapConcat() = runBlocking {
        val flow1 = flowOf(1, 2, 3)
        val flow2 = flowOf(4, 5, 6)
        val result = mutableListOf<Int>()
        flow1.flatMapConcat { value1 ->
            flow2.map { value2 -> value1 + value2 }
        }.collect { mergedValue ->
            result.add(mergedValue)
        }
        assertEquals(listOf(5, 6, 7, 6, 7, 8, 7, 8, 9), result)
    }

    @Test
    fun testFlatMapMerge() = runBlocking {
        val flow1 = flowOf(1, 2, 3)
        val flow2 = flowOf(4, 5, 6)
        val result = mutableListOf<Int>()
        flow1.flatMapMerge { value1 ->
            flow2.map { value2 -> value1 + value2 }
        }.collect { mergedValue ->
            result.add(mergedValue)
        }
        assertEquals(listOf(5, 6, 7, 6, 7, 8, 7, 8, 9), result)
    }

    @Test
    fun testRunningReduce() = runBlocking {
        val result = flowOf(1, 2, 3, 4)
                .runningReduce { acc, value -> acc + value }
                .toList()
        assertEquals(listOf(1, 3, 6, 10), result)
    }

    @Test
    fun testRunningFold() = runBlocking {
        val flow1 = flowOf(1, 2, 3)
        val flow2 = flowOf(4, 5, 6)
        val mergedFlow = flow1.zip(flow2) { a, b -> a + b }
                .runningFold(emptyList<Int>()) { acc, value -> acc + value }
                .toList()
                .last()
        Assert.assertEquals(listOf(5, 7, 9), mergedFlow)
    }
}
