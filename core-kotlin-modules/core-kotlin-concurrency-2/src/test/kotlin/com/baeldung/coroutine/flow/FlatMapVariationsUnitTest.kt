package com.baeldung.coroutine.flow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class FlatMapVariationsUnitTest {

    val upStreamCount = 3

    @OptIn(FlowPreview::class)
    @Test
    fun whenFlatMapMerge_thenUpStreamNotWaitForDownStream() = runBlocking {
        val result = flow { // flow builder
            for (i in 1..upStreamCount) {
                delay(1) // pretend we are doing something useful here
                emit(i) // emit next value
            }
        }.flatMapMerge { it ->
            flow {
                emit(it * 2)
                delay(2)
                emit(it * 2 + 1)
            }
        }.toList()
        Assertions.assertEquals(upStreamCount * 2, result.size)
        Assertions.assertNotEquals(result, result.sorted())
    }

    @OptIn(FlowPreview::class)
    @Test
    fun whenFlatMapConcat_thenUpStreamWaitForDownStream() = runBlocking {
        val result = flow { // flow builder
            for (i in 1..upStreamCount) {
                delay(1) // pretend we are doing something useful here
                emit(i) // emit next value
            }
        }.flatMapConcat { it ->
            flow {
                emit(it * 2)
                delay(2)
                emit(it * 2 + 1)
            }
        }.toList()
        Assertions.assertEquals(upStreamCount * 2, result.size)
        Assertions.assertEquals(result, result.sorted())
    }

    @OptIn(FlowPreview::class)
    @Test
    fun whenFlattenConcat_thenUpStreamWaitForDownStream() = runBlocking {
        val result = flow { // flow builder
            for (i in 1..upStreamCount) {
                delay(1) // pretend we are doing something useful here
                emit(i) // emit next value
            }
        }.map { it ->
            flow {
                emit(it * 2)
                delay(2)
                emit(it * 2 + 1)
            }
        }.flattenConcat()
            .toList()
        Assertions.assertEquals(upStreamCount * 2, result.size)
        Assertions.assertEquals(result, result.sorted())
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    @Test
    fun whenFlatMapLatest_thenUpStreamEmitCancelDownStream() = runBlocking {
        val result = flow { // flow builder
            for (i in 1..upStreamCount) {
                delay(1) // pretend we are doing something useful here
                emit(i) // emit next value
            }
        }.flatMapLatest { it ->
            flow {
                emit(it * 2)
                delay(2)
                emit(it * 2 + 1)
            }
        }.toList()
        Assertions.assertEquals(result.last(), upStreamCount * 2 + 1)
        Assertions.assertEquals(result.dropLast(1).size, upStreamCount)
        Assertions.assertTrue(result.dropLast(1).none { i -> i % 2 == 1 })
    }
}