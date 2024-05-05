package com.baeldung.parallelOperationsCollections

import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
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

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd:HH:mm:ss:SSS")

    private fun assertResults(filteredPeople: List<Person>) {
        assertThat(filteredPeople).containsExactly(
            Person("Bob", 16, false), Person("Alice", 30, true), Person("Charlie", 40, true), Person("Ahmad", 42, true)
        )
    }

    @Test
    fun `using coroutines for parallel operations`() = runBlocking {
        println(
            "%-30s %-40s %s".format(
                "Time", "Thread name", "Operation"
            )
        )
        val startTime = System.currentTimeMillis()
        val filteredPeople = people.map { person ->
            async {
                launch {
                    person.isAdult = person.age >= 18
                    println(
                        "%-30s %-40s %s".format(
                            dateFormat.format(System.currentTimeMillis()), Thread.currentThread().name, person
                        )
                    )
                }
                person
            }
        }.awaitAll().filter { it.age > 15 }.sortedBy { it.age }

        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        println("Total time taken: $duration ms")
        println()

        assertResults(filteredPeople)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `using coroutines for parallel operations with Flow`() = runBlocking {
        println(
            "%-30s %-40s %s".format(
                "Time", "Thread name", "Operation"
            )
        )
        val startTime = System.currentTimeMillis()
        val filteredPeople = people.asFlow().flatMapMerge { person ->
            flow {
                emit(async {
                    person.isAdult = person.age >= 18

                    println(
                        "%-30s %-40s %s".format(
                            dateFormat.format(System.currentTimeMillis()), Thread.currentThread().name, person
                        )
                    )
                    person
                }.await())
            }
        }.filter { it.age > 15 }.toList().sortedBy { it.age }

        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        println("Total time taken: $duration ms")
        println()

        assertResults(filteredPeople)
    }

    @Test
    fun `using RxJava for parallel operations`() { // Observable.class from io.reactivex;
        println(
            "%-30s %-40s %s".format(
                "Time", "Thread name", "Operation"
            )
        )
        val startTime = System.currentTimeMillis()
        val observable = Observable.fromIterable(people).flatMap({
            Observable.just(it).subscribeOn(Schedulers.computation()).doOnNext { person ->
                person.isAdult = person.age >= 18
                println(
                    "%-30s %-40s %s".format(
                        dateFormat.format(System.currentTimeMillis()), Thread.currentThread().name, person
                    )
                )
            }
        }, people.size) // Uses maxConcurrency for the number of elements
            .filter { it.age > 15 }.toList().map { it.sortedBy { person -> person.age } }.blockingGet()

        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        println("Total time taken: $duration ms")
        println()

        assertResults(observable)
    }

    @Test
    fun `using RxKotlin for parallel operations`() { // ObservableKt.kt.class from io.reactivex.rxkotlin
        println(
            "%-30s %-40s %s".format(
                "Time", "Thread name", "Operation"
            )
        )
        val startTime = System.currentTimeMillis()
        val observable = people.toObservable().flatMap({
            Observable.just(it).subscribeOn(Schedulers.computation()).doOnNext { person ->
                person.isAdult = person.age >= 18
                println(
                    "%-30s %-40s %s".format(
                        dateFormat.format(System.currentTimeMillis()), Thread.currentThread().name, person
                    )
                )
            }
        }, people.size) // Uses maxConcurrency for the number of elements
            .filter { it.age > 15 }.toList().map { it.sortedBy { person -> person.age } }.blockingGet()

        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        println("Total time taken: $duration ms")
        println()

        assertResults(observable)
    }

    @Test
    fun `using RxKotlin but still use 1 thread`() { // ObservableKt.kt.class from io.reactivex.rxkotlin
        println(
            "%-30s %-40s %s".format(
                "Time", "Thread name", "Operation"
            )
        )
        val startTime = System.currentTimeMillis()
        val observable =
            people.toObservable().subscribeOn(Schedulers.io()).flatMap { Observable.just(it) }.doOnNext { person ->
                person.isAdult = person.age >= 18
                println(
                    "%-30s %-40s %s".format(
                        dateFormat.format(System.currentTimeMillis()), Thread.currentThread().name, person
                    )
                )
            }.filter { it.age > 15 }.toList().map { it.sortedBy { person -> person.age } }.blockingGet()

        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        println("Total time taken: $duration ms")
        println()

        assertResults(observable)
    }

    @Test
    fun `using parallelStream()`() {
        println(
            "%-30s %-40s %s".format(
                "Time", "Thread name", "Operation"
            )
        )
        val startTime = System.currentTimeMillis()
        val filteredPeople = people.parallelStream().map { person ->
            person.isAdult = person.age >= 18
            println(
                "%-30s %-40s %s".format(
                    dateFormat.format(System.currentTimeMillis()), Thread.currentThread().name, person
                )
            )
            person
        }.filter { it.age > 15 }.sorted { p1, p2 -> p1.age.compareTo(p2.age) }.collect(Collectors.toList())

        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        println("Total time taken: $duration ms")
        println()

        assertResults(filteredPeople)
    }

    @Test
    fun `using ScheduledExecutorService for parallel operations`() {
        println(
            "%-30s %-40s %s".format(
                "Time", "Thread name", "Operation"
            )
        )
        val startTime = System.currentTimeMillis()
        val executor = Executors.newFixedThreadPool(people.size)
        val futures = people.map { person ->
            executor.submit(Callable {
                person.isAdult = person.age >= 18
                println(
                    "%-30s %-40s %s".format(
                        dateFormat.format(System.currentTimeMillis()), Thread.currentThread().name, person
                    )
                )
                person
            })
        }.map { it.get() }.filter { it.age > 15 }.sortedBy { it.age }

        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        println("Total time taken: $duration ms")
        println()

        assertResults(futures)

        executor.shutdown()
    }
}
