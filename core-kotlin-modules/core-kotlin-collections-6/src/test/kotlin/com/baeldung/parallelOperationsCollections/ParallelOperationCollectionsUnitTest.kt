package com.baeldung.parallelOperationsCollections

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.Executors
import java.util.stream.Collectors


class ParallelOperationCollectionsUnitTest {

    private val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    private inline fun <T, R> Iterable<T>.parallelMap(crossinline transform: (T) -> R): List<R> =
        runBlocking { map { async { transform(it) } }.map { it.await() } }

    private inline fun <T> Iterable<T>.forEachParallel(crossinline action: suspend (T) -> Unit) =
        runBlocking { map { async { action(it) } }.forEach { it.await() } }

    @Test
    fun `using parallelStream`() {
        val result = list.parallelStream().map { it * it }.collect(Collectors.toList())

        assertEquals(listOf(1, 4, 9, 16, 25, 36, 49, 64, 81, 100), result)
    }

    @Test
    fun `using Coroutines map parallel`() {
        val result = list.parallelMap { it * it }

        assertEquals(listOf(1, 4, 9, 16, 25, 36, 49, 64, 81, 100), result)
    }

    @Test
    fun `using Coroutines forEach parallel`()  {
        val mutex = Mutex()
        val result = mutableListOf<Int>()

        list.forEachParallel {
            mutex.withLock {
                result.add(it * it)
            }
        }

        assertEquals(listOf(1, 4, 9, 16, 25, 36, 49, 64, 81, 100), result)
    }

    @Test
    fun `using executorService java`() {
        val executor = Executors.newFixedThreadPool(1)

        val result = list.map { executor.submit<Int> { it * it }.get() }

        assertEquals(listOf(1, 4, 9, 16, 25, 36, 49, 64, 81, 100), result)

        executor.shutdown()
    }

    @Test
    fun `using rxJava2`() {
        val result = Observable.fromIterable(list)
            .concatMap { Observable.just(it * it).subscribeOn(Schedulers.io()) } // instead flatmap
            .toList().blockingGet()

        assertEquals(listOf(1, 4, 9, 16, 25, 36, 49, 64, 81, 100), result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `using Kotlin Flow`() = runBlocking {
        val result = list.asFlow().flatMapMerge { flow { emit(it * it) } } // ExperimentalCoroutinesApi
            .toList()

        assertEquals(listOf(1, 4, 9, 16, 25, 36, 49, 64, 81, 100), result)
    }

}