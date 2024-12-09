package com.baeldung.flowToList

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FlowToListUnitTest {

    @Test
    fun `create list from flow using toList method`() = runTest {
        val flow = flowOf(1, 2, 3)
        val result = flow.toList()

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

    @Test
    fun `create list from flow using launchIn method`() = runTest {
        val list = mutableListOf<Int>()
        val scope = CoroutineScope(SupervisorJob())
        val flow = flowOf(1, 2, 3)

        flow.onEach { list.add(it) }
            .launchIn(scope)

        assertEquals(listOf(1, 2, 3), list)
    }

    @Test
    fun `create list from flow using flatMapConcat method`() = runTest {
        val flow = flowOf(1, 2, 3)
        val result = flow.flatMapConcat { value ->
            flowOf(value * 10)
        }.toList()

        assertEquals(listOf(10, 20, 30  ), result)
    }
}