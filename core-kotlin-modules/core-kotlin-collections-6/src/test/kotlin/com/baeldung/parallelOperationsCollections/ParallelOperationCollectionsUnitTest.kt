package com.baeldung.parallelOperationsCollections

import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
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

    private fun assertResults(filteredPeople: List<Person>) {
        assertThat(filteredPeople).containsExactly(
            Person("Bob", 16, false), Person("Alice", 30, true), Person("Charlie", 40, true), Person("Ahmad", 42, true)
        )
    }

    @Test
    fun `using coroutines for parallel operations`() = runBlocking {
        val filteredPeople = people.map { person ->
            async {
                launch {
                    person.isAdult = person.age > 18
                }
                person
            }
        }.awaitAll().filter { it.age > 15 }.sortedBy { it.age }

        assertResults(filteredPeople)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `using coroutines for parallel operations with Flow`() = runBlocking {
        val filteredPeople = people.asFlow().flatMapMerge { person ->
            flow {
                emit(async {
                    person.isAdult = person.age > 18
                    person
                }.await())
            }
        }.filter { it.age > 15 }.toList().sortedBy { it.age }

        assertResults(filteredPeople)
    }

    @Test
    fun `using RxJava for parallel operations`() { // Observable.class from io.reactivex;
        val observable = Observable.fromIterable(people)
            .subscribeOn(Schedulers.io())
            .flatMap { Observable.just(it) }.doOnNext { person ->
                person.isAdult = person.age > 18
            }.filter { it.age > 15 }.toList().map { it.sortedBy { person -> person.age } }.blockingGet()

        assertResults(observable)
    }

    @Test
    fun `using RxKotlin for parallel operations`() { // ObservableKt.kt.class from io.reactivex.rxkotlin
        val observable = people.toObservable()
            .subscribeOn(Schedulers.io())
            .flatMap { Observable.just(it) }.doOnNext { person ->
                person.isAdult = person.age > 18
            }.filter { it.age > 15 }.toList().map { it.sortedBy { person -> person.age } }.blockingGet()

        assertResults(observable)
    }

    @Test
    fun `using parallelStream()`() {
        val filteredPeople = people.parallelStream().map { person ->
            person.isAdult = person.age > 18
            person
        }.filter { it.age > 15 }.sorted { p1, p2 -> p1.age.compareTo(p2.age) }.collect(Collectors.toList())

        assertResults(filteredPeople)
    }

    @Test
    fun `using ScheduledExecutorService for parallel operations`() {
        val executor = Executors.newCachedThreadPool()
        val futures = people.map { person ->
            executor.submit(Callable {
                person.isAdult = person.age > 18
                person
            })
        }

        val filteredPeople = futures.map { it.get() }.filter { it.age > 15 }.sortedBy { it.age }

        assertResults(filteredPeople)

        executor.shutdown()
    }
}
