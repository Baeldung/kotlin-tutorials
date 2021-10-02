package com.baeldung.coroutines.cancellation


import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import org.junit.jupiter.api.Test

class CancellationCasesUnitTest {
    @Test
    fun `when cancelled then exits the endless loop with exception`() = runBlocking {
        val job = launch {
            println("job: I'm a long-running coroutine")
            while (true) {
                try {
                    delay(30L)
                } catch (ex: CancellationException) {
                    println("job: I have been cancelled, what a pity.")
                    throw ex
                }
            }
        }
        delay(100L)
        println("main: That's enough, let's cancel you")
        job.cancelAndJoin()
    }

    @Test
    fun `when cancelled then exits the long loop but prints final words`() = runBlocking {
        val job = with(CoroutineScope(Dispatchers.Default)) {
            launch {
                val allPrimes = mutableSetOf<Long>()
                var i = 1L
                while (i++ < Long.MAX_VALUE && coroutineContext.isActive) {
                    if (allPrimes.none { i % it == 0L })
                        allPrimes.add(i)
                }
                println("job: The biggest primes was: ${allPrimes.last()}")
            }
        }
        delay(100L)
        println("main: That's enough, let's cancel you")
        job.cancelAndJoin()
    }

    @Test
    fun `when parent cancelled then the child is cancelled too`() = runBlocking {
        val parentJob = with(CoroutineScope(Dispatchers.Default)) {
            launch {
                println("parent job: I am a parent job")
                launch {
                    println("child job: I am a child job")
                    try {
                        delay(30000L)
                    } finally {
                        println("child job: I got cancelled")
                        println("child job: But I got lots to do before stopping")
                        println("child job: My parent will wait for me")
                    }
                }
                try {
                    delay(30000L)
                } finally {
                    println("parent job: I got cancelled")
                }
            }
        }
        delay(100L)
        println("main: That's enough, let's cancel you")
        parentJob.cancelAndJoin()
    }

    @Test
    fun `when the scope cancelled then all coroutines in it are cancelled too`() = runBlocking {
        val commonContext = CoroutineScope(Dispatchers.Default)
        val jobs = (1..4).map { jobNumber ->
            commonContext.launch {
                println("I am a job $jobNumber")
                try {
                    delay(30000L)
                } catch (ex: Exception) {
                    println("job $jobNumber: I got cancelled with message: ${ex.message}")
                }
            }
        }
        delay(100L)
        commonContext.cancel("We don't want you anymore")
        jobs.forEach { it.join() }
    }

    @Test
    fun `when the time is out then the call is cancelled`() = runBlocking {
        with(CoroutineScope(Dispatchers.Default)) {
            val deferredResult = async {
                println("My job is to provide a result, by I am going to be slow about it")
                delay(30_000L)
                42
            }
            try {
                val result = withTimeout(100) { deferredResult.await() }
            } catch (ex: TimeoutCancellationException) {
                println("Time is up")
            }
        }
    }


    @Test
    fun `when the coroutine is cancelled then non-cancellable block still works`() = runBlocking {

        val job = launch {
            println("job: I'm a long-running coroutine")
            while (true) {
                try {
                    delay(30L)
                } catch (ex: CancellationException) {
                    println("job: I have been cancelled, what a pity.")
                    throw ex
                } finally {
                    withContext(NonCancellable) {
                        delay(30)
                        println("job: But I have so many things to do yet!")
                    }
                }
            }
        }

        delay(100L)
        println("main: That's enough, let's cancel you")
        job.cancelAndJoin()

    }
}