package com.baeldung.parallelOperationsCollections

import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.stream.Collectors
import kotlin.test.assertEquals


class ParallelOperationCollectionsUnitTest {

    data class Person(val name: String, val age: Int)

    private val people = listOf(Person("Ahmad", 42), Person("Alina", 22), Person("Alice", 30), Person("Bob", 25), Person("Charlie", 40))

    @Test
    fun `using coroutines for parallel operations`(): Unit = runBlocking {
        val deferred = coroutineScope {
            people.map { async { it } }
        }

        val filteredPeople = deferred.map { it.await() }.filter { it.age > 30 }.sortedBy { it.age }

        val names = filteredPeople.map { it.name }.sorted()
        val totalAge = filteredPeople.sumOf { it.age }

        assertEquals(82, totalAge)
        assertThat(names).containsExactly("Ahmad", "Charlie")
        assertThat(filteredPeople).containsExactly(Person("Charlie", 40), Person("Ahmad", 42))
    }

    @Test
    fun `using coroutines for parallel operations with Flow`(): Unit = runBlocking {
        val filteredPeople = people.asFlow().map { async { it } }.buffer().map { it.await() }.filter { it.age > 30 }.toList()  // Convert the Flow to a list
            .sortedBy { it.age }

        val names = filteredPeople.map { it.name }.sorted()
        val totalAge = filteredPeople.sumOf { it.age }

        assertEquals(82, totalAge)
        assertThat(names).containsExactly("Ahmad", "Charlie")
        assertThat(filteredPeople).containsExactly(Person("Charlie", 40), Person("Ahmad", 42))
    }

    @Test
    fun `using RxJava for parallel operations`() { // Observable.class from io.reactivex;
        val observable = Observable.fromIterable(people).flatMap { Observable.just(it).subscribeOn(Schedulers.computation()) }.filter { it.age > 30 }.toList().blockingGet().sortedBy { it.age }

        val names = observable.map { it.name }.sorted()
        val totalAge = observable.sumOf { it.age }

        assertEquals(82, totalAge)
        assertThat(names).containsExactly("Ahmad", "Charlie")
        assertThat(observable).containsExactly(Person("Charlie", 40), Person("Ahmad", 42))
    }

    @Test
    fun `using RxKotlin for parallel operations`() { // ObservableKt.kt.class from io.reactivex.rxkotlin
        val observable = people.toObservable().flatMap { Observable.just(it).subscribeOn(Schedulers.computation()) }.filter { it.age > 30 }.toList().blockingGet().sortedBy { it.age }

        val names = observable.map { it.name }.sorted()
        val totalAge = observable.sumOf { it.age }

        assertEquals(82, totalAge)
        assertThat(names).containsExactly("Ahmad", "Charlie")
        assertThat(observable).containsExactly(Person("Charlie", 40), Person("Ahmad", 42))
    }

    @Test
    fun `using parallelStream()`() {
        val persons = people.parallelStream().filter { it.age > 30 }.sorted { p1, p2 -> p1.age.compareTo(p2.age) }.peek { println(it) } // Optional: for debugging, prints each element
            .collect(Collectors.toList())

        val names = persons.map { it.name }.sorted()
        val totalAge = persons.sumOf { it.age }

        assertEquals(82, totalAge)
        assertThat(names).containsExactly("Ahmad", "Charlie")
        assertThat(persons).containsExactly(Person("Charlie", 40), Person("Ahmad", 42))
    }

    @Test
    fun `using ScheduledExecutorService for parallel operations`() {
        val executor = Executors.newScheduledThreadPool(2)
        val futures = people.map { person ->
            executor.submit(Callable {
                Thread.sleep(100) // Simulating some computation
                person
            })
        }

        val filteredPeople = futures.map { it.get() }.filter { it.age > 30 }.sortedBy { it.age }

        val names = filteredPeople.map { it.name }.sorted()
        val totalAge = filteredPeople.sumOf { it.age }

        assertEquals(82, totalAge)
        assertThat(names).containsExactly("Ahmad", "Charlie")
        assertThat(filteredPeople).containsExactly(Person("Charlie", 40), Person("Ahmad", 42))

        executor.shutdown()
    }

}
