package com.baeldung.mergemultipleflows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FlowMergingTests {

    @Test
    fun `should merge using the merge function`(): Unit = runBlocking {
        val flow1 = flowOf(1, 2, 3, 4)
        val flow2 = flowOf(5, 6, 7, 8)
        val mergedFlow = merge(flow1, flow2)
        val result = mergedFlow.toList()

        assertThat(result).containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6, 7, 8)
    }

    @Test
    fun `should merge using zip`() = runBlocking {
        val flow1 = flowOf(1, 2, 3)
        val flow2 = flowOf("A", "B", "C")

        val result = flow1.zip(flow2) { num, letter ->
            "$num$letter"
        }.toList()

        assertEquals(listOf("1A", "2B", "3C"), result)
    }


    @Test
    fun `should merge using combine`() = runBlocking {
        val flow1 = flowOf(0)
        val flow2 = flowOf(1, 2, 3)
        val result = flow1.combine(flow2) { num1, num2 ->
            num1 + num2
        }.toList()
        assertEquals(listOf(1, 2, 3), result)
    }



    @Test
    fun `should merge using flatmapconcat`() = runBlocking {
        val flow1 = flowOf(1, 2, 3)
        val flow2 = flowOf(4, 5, 6)
        val result = flow1.flatMapConcat { value1 ->
            flow2.map { value2 -> value1 + value2 }
        }.toList()
        assertEquals(listOf(5, 6, 7, 6, 7, 8, 7, 8, 9), result)
    }


    @Test
    fun `should merge using flatmerge`() = runBlocking {
        val flow1 = flowOf(1, 2, 3)
        val flow2 = flowOf(4, 5, 6)
        val result = flow1.flatMapMerge { value1 ->
            flow2.map { value2 -> value1 + value2 }
        }.toList()
        assertEquals(listOf(5, 6, 7, 6, 7, 8, 7, 8, 9), result)
    }


    @Test
    fun `should merge using runningReduce`() = runBlocking {
        val result = flowOf(1, 2, 3, 4)
                .runningReduce { acc, value -> acc + value }
                .toList()
        assertEquals(listOf(1, 3, 6, 10), result)
    }
}
