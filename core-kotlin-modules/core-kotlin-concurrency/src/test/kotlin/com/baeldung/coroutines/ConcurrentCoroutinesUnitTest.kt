package com.baeldung.coroutines

import kotlinx.coroutines.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger

class ConcurrentCoroutinesUnitTest {
    @Test
    fun whenAwaitAsyncCoroutines_thenAllTerminated() {
        val count = AtomicInteger()
        runBlocking {
            val tasks = listOf(
                async(Dispatchers.IO) { count.addAndGet(longRunningTask()) },
                async(Dispatchers.IO) { count.addAndGet(longRunningTask()) }
            )
            tasks.awaitAll()
            Assertions.assertEquals(2, count.get())
        }
    }

    @Test
    fun whenJoinLaunchedCoroutines_thenAllTerminated() {
        val count = AtomicInteger()
        runBlocking {
            val tasks = listOf(
                launch(Dispatchers.IO) { count.addAndGet(longRunningTask()) },
                launch(Dispatchers.IO) { count.addAndGet(longRunningTask()) }
            )
            tasks.joinAll()
            Assertions.assertEquals(2, count.get())
        }
    }


    @Test
    fun whenParentCoroutineRunAsyncCoroutines_thenAllTerminated() {
        val count = AtomicInteger()
        runBlocking {
            withContext(coroutineContext) {
                async(Dispatchers.IO) { count.addAndGet(longRunningTask()) }
                async(Dispatchers.IO) { count.addAndGet(longRunningTask()) }
            }
            Assertions.assertEquals(2, count.get())
        }
    }

    @Test
    fun whenParentCoroutineLaunchCoroutines_thenAllTerminated() {
        val count = AtomicInteger()
        runBlocking {
            withContext(coroutineContext) {
                launch(Dispatchers.IO) { count.addAndGet(longRunningTask()) }
                launch(Dispatchers.IO) { count.addAndGet(longRunningTask()) }
            }
            Assertions.assertEquals(2, count.get())
        }
    }


    private suspend fun longRunningTask(): Int {
        delay(1000)
        return 1
    }

}
