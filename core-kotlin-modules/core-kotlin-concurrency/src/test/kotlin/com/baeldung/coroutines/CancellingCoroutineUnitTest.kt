package com.baeldung.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Test

class CancellingCoroutineUnitTest {
    @Test
    @ExperimentalCoroutinesApi
    fun coroutine_isCanceled() {
        runBlockingTest {
            val scope = CoroutineScope(coroutineContext)
            scope.launch {
                val job = launch {
                    while (true) { // infinite loop (long running work)
                        println("Job is running...")
                        delay(1000) // delays loop for one second
                    }
                }
                delay(500) // delays scope for half a second
                println("Cancelling...")
                job.cancel() // cancels job
                job.join() // waits for job to be completed
                println("Job is cancelled!")
                assertEquals(true, job.isCancelled)
            }
        }
    }

    @Test
    @ExperimentalCoroutinesApi
    fun usingIsActive_coroutine_isCanceled() {
        runBlocking(Dispatchers.IO) {
            val job = launch {
                while (true && isActive) { // infinite loop (long running work) AND checking if coroutine is still active
                    println("Job is running...")
                }
            }
            delay(500) // delays scope for half a second
            println("Cancelling...")
            job.cancelAndJoin() // cancels and waits for job to be completed
            println("Job is cancelled!")
            assertEquals(true, job.isCancelled)
        }
    }

    @Test
    @ExperimentalCoroutinesApi
    fun usingEnsureActive_coroutine_isCanceled() {
        runBlocking(Dispatchers.IO) {
            val job = launch {
                while (true) { // infinite loop (long running work)
                    ensureActive() // checking if coroutine is still active
                    println("Job is running...")
                }
            }
            delay(500) // delays scope for half a second
            println("Cancelling...")
            job.cancelAndJoin() // cancels and waits for job to be completed
            println("Job is cancelled!")
            assertEquals(true, job.isCancelled)
        }
    }

    @Test
    @ExperimentalCoroutinesApi
    fun usingTryCatch_exceptionInCoroutine_isHandled() {
        runBlockingTest {
            val scope = CoroutineScope(coroutineContext)
            scope.launch {
                try {
                    // computation that could throw an exception
                    doSomething()
                } catch (exception: Exception) {
                    println("Exception: ${exception.message}")
                    assertEquals("Thrown test exception message!", exception.message)
                    // handle exception here
                }
            }
        }
    }

    @Test
    @ExperimentalCoroutinesApi
    fun usingCoroutineExceptionHandler_exceptionInCoroutine_isHandled() {
        runBlockingTest {
            val scope = CoroutineScope(coroutineContext)
            val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
                println("CoroutineExceptionHandler Exception: ${exception.message}")
                assertEquals("Thrown test exception message!", exception.message)
                // handle exception here
            }

            scope.launch(coroutineExceptionHandler) {
                // computation that could throw an exception
                doSomething()
            }
        }
    }

    private fun doSomething() {
        throw Exception("Thrown test exception message!")
    }
}
