package com.baeldung.parallelOperationsCollections

import io.reactivex.Observable
import kotlinx.coroutines.async
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

    private val inputNumList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    private val inputStringList = listOf("apple", "banana", "cherry", "date", "elderberry")

    private fun <T, R> Iterable<T>.parallelMap(transform: (T) -> R): List<R> = runBlocking { map { async { transform(it) } }.map { it.await() } }

    private fun <T> Iterable<T>.forEachParallel(action: suspend (T) -> Unit) = runBlocking { map { async { action(it) } }.forEach { it.await() } }

    private fun assertListEquals(expected: List<Any>, actual: List<Any>) {
        assertEquals(expected, actual)
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
        val numResults = Observable.fromIterable(inputNumList).map { it * it }.filter { it % 2 == 0 }.toList().blockingGet()

        val fruitResults = Observable.fromIterable(inputStringList).filter { it.contains("a") }.map { "$it-$it" }.toList().blockingGet()

        assertListEquals(listOf(4, 16, 36, 64, 100), numResults)
        assertListEquals(listOf("apple-apple", "banana-banana", "date-date"), fruitResults)


        val mergeResults = Observable.fromIterable(inputNumList).map { it * it }.filter { it % 2 == 0 }.zipWith(Observable.fromIterable(inputStringList).filter { it.contains("a") }.map { "$it-$it" }) { num, str ->
            "$num. ${str.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }}(${str.length})"
        }.toList().blockingGet()

        assertListEquals(listOf("4. Apple-apple(11)", "16. Banana-banana(13)", "36. Date-date(9)"), mergeResults)
    }

    // using _Collection.kt
    @Test
    fun `using map and filter`() {
        val numResults = inputNumList.map { it * it }.filter { it % 2 == 0 }
        val fruitResults = inputStringList.filter { it.contains("a") }.map { "$it-$it" }

        assertListEquals(listOf(4, 16, 36, 64, 100), numResults)
        assertListEquals(listOf("apple-apple", "banana-banana", "date-date"), fruitResults)
    }

    @Test
    fun `using merge using zip & mapIndex`() {
        val numResults = inputNumList.zip(inputStringList).mapIndexed { _, (num, str) ->
            "${num * num}. ${str}(${str.length})"
        }.toList()

        assertListEquals(listOf("1. apple(5)", "4. banana(6)", "9. cherry(6)", "16. date(4)", "25. elderberry(10)"), numResults)
    }

    @Test
    fun `using map zip flatmap`() {
        val results = inputNumList.map { it * it }.filter { it % 2 == 0 }.zip(inputStringList.filter { it.contains("a") }.map { "$it-$it" }).flatMap { (num, str) ->
            listOf("$num. ${str.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }}(${str.length})")
        }

        assertListEquals(listOf("4. Apple-apple(11)", "16. Banana-banana(13)", "36. Date-date(9)"), results)
    }

    // using Kotlin Flow
    @Test
    fun `asFlow and map`() = runBlocking {
        val numResults = inputNumList.asFlow().map { it * it }.toList().filter { it % 2 == 0 }
        val fruitResults = inputStringList.asFlow().map { "$it-$it" }.toList().filter { it.contains("a") }

        assertListEquals(listOf(4, 16, 36, 64, 100), numResults)
        assertListEquals(listOf("apple-apple", "banana-banana", "date-date"), fruitResults)
    }

    @Test
    fun `merge two list with zip and asFlow`() = runBlocking {
        val results = mutableListOf<Any>()

        inputNumList.zip(inputStringList) { num, str ->
            "${num * num}. ${str.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }}(${str.length})"
        }.asFlow().collect { result ->
            results.add(result)
        }

        assertListEquals(listOf("1. Apple(5)", "4. Banana(6)", "9. Cherry(6)", "16. Date(4)", "25. Elderberry(10)"), results)
    }

    // Java Stream API
    @Test
    fun `using parallelStream`() {
        val numResults = inputNumList.parallelStream().filter { it % 2 == 0 }.map { it * it }.collect(Collectors.toList())
        val fruitResults = inputStringList.parallelStream().filter { it.contains("a") }.map { "$it-$it" }.collect(Collectors.toList())

        assertListEquals(listOf(4, 16, 36, 64, 100), numResults)
        assertListEquals(listOf("apple-apple", "banana-banana", "date-date"), fruitResults)

        println(numResults.zip(fruitResults))
    }
}
