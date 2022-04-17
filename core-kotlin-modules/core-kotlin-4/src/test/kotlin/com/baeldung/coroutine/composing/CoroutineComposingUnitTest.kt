package com.baeldung.coroutine.composing

import kotlinx.coroutines.future.await
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import java.net.URL
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class CoroutineComposingUnitTest {

    @Test
    fun given_X_when_Y_then_Z() {

    }

    @Test
    fun when_implemented_naively_then_executes_but_may_block() = runBlocking {
        UserInput(query = "I feel lucky").let {
            DataProcessUseCase(dispatcher).processCallAsync(it).await()
        }
        Unit // to make JUnit happy
    }

    @Test
    fun when_io_offloaded_to_another_context_then_executes_with_more_throughput() = runBlocking {
        UserInput(query = "I feel lucky").let {
            DataProcessUseCase(dispatcher).processCallEfficientlyAsync(it).await()
        }
        Unit // to make JUnit happy
    }

    @Test
    fun when_io_offloaded_to_completable_future_supplier_then_also_executes_with_more_throughput() = runBlocking {
        UserInput(query = "I feel lucky").let {
            val completableFuture = DataProcessUseCase(dispatcher).storeToDatabaseAsync(it, "Amazing result")
            completableFuture.await() // Await when we need a result
        }
        Unit // to make JUnit happy
    }

    @Test
    fun given_urls_that_succeed_when_called_at_once_then_produces_results() = runBlocking {
        val input = UserInput(query = "I feel lucky")
        val results = listOf(DataProcessUseCase.urlA, DataProcessUseCase.urlB, DataProcessUseCase.urlB)
          .let { DataProcessUseCase(dispatcher).callManyHttpUrlsAtOnce(input, it) }
        assertEquals(3, results.size)
        results.forEach { assertNotNull(it) }
    }

    @Test
    fun given_urls_that_partly_fails_when_called_at_once_then_partly_succeeds() = runBlocking {
        val input = UserInput(query = "I feel lucky")
        val results = listOf(DataProcessUseCase.urlA, DataProcessUseCase.urlB, URL("https://fail.com"))
          .let { DataProcessUseCase(dispatcher).callManyHttpUrlsAtOnceIgnoreErrors(input, it) }
        assertEquals(2, results.size)
        val (one, two) = results.map { it.body }
        assertEquals("Hello, world!", one + two)
    }

    @Test
    fun when_fired_in_launch_then_just_runs() {
        val input = UserInput(query = "I feel lucky")
        with(DataProcessUseCase(dispatcher)) {
            launch {
                processCallEfficientlyAsync(input).await()
            }
        }
    }
}