package com.baeldung.parallelOperationsCollections

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*
import java.util.concurrent.Executors
import java.util.stream.Collectors


class ParallelOperationCollectionsUnitTest {

    private val inputNumLists = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    private val inputFruitLists = listOf("apple", "banana", "cherry", "date", "elderberry")

    // using _Collection.kt Not Parallel
    @Test
    fun `using map and filter`() { // serial a.k.a not parallel
        val numResults = inputNumLists.map { it * it }.filter { it % 2 == 0 }
        val fruitResults = inputFruitLists.filter { it.contains("a") }.map { "$it-$it" }

        assertListEquals(listOf(4, 16, 36, 64, 100), numResults)
        assertListEquals(listOf("apple-apple", "banana-banana", "date-date"), fruitResults)
    }

    @Test
    fun `using merge using zip & mapIndex`() { // serial a.k.a not parallel
        val numResults = inputNumLists.zip(inputFruitLists).mapIndexed { _, (num, str) ->
            "${num * num}. ${str}(${str.length})"
        }.toList()

        assertListEquals(listOf("1. apple(5)", "4. banana(6)", "9. cherry(6)", "16. date(4)", "25. elderberry(10)"), numResults)
    }

    @Test
    fun `using map zip flatmap`() { // serial a.k.a not parallel
        val results = inputNumLists.map { it * it }.filter { it % 2 == 0 }.zip(inputFruitLists.filter { it.contains("a") }.map { "$it-$it" }).flatMap { (num, str) ->
            listOf("$num. ${str.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }}(${str.length})")
        }
        assertListEquals(listOf("4. Apple-apple(11)", "16. Banana-banana(13)", "36. Date-date(9)"), results)
    }

    private fun assertListEquals(expected: List<Any>, actual: List<Any>) {
        assertEquals(expected, actual)
    }

    private fun <T, R> Iterable<T>.parallelMap(transform: (T) -> R): List<R> = runBlocking { map { async { transform(it) } }.map { it.await() } }

    @Test
    fun `using parallel map by creating an extension of Iterable`() { // parallel
        val numResults = inputNumLists.parallelMap { it * it }.filter { it % 2 == 0 }
        val fruitResults = inputFruitLists.parallelMap { "$it-$it" }.filter { it.contains("a") }

        assertListEquals(listOf(4, 16, 36, 64, 100), numResults)
        assertListEquals(listOf("apple-apple", "banana-banana", "date-date"), fruitResults)
    }

    @Test
    fun `using Coroutines map async awaitAll`() { // parallel
        val numResults = runBlocking {
            inputNumLists.map { num ->
                async {
                    println(num * num)
                    num * num
                }
            }.awaitAll().filter { it % 2 == 0 }
        }

        val fruitResults = runBlocking {
            inputFruitLists.map { str ->
                async {
                    println("$str-$str")
                    "$str-$str"
                }
            }.awaitAll().filter { it.contains("a") }
        }

        assertListEquals(listOf(4, 16, 36, 64, 100), numResults)
        assertListEquals(listOf("apple-apple", "banana-banana", "date-date"), fruitResults)
    }

    private fun <T> Iterable<T>.forEachParallel(action: suspend (T) -> Unit) = runBlocking { map { async { action(it) } }.forEach { it.await() } }

    @Test
    fun `using Coroutines forEach parallel`() { // parallel
        val numResults = mutableListOf<Int>()
        val fruitResults = mutableListOf<String>()

        inputNumLists.forEachParallel {
            val square = it * it
            if (square % 2 == 0) {
                numResults.add(square)
            }
        }

        inputFruitLists.forEachParallel {
            if (it.contains("a")) {
                fruitResults.add("$it-$it")
            }
        }

        assertListEquals(listOf(4, 16, 36, 64, 100), numResults)
        assertListEquals(listOf("apple-apple", "banana-banana", "date-date"), fruitResults)
    }

    @Test
    fun `using executorService java`() { // parallel
        val executor = Executors.newFixedThreadPool(1)

        val numResults = inputNumLists.map { executor.submit<Int> { it * it }.get() }.filter { it % 2 == 0 }
        val fruitResults = inputFruitLists.map { executor.submit<String> { "$it-$it" }.get() }.filter { it.contains("a") }

        assertListEquals(listOf(4, 16, 36, 64, 100), numResults)
        assertListEquals(listOf("apple-apple", "banana-banana", "date-date"), fruitResults)

        executor.shutdown()
    }

    @Test
    fun `using rxJava2`() { // parallel
        val numResults = Observable.fromIterable(inputNumLists).subscribeOn(Schedulers.io()) // Switch to IO scheduler for CPU-bound work
            .map { it * it }.filter { it % 2 == 0 }.observeOn(Schedulers.computation()) // Switch to computation scheduler for filtering
            .toList().toObservable() // Convert Single to Observable
            .subscribeOn(Schedulers.io()) // Back to IO for final list creation

        val fruitResults = Observable.fromIterable(inputFruitLists).subscribeOn(Schedulers.io()) // Switch to IO scheduler for string operations
            .filter { it.contains("a") }.map { "$it-$it" }.observeOn(Schedulers.computation()) // Switch to computation scheduler for list creation
            .toList().toObservable() // Convert Single to Observable
            .subscribeOn(Schedulers.io()) // Back to IO for final list creation

        val mergedResults = Observable.zip(numResults.flatMapIterable { it }, fruitResults.flatMapIterable { it }) { num, fruits -> "$num. $fruits(${fruits.length})" }.subscribeOn(Schedulers.io()) // Switch to IO scheduler for merging
            .toList().blockingGet()

        assertListEquals(listOf("4. apple-apple(11)", "16. banana-banana(13)", "36. date-date(9)"), mergedResults)
    }

    // using Kotlin Flow
    @Test
    fun `asFlow and map`() = runBlocking { // parallel
        val numResults = inputNumLists.asFlow().map { it * it }.toList().filter { it % 2 == 0 }
        val fruitResults = inputFruitLists.asFlow().map { "$it-$it" }.toList().filter { it.contains("a") }

        assertListEquals(listOf(4, 16, 36, 64, 100), numResults)
        assertListEquals(listOf("apple-apple", "banana-banana", "date-date"), fruitResults)
    }

    @Test
    fun `merge two list with zip and asFlow`() = runBlocking { // parallel
        val results = mutableListOf<Any>()

        inputNumLists.zip(inputFruitLists) { num, str ->
            "${num * num}. ${str.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }}(${str.length})"
        }.asFlow().collect { result ->
            results.add(result)
        }

        assertListEquals(listOf("1. Apple(5)", "4. Banana(6)", "9. Cherry(6)", "16. Date(4)", "25. Elderberry(10)"), results)
    }

    // Java Stream API
    @Test
    fun `using parallelStream`() { // parallel
        val numResults = inputNumLists.parallelStream().filter { it % 2 == 0 }.map { it * it }.collect(Collectors.toList())
        val fruitResults = inputFruitLists.parallelStream().filter { it.contains("a") }.map { "$it-$it" }.collect(Collectors.toList())

        assertListEquals(listOf(4, 16, 36, 64, 100), numResults)
        assertListEquals(listOf("apple-apple", "banana-banana", "date-date"), fruitResults)

        println(numResults.zip(fruitResults))
    }
}
