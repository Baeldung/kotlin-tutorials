package com.baeldung.coroutines.cancellation

import kotlinx.coroutines.*
import org.junit.Test
import org.junit.jupiter.api.Assertions
import java.util.concurrent.atomic.AtomicInteger

class CoroutinesCancellationUnitTest {

    @Test
    fun whenCoroutineCancelled_thenCounterEquals3() {
        val count = AtomicInteger()
        runBlocking {
            val job = CoroutineScope(Dispatchers.Default).launch {
                repeat(1000) {
                    count.addAndGet(1)
                    delay(400L)
                }
            }
            delay(1100L)
            job.cancel()
            Assertions.assertEquals(3, count.get())
        }
    }

    @Test
    fun whenCoroutineScopeCancelled_thenCounter1Equals3AndCounter2Equals2() {
        val count1 = AtomicInteger()
        val count2 = AtomicInteger()
        runBlocking {
            val scope = CoroutineScope(Dispatchers.Default).launch {
                val job1 = launch {
                    repeat(1000) { i ->
                        count1.addAndGet(1)
                        delay(400L)
                    }
                }

                val job2 = launch {
                    repeat(1000) { i ->
                        count2.addAndGet(1)
                        delay(600L)
                    }
                }

            }
            delay(1100L)
            scope.cancel()
            Assertions.assertEquals(3, count1.get())
            Assertions.assertEquals(2, count2.get())
        }
    }

    @Test
    fun whenLongComputationCoroutineCancel_thenItRunsUntilCompleteAndCounterEquals5() {
        val count = AtomicInteger()
        runBlocking {
            val start = System.currentTimeMillis()
            val job = launch(Dispatchers.Default) {
                var next = start
                var i = 0
                while (i < 5) {
                    if (System.currentTimeMillis() >= next) {
                        i++
                        count.addAndGet(1)
                        next += 400L
                    }
                }
            }
            delay(1100L)
            job.cancel()
            Assertions.assertEquals(3, count.get())

            delay(500L)
            Assertions.assertEquals(5, count.get())
        }
    }

    @Test
    fun givenPeriodicalCheckForIsActive_whenCoroutineCancel_thenStopCountAndCounterEquals3() {
        val count = AtomicInteger()
        runBlocking {
            val start = System.currentTimeMillis()
            val job = launch(Dispatchers.Default) {
                var next = start
                var i = 0
                while (isActive && i < 5) {
                    if (System.currentTimeMillis() >= next) {
                        i++
                        count.addAndGet(1)
                        next += 400L
                    }
                }
            }
            delay(1100L)
            job.cancel()
            Assertions.assertEquals(3, count.get())

            delay(500L)
            Assertions.assertEquals(3, count.get())
        }
    }

    @Test
    fun givenPeriodicalCheckWithEnsureActive_whenCoroutineCancel_thenStopCountAndCounterEquals3() {
        val count = AtomicInteger()
        runBlocking {
            val start = System.currentTimeMillis()
            val job = launch(Dispatchers.Default) {
                var next = start
                var i = 0
                while (i < 5) {
                    ensureActive()
                    if (System.currentTimeMillis() >= next) {
                        i++
                        count.addAndGet(1)
                        next += 400L
                    }
                }
            }
            delay(1100L)
            job.cancel()
            Assertions.assertEquals(3, count.get())

            delay(500L)
            Assertions.assertEquals(3, count.get())
        }
    }

    @Test
    fun givenYieldCallBeforeComputation_whenCoroutineCancel_thenStopCountAndCounterEquals3() {
        val count = AtomicInteger()
        runBlocking {
            val start = System.currentTimeMillis()
            val job = launch(Dispatchers.Default) {
                var next = start
                var i = 0
                while (i < 5) {
                    yield()
                    if (System.currentTimeMillis() >= next) {
                        i++
                        count.addAndGet(1)
                        next += 400L
                    }
                }
            }
            delay(1100L)
            job.cancel()
            Assertions.assertEquals(3, count.get())

            delay(500L)
            Assertions.assertEquals(3, count.get())
        }
    }

    @Test
    fun givenWaitForJobCompletion_whenCoroutineCancelled_thenCounterEquals5() {
        val count = AtomicInteger()
        runBlocking {
            val start = System.currentTimeMillis()
            val job = launch(Dispatchers.Default) {
                var next = start
                var i = 0
                while (i < 5) {
                    if (System.currentTimeMillis() >= next) {
                        i++
                        count.addAndGet(1)
                        next += 400L
                    }
                }
            }
            delay(1100L)
            job.cancel()
            job.join()
            Assertions.assertEquals(5, count.get())
        }
    }

    @Test
    fun givenResource_whenCoroutineCancelled_thenReleaseResource() {
        runBlocking {
            val job = launch {
                var resource: Resource? = null
                try {
                    Assertions.assertEquals(0, acquired)
                    resource = Resource()
                    repeat(1000) {
                        Assertions.assertEquals(1, acquired)
                        delay(400L)
                    }
                } finally {
                    resource?.close()
                }
            }
            delay(1100L)
            job.cancelAndJoin()
            Assertions.assertEquals(0, acquired)
        }
    }

    @Test
    fun givenResourceWithSuspendRelease_whenCoroutineCancelled_thenReleaseResource() {
        runBlocking {
            val job = launch {
                var resource: ResourceWithSuspension? = null
                try {
                    Assertions.assertEquals(0, acquiredWithSuspension)
                    resource = ResourceWithSuspension()
                    repeat(1000) {
                        Assertions.assertEquals(1, acquiredWithSuspension)
                        delay(400L)
                    }
                } finally {
                    withContext(NonCancellable) {
                        resource?.close()
                    }
                }
            }
            delay(1100L)
            job.cancelAndJoin()
            Assertions.assertEquals(0, acquiredWithSuspension)
        }
    }
}