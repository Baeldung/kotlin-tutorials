package com.baeldung.coroutine

import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

object ConcurrentCoroutinesExamples {
    suspend fun doSomething() {
        val delay = Random.nextInt(100, 1000)
        delay(delay.milliseconds)
        println(" - doSomething waited for $delay milliseconds")
    }

    suspend fun doSomethingElse() {
        val delay = Random.nextInt(100, 1000)
        delay(delay.milliseconds)
        println(" - doSomethingElse waited for $delay milliseconds")
    }

    suspend fun executeTwoCoroutinesInParallelUsingLaunch() {
        coroutineScope {
            launch { doSomething() }
            launch { doSomethingElse() }
            println("Waiting for coroutines to finish")
        }
    }

    suspend fun executeTwoCoroutinesInParallelUsingAsync() {
        coroutineScope {
            val something = async { fetchSomething() }
            val somethingElse = async { fetchSomethingElse() }

            println("Waiting for coroutines to finish")
            println(
                "The sum of something and somethingElse " +
                    "is: ${something.await() + somethingElse.await()}",
            )
        }
    }

    suspend fun fetchSomething(): Int {
        val delay = Random.nextInt(100, 1000)
        delay(delay.milliseconds)
        println(" - fetchSomething waited for $delay milliseconds")
        return delay
    }

    suspend fun fetchSomethingElse(): Int {
        val delay = Random.nextInt(100, 1000)
        delay(delay.milliseconds)
        println(" - fetchSomethingElse waited for $delay milliseconds")
        return delay
    }

    suspend fun executeManyCoroutinesInParallelUsingAsync(): List<Int> {
        val result = coroutineScope {
            (1..5).map { n ->
                async {
                    val delay = Random.nextInt(100, 1000)
                    delay(delay.milliseconds)
                    println("- processing $n")
                    n * n
                }
            }.awaitAll()
        }
        println("Result:  $result")
        return result
    }
}
