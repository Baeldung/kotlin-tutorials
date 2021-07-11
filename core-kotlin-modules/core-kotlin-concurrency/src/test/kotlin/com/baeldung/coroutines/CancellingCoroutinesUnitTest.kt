package com.baeldung.coroutines

import kotlinx.coroutines.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CancellingCoroutinesUnitTest {
    @Test
    fun givenCancellableCoroutine_whenCancelAndWaitTillCompletion_thenShouldBeCancelledAndCompleted() {
        runBlocking {
            val job = launch {
                while (true) {
                    delay(500L)
                }
            }

            delay(800L)
            job.cancelAndJoin()

            Assertions.assertFalse(job.isActive)
            Assertions.assertTrue(job.isCancelled)
            Assertions.assertTrue(job.isCompleted)
        }
    }

    @Test
    fun givenCancellableCoroutine_whenOnlyCancelled_thenShouldBeCancelledButNotCompleted() {
        runBlocking {
            val job = launch {
                while (true) {
                    delay(500L)
                }
            }

            delay(800L)
            job.cancel()

            Assertions.assertFalse(job.isActive)
            Assertions.assertTrue(job.isCancelled)
            Assertions.assertFalse(job.isCompleted)
        }
    }

    @Test
    fun givenCancellableScope_whenScopeIsCancelled_thenAllChildrenShouldBeCancelled() {
        val scope = MainScope()
        runBlocking {
            val job1 = scope.launch(Dispatchers.Default) {
                while (true) {
                    delay(500L)
                }
            }

            delay(500L)

            val job2 = scope.launch(Dispatchers.Default) {
                while (true) {
                    delay(500L)
                }
            }

            delay(500L)

            scope.cancel()

            Assertions.assertTrue(job1.isCancelled)
            Assertions.assertTrue(job2.isCancelled)
        }
    }

    @Test
    fun givenCancellableScope_whenOneJobIsCancelled_thenSiblingJobsShouldNotBeCancelled() {
        val scope = MainScope()
        runBlocking {
            val job1 = scope.launch(Dispatchers.Default) {
                while (true) {
                    delay(500L)
                }
            }

            delay(500L)

            val job2 = scope.launch(Dispatchers.Default) {
                while (true) {
                    delay(500L)
                }
            }

            delay(500L)

            job1.cancelAndJoin()

            Assertions.assertTrue(job1.isCancelled)
            Assertions.assertFalse(job2.isCancelled)
        }
    }
}