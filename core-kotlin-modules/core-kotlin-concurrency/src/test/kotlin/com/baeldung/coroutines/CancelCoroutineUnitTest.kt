package com.baeldung.coroutines

import kotlinx.coroutines.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.concurrent.atomic.AtomicInteger

internal class CancelCoroutineUnitTest {

    @Test
    fun givenCancellableCoroutine_whenRequestForCancel_thenShouldQuit() {
        runBlocking<Unit> {
            //given
            val job = launch {
                repeat(10000) {
                    delay(500L)
                }
            }
            //when
            delay(1000L)
            job.cancelAndJoin()
            //then
            kotlin.test.assertFalse(job.isActive)
        }
    }

    @Test
    fun givenComputingCoroutine_whenRequestForCancel_thenShouldProceed() {
        runBlocking<Unit> {
            //given
            val startTime = System.currentTimeMillis()
            val job = launch(Dispatchers.Default) {
                var subsequentTime = startTime
                var i = 0
                while (i < 7) {
                    if (System.currentTimeMillis() >= subsequentTime) {
                        i++
                        subsequentTime += 500L
                    }
                }
            }
            //when
            delay(1000L)
            job.cancel()
            //then
            kotlin.test.assertFalse(job.isActive)
            kotlin.test.assertFalse(job.isCompleted)
        }
    }

    @Test
    fun givenComputingCoroutine_whenRequestForCancel_thenShouldQuit() {
        runBlocking<Unit> {
            //given
            val startTime = System.currentTimeMillis()
            val job = launch(Dispatchers.Default) {
                var subsequentTime = startTime
                var i = 0
                while (isActive) {
                    if (System.currentTimeMillis() >= subsequentTime) {
                        i++
                        subsequentTime += 500L
                    }
                }
            }
            //when
            delay(1000L)
            job.cancelAndJoin()
            //then
            kotlin.test.assertFalse(job.isActive)
            kotlin.test.assertTrue(job.isCompleted)
        }
    }


}