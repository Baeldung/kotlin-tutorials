package com.baeldung.flowToList

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.CopyOnWriteArrayList

class FlowToListUnitTest {

    @Test
    fun `create list from flow using toList method`() = runTest {
        val flow = flowOf(1, 2, 3)
        val result = flow.toList()

        assertEquals(listOf(1, 2, 3), result)
    }

    @Test
    fun `create list using toList with custom mutable list`() = runTest {
        val flow = flowOf(1, 2, 3)
        val result = CopyOnWriteArrayList<Int>()
        flow.toList(result)

        assertEquals(listOf(1, 2, 3), result)
    }

    @Test
    fun `create list from flow using collect method`() = runTest {
        val flow = flowOf(1, 2, 3)
        val result = mutableListOf<Int>()
        flow.collect { result.add(it) }

        assertEquals(listOf(1, 2, 3), result)
    }

    @Test
    fun `create list from flow using fold method`() = runTest {
        val flow = flowOf(1, 2, 3)
        val result = flow.fold(mutableListOf<Int>()) { acc, value ->
            acc.apply { add(value) }
        }
        assertEquals(listOf(1, 2, 3), result)
    }

}