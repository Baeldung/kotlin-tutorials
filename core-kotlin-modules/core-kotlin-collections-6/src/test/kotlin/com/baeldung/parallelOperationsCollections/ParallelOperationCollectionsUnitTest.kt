package com.baeldung.parallelOperationsCollections

import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.Instant
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.stream.Collectors


class ParallelOperationCollectionsUnitTest {

    data class Person(val name: String, val age: Int, var isAdult: Boolean? = null)

    private val people = listOf(
        Person("Martin", 12),
        Person("Ahmad", 42),
        Person("Alina", 13),
        Person("Alice", 30),
        Person("Bob", 16),
        Person("Charlie", 40)
    )

    private fun List<Person>.assertResultsTrue() {
        assertThat(this).containsExactly(
            Person("Bob", 16, false), Person("Alice", 30, true), Person("Charlie", 40, true), Person("Ahmad", 42, true)
        )
    }

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd:HH:mm:ss:SSS")

    private fun Person.printFormattedInfo() {
        println(
            "%-30s %-40s %s".format(
                dateFormat.format(Date.from(Instant.now())), Thread.currentThread().name, this
            )
        )
    }

    private fun printHeader() {
        println(
            "%-30s %-40s %s".format(
                "Time", "Thread name", "Operation"
            )
        )
        println("-".repeat(115))
    }

    private fun Instant.printFooter() {
        val endTime = Instant.now()
        val duration = Duration.between(this, endTime)
        println("Total time taken: ${duration.toMillis()} ms")
        println()
    }

    @Test
    fun `using coroutines for parallel operations`() = runBlocking {
        printHeader()
        val startTime = Instant.now()

        val filteredPeople = people.map { person ->
            async {
                launch {
                    person.isAdult = person.age >= 18
                    person.printFormattedInfo()
                }
                person
            }
        }.awaitAll().filter { it.age > 15 }.sortedBy { it.age }

        startTime.printFooter()

        filteredPeople.assertResultsTrue()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `using coroutines for parallel operations with Flow`() = runBlocking {
        printHeader()
        val startTime = Instant.now()

        val filteredPeople = people.asFlow().flatMapMerge { person ->
            flow {
                emit(async {
                    person.isAdult = person.age >= 18
                    person.printFormattedInfo()
                    person
                }.await())
            }
        }.filter { it.age > 15 }.toList().sortedBy { it.age }

        startTime.printFooter()

        filteredPeople.assertResultsTrue()
    }

    @Test
    fun `using RxJava for parallel operations`() { // Observable.class from io.reactivex;
        printHeader()
        val startTime = Instant.now()

        val observable = Observable.fromIterable(people).flatMap({
            Observable.just(it).subscribeOn(Schedulers.computation()).doOnNext { person ->
                person.isAdult = person.age >= 18
                person.printFormattedInfo()
            }
        }, people.size) // Uses maxConcurrency for the number of elements
            .filter { it.age > 15 }.toList().map { it.sortedBy { person -> person.age } }.blockingGet()

        startTime.printFooter()

        observable.assertResultsTrue()
    }

    @Test
    fun `using RxKotlin for parallel operations`() { // ObservableKt.kt.class from io.reactivex.rxkotlin
        printHeader()
        val startTime = Instant.now()

        val observable = people.toObservable().flatMap({
            Observable.just(it).subscribeOn(Schedulers.computation()).doOnNext { person ->
                person.isAdult = person.age >= 18
                person.printFormattedInfo()
            }
        }, people.size) // Uses maxConcurrency for the number of elements
            .filter { it.age > 15 }.toList().map { it.sortedBy { person -> person.age } }.blockingGet()

        startTime.printFooter()

        observable.assertResultsTrue()
    }

    @Test
    fun `using RxKotlin but still use 1 thread`() { // ObservableKt.kt.class from io.reactivex.rxkotlin
        printHeader()
        val startTime = Instant.now()

        val observable =
            people.toObservable().subscribeOn(Schedulers.io()).flatMap { Observable.just(it) }.doOnNext { person ->
                person.isAdult = person.age >= 18
                person.printFormattedInfo()
            }.filter { it.age > 15 }.toList().map { it.sortedBy { person -> person.age } }.blockingGet()

        startTime.printFooter()

        observable.assertResultsTrue()
    }

    @Test
    fun `using parallelStream()`() {
        printHeader()
        val startTime = Instant.now()

        val filteredPeople = people.parallelStream().map { person ->
            person.isAdult = person.age >= 18
            person.printFormattedInfo()
            person
        }.filter { it.age > 15 }.sorted { p1, p2 -> p1.age.compareTo(p2.age) }.collect(Collectors.toList())

        startTime.printFooter()

        filteredPeople.assertResultsTrue()
    }

    @Test
    fun `using ScheduledExecutorService for parallel operations`() {
        printHeader()
        val startTime = Instant.now()

        val executor = Executors.newFixedThreadPool(people.size)
        val futures = people.map { person ->
            executor.submit(Callable {
                person.isAdult = person.age >= 18
                person.printFormattedInfo()
                person
            })
        }.map { it.get() }.filter { it.age > 15 }.sortedBy { it.age }

        executor.shutdown()

        startTime.printFooter()

        futures.assertResultsTrue()
    }
}
