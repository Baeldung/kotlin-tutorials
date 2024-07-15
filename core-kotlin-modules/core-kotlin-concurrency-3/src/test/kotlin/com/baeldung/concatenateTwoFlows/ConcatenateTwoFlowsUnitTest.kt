package com.baeldung.concatenateTwoFlows

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.asFlux
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux

class ConcatenateTwoFlowsUnitTest {

    @Test
    fun `concatenate two flows using  custom flow builder`() = runBlocking {
        val flow1 = flowOf(1, 2, 3)
        val flow2 = flowOf(4, 5, 6)
        val result = concatenateFlowsUsingCustomBuilder(flow1, flow2).toList()

        assertEquals(listOf(1, 2, 3, 4, 5, 6), result)
    }

    @Test
    fun `concatenate two flows using flattenConcat method`() = runBlocking {
        val flow1 = flowOf(1, 2, 3)
        val flow2 = flowOf(4, 5, 6)
        val result = flowOf(flow1, flow2).flattenConcat().toList()

        assertEquals(listOf(1, 2, 3, 4, 5, 6), result)
    }

    @Test
    fun `concatenate two flows using onCompletion method`() = runBlocking {
        val flow1 = flowOf(1, 2, 3)
        val flow2 = flowOf(4, 5, 6)
        val result = flow1.onCompletion { emitAll(flow2) }.toList()

        assertEquals(listOf(1, 2, 3, 4, 5, 6), result)
    }

    @Test
    fun `concatenate two flows using collect and emitAll method`() = runBlocking {
        val flow1 = flowOf(1, 2, 3)
        val flow2 = flowOf(4, 5, 6)
        val result = concatenateFlowsUsingEmitAll(flow1, flow2).toList()

        assertEquals(listOf(1, 2, 3, 4, 5, 6), result)
    }

    @Test
    fun `concatenate two flows using reactive`() = runBlocking {
        val flow1 = flowOf(1, 2, 3)
        val flow2 = flowOf(4, 5, 6)
        val result = concatenateFlowsUsingReactive(flow1, flow2).toList()

        assertEquals(listOf(1, 2, 3, 4, 5, 6), result)
    }
}

fun concatenateFlowsUsingCustomBuilder(flow1: Flow<Int>, flow2: Flow<Int>): Flow<Int> = flow {
    flow1.collect { emit(it) }
    flow2.collect { emit(it) }
}

fun concatenateFlowsUsingEmitAll(flow1: Flow<Int>, flow2: Flow<Int>): Flow<Int> = flow {
    flow1.collect { emit(it) }
    emitAll(flow2)
}

fun concatenateFlowsUsingReactive(flow1: Flow<Int>, flow2: Flow<Int>): Flow<Int> {
    val flux1 = flow1.asFlux()
    val flux2 = flow2.asFlux()
    return Flux.concat(flux1, flux2).asFlow()
}