package com.baeldung.threadsvscoroutines

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test

class CoroutineUnitTest {

    @Test
    fun whenCreateCoroutineWithLaunchWithoutContext_thenRun() = runBlocking {

        launch {
            println("${Thread.currentThread()} has run.")
        }

    }

    @Test
    fun whenCreateCoroutineWithLaunchWithDefaultContext_thenRun() = runBlocking {

        launch(Dispatchers.Default) {
            println("${Thread.currentThread()} has run.")
        }
    }

    @Test
    fun whenCreateCoroutineWithLaunchWithUnconfinedContext_thenRun() = runBlocking {

        launch(Dispatchers.Unconfined) {
            println("${Thread.currentThread()} has run.")
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    @Test
    fun whenCreateCoroutineWithLaunchWithDedicatedThread_thenRun() = runBlocking {

        launch(newSingleThreadContext("dedicatedThread")) {
            println("${Thread.currentThread()} has run.")
        }

    }

    @Test
    fun whenCreateAsyncCoroutine_thenRun() = runBlocking {

        val deferred = async(Dispatchers.IO) {
            return@async "${Thread.currentThread()} has run."
        }

        val result = deferred.await()
        println(result)
    }
}