package com.baeldung.parallelOperationsCollections

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.Executors
import java.util.stream.Collectors


class ParallelOperationCollectionsUnitTest {

    private val inputNumList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    private val inputStringList = listOf("apple", "banana", "cherry", "date", "elderberry")

    private inline fun <T, R> Iterable<T>.parallelMap(crossinline transform: (T) -> R): List<R> = runBlocking { map { async { transform(it) } }.map { it.await() } }

    private inline fun <T> Iterable<T>.forEachParallel(crossinline action: suspend (T) -> Unit) = runBlocking { map { async { action(it) } }.forEach { it.await() } }

    private fun assertListEquals(expected: List<Any>, actual: List<Any>) {
        assertEquals(expected, actual)
    }

    @Test
    fun `using parallelStream`() {
        val numResults = inputNumList.parallelStream().filter { it % 2 == 0 }.map { it * it }.collect(Collectors.toList())
        val fruitResults = inputStringList.parallelStream().filter { it.contains("a") }.map { "$it-$it" }.collect(Collectors.toList())

        println(fruitResults)

        assertListEquals(listOf(4, 16, 36, 64, 100), numResults)
        assertListEquals(listOf("apple-apple", "banana-banana", "date-date"), fruitResults)
    }

    @Test
    fun `using Coroutines map parallel`() {
        val numResults = inputNumList.parallelMap { it * it }.filter { it % 2 == 0 }
        val fruitResults = inputStringList.parallelMap { "$it-$it" }.filter { it.contains("a") }

        assertListEquals(listOf(4, 16, 36, 64, 100), numResults)
        assertListEquals(listOf("apple-apple", "banana-banana", "date-date"), fruitResults)
    }

    @Test
    fun `using Coroutines forEach parallel`() {
        val numResults = mutableListOf<Int>()
        val fruitResults = mutableListOf<String>()

        inputNumList.forEachParallel {
            val square = it * it
            if (square % 2 == 0) {
                numResults.add(square)
            }
        }

        inputStringList.forEachParallel {
            if (it.contains("a")) {
                fruitResults.add("$it-$it")
            }
        }

        assertListEquals(listOf(4, 16, 36, 64, 100), numResults)
        assertListEquals(listOf("apple-apple", "banana-banana", "date-date"), fruitResults)
    }

    @Test
    fun `using executorService java`() {
        val executor = Executors.newFixedThreadPool(1)

        val numResults = inputNumList.map { executor.submit<Int> { it * it }.get() }.filter { it % 2 == 0 }
        val fruitResults = inputStringList.map { executor.submit<String> { "$it-$it" }.get() }.filter { it.contains("a") }

        assertListEquals(listOf(4, 16, 36, 64, 100), numResults)
        assertListEquals(listOf("apple-apple", "banana-banana", "date-date"), fruitResults)

        executor.shutdown()
    }

    @Test
    fun `using rxJava2`() {
        val numResults = Observable.fromIterable(inputNumList).concatMap { Observable.just(it * it).subscribeOn(Schedulers.io()) }.toList().blockingGet().filter { it % 2 == 0 }
        val fruitResults = Observable.fromIterable(inputStringList).concatMap { Observable.just("$it-$it").subscribeOn(Schedulers.io()) }.toList().blockingGet().filter { it.contains("a") }

        assertListEquals(listOf(4, 16, 36, 64, 100), numResults)
        assertListEquals(listOf("apple-apple", "banana-banana", "date-date"), fruitResults)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `using Kotlin Flow`() = runBlocking {
        val numResults = inputNumList.asFlow().flatMapMerge { flow { emit(it * it) } }.toList().filter { it % 2 == 0 }
        val fruitResults = inputStringList.asFlow().flatMapMerge { flow { emit("$it-$it") } }.toList().filter { it.contains("a") }

        assertListEquals(listOf(4, 16, 36, 64, 100), numResults)
        assertListEquals(listOf("apple-apple", "banana-banana", "date-date"), fruitResults)
    }
}
