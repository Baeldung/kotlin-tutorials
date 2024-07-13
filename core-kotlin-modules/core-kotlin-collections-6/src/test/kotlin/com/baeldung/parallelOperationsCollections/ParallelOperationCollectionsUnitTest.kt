package com.baeldung.parallelOperationsCollections

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.toObservable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.time.Duration
import java.time.Instant
import java.util.stream.Collectors


class ParallelOperationCollectionsUnitTest {

    private val logger = LoggerFactory.getLogger("")

    data class Person(val name: String, val age: Int, var isAdult: Boolean? = null)

    private val people = listOf(
        Person("Martin", 12),
        Person("Ahmad", 42),
        Person("Alina", 13),
        Person("Alice", 30),
        Person("Bob", 16),
        Person("Charlie", 40)
    )

    private fun List<Person>.assertOver15AndSortedByAge() {
        assertThat(this).containsExactly(
            Person("Bob", 16, false),
            Person("Alice", 30, true),
            Person("Charlie", 40, true),
            Person("Ahmad", 42, true)
        )
    }

    private fun Person.setAdult() {
        this.isAdult = this.age >= 18
        logger.info(this.toString())
    }
    
    private fun Instant.printTotalTime() {
        val totalTime = Duration.between(this, Instant.now()).toMillis()
        logger.info("Total time taken: {} ms", totalTime)
    }

    @Test
    fun `using coroutines for parallel operations`() = runBlocking {
        logger.info("Using Coroutines")
        val startTime = Instant.now()

        val filteredPeople = people
            .map { person ->
                async(Dispatchers.IO) {
                    Thread.sleep(1500)
                    person.setAdult()
                    person
                }
            }.awaitAll()
            .filter { it.age > 15 }
            .sortedBy { it.age }

        startTime.printTotalTime()

        filteredPeople.assertOver15AndSortedByAge()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `using coroutines for parallel operations with Flow`() = runBlocking {
        logger.info("Using Kotlin Flow")
        val startTime = Instant.now()

        val filteredPeople = people.asFlow()
            .flatMapMerge { person ->
                flow {
                    person.setAdult()
                    Thread.sleep(1500)
                    emit(person)
                }
            }
            .flowOn(Dispatchers.IO)
            .filter { it.age > 15 }.toList()
            .sortedBy { it.age }

        startTime.printTotalTime()

        filteredPeople.assertOver15AndSortedByAge()
    }

    @Test
    fun `using RxJava for parallel operations`() { // Observable.class from io.reactivex;
        logger.info("Using RxJava")
        val startTime = Instant.now()

        Observable.fromIterable(people)
            .flatMap(
                {
                    Observable.just(it).subscribeOn(Schedulers.computation()).doOnNext { person ->
                        person.setAdult()
                        Thread.sleep(1500)
                    }
                }, people.size // Uses maxConcurrency for the number of elements
            )
            .filter { it.age > 15 }
            .toSortedList { person1, person2 -> person1.age.compareTo(person2.age) }
            .subscribe({ sortedList ->
                startTime.printTotalTime()
                sortedList.assertOver15AndSortedByAge()
            }, { error ->
                logger.error("Error occurred: $error")
            })
    }

    @Test
    fun `using RxKotlin for parallel operations`() { // ObservableKt.kt.class from io.reactivex.rxkotlin
        logger.info("Using RxKotlin")
        val startTime = Instant.now()

        people.toObservable()
            .flatMap(
                {
                    Observable.just(it).subscribeOn(Schedulers.computation()).doOnNext { person ->
                        person.setAdult()
                        Thread.sleep(1500)
                    }
                }, people.size // Uses maxConcurrency for the number of elements
            )
            .filter { it.age > 15 }
            .toSortedList { person1, person2 -> person1.age.compareTo(person2.age) }
            .subscribe({ sortedList ->
                startTime.printTotalTime()
                sortedList.assertOver15AndSortedByAge()
            }, { error ->
                logger.error("Error occurred: $error")
            })
    }

    @Test
    fun `using parallelStream()`() {
        logger.info("Using Stream API")
        val startTime = Instant.now()

        val filteredPeople = people.parallelStream()
            .map { person ->
                person.setAdult()
                Thread.sleep(1500)
                person
            }.filter { it.age > 15 }
            .sorted { p1, p2 -> p1.age.compareTo(p2.age) }
            .collect(Collectors.toList())

        startTime.printTotalTime()

        filteredPeople.assertOver15AndSortedByAge()
    }
}

