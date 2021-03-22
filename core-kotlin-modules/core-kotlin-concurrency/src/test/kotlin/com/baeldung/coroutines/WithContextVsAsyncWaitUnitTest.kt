package com.baeldung.coroutines

import kotlinx.coroutines.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis

class WithContextVsAsyncWaitUnitTest {

    suspend fun doTheTask(delayMilli: Long): Thread {
        delay(delayMilli)
        return Thread.currentThread()
    }

    @Test
    fun givenTwoAsyncBlockWithNoDispatcher_whenRunningParallel_expectLessThanTwiceDelay() {
        runBlocking {
            val time = measureTimeMillis {
                val task1 = async { doTheTask(DELAY) }
                val task2 = async { doTheTask(DELAY) }
                task1.await()
                task2.await()
            }
            Assertions.assertTrue(time < DELAY * 2)
        }
    }


    @Test
    fun givenTwoAsyncBlockWithThreadPool_whenRunningSequentially_expectTwiceDelay() {
        runBlocking {
            val dispatcher = Executors.newFixedThreadPool(2).asCoroutineDispatcher()
            val time = measureTimeMillis {
                val task1 = async(dispatcher) { doTheTask(DELAY) }
                task1.await()
                val task2 = async(dispatcher) { doTheTask(DELAY) }
                task2.await()
            }
            Assertions.assertTrue(time >= DELAY * 2)
        }
    }


    @Test
    fun givenTwoAsyncBlockWithThreadPool_whenRunningParallel_expectTwoThreads() {
        runBlocking {
            val dispatcher = Executors.newFixedThreadPool(2).asCoroutineDispatcher()
            val task1 = async(dispatcher) { doTheTask(DELAY) }
            val task2 = async(dispatcher) { doTheTask(DELAY) }
            val thread1 = task1.await()
            val thread2 = task2.await()
            Assertions.assertNotEquals(thread1, thread2)
        }
    }


    @Test
    fun givenTwoWithContextBlockWithThreadPool_whenRunning_expectTwiceDelay() {
        runBlocking {
            val time = measureTimeMillis {
                val dispatcher = Executors.newFixedThreadPool(2).asCoroutineDispatcher()
                withContext(dispatcher) {
                    doTheTask(DELAY)
                }
                withContext(dispatcher) {
                    doTheTask(DELAY)
                }
            }
            Assertions.assertTrue(time >= DELAY * 2)
        }
    }


    @Test
    fun givenAsyncBlockInRunCatching_whenThrowsException_expectNotCatch() {
        Assertions.assertThrows(
                Exception::class.java) {
            runBlocking {
                kotlin.runCatching {
                    async(Dispatchers.Default) {
                        doTheTask(DELAY)
                        throw Exception("Exception")
                    }.await()
                }
            }
        }
    }

    @Test
    fun givenWithContextBlockInRunCatching_whenThrowsException_expectCatched() {
        runBlocking {
            kotlin.runCatching {
                withContext(Dispatchers.Default) {
                    doTheTask(DELAY)
                    throw Exception("Exception")
                }
            }
        }
    }

    companion object {
        private const val DELAY = 1000L
    }

}


