package com.baeldung.parallelOperationsCollections

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.stream.Collectors
import java.util.concurrent.Executors
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList


class ParallelOperationCollectionsUnitTest {

    @Test
    fun `using parallelStream`(){
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        val result = list.parallelStream()
            .map { it * it }
            .collect(Collectors.toList())

        assertEquals(listOf(1, 4, 9, 16, 25, 36, 49, 64, 81, 100), result)
    }

    @Test
    fun `using map, async & transform`(){
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val result = list.parallelMap { it * it }
        assertEquals(listOf(1, 4, 9, 16, 25, 36, 49, 64, 81, 100), result)
    }

    private inline fun <T, R> Iterable<T>.parallelMap(crossinline transform: (T) -> R): List<R> {
        return runBlocking {
            map { async { transform(it) } }
                .map { it.await() }
        }
    }

    @Test
    fun `using forEachParallel from Coroutines`() = runBlocking{
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val mutex = Mutex()
        val result = mutableListOf<Int>()

        list.forEachParallel(Dispatchers.Default) {
            mutex.withLock {
                result.add(it * it)
            }
        }
        assertEquals(listOf(1, 4, 9, 16, 25, 36, 49, 64, 81, 100), result)
    }

    private suspend inline fun <T> Iterable<T>.forEachParallel(
        dispatcher: CoroutineDispatcher,
        crossinline action: suspend (T) -> Unit
    ) = map { (dispatcher) { action(it) } }

    @Test
    fun `using executorService java`(){
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        val executor = Executors.newFixedThreadPool(1)

        val result = list.map {
            executor.submit<Int> { it * it }.get()
        }

        assertEquals(listOf(1, 4, 9, 16, 25, 36, 49, 64, 81, 100), result)

        executor.shutdown()
    }

    @Test
    fun `using rxJava2`(){
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        val result = Observable.fromIterable(list)
            .concatMap { Observable.just(it * it).subscribeOn(Schedulers.io()) } // instead flatmap
            .toList()
            .blockingGet()

        assertEquals(listOf(1, 4, 9, 16, 25, 36, 49, 64, 81, 100), result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `using Kotlin Flow`() = runBlocking{
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        val result = list.asFlow()
            .flatMapMerge { flow { emit(it * it) } } // ExperimentalCoroutinesApi
            .toList()

        assertEquals(listOf(1, 4, 9, 16, 25, 36, 49, 64, 81, 100), result)
    }

}