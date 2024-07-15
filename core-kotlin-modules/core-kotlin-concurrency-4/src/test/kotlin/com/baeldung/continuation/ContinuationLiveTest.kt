package com.baeldung.continuation

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import org.slf4j.LoggerFactory
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContinuationLiveTest {

    private val logger = LoggerFactory.getLogger("")

    private suspend fun doWork(name: String, delay: Long): String {
        logger.info("$name started")
        delay(delay)
        logger.info("$name resumed")
        return name
    }

    @Test
    fun `prove suspending`() = runBlocking {
        val dispatcher = Dispatchers.Default  // Use a dispatcher with a thread pool

        val job1 = launch(dispatcher) {
            doWork("Job 1", 2000)
        }

        val job2 = launch(dispatcher) {
            doWork("Job 2", 600)
        }

        val job3 = launch(dispatcher) {
            doWork("Job 3", 100)
        }

        job1.join()
        job2.join()
        job3.join()

        logger.info("All coroutines finished!")
    }

    @Test
    fun `test create continuation manually`(): Unit = runBlocking {
        val simpleContinuation = Continuation<Int>(Dispatchers.IO) { result ->
            assertEquals(45, result.getOrNull())
        }
        simpleContinuation.resumeWith(Result.success(45))
    }

    @Test
    fun `test continuation using suspendCoroutine`() = runBlocking {
        val result = suspendCoroutine { continuation ->
            continuation.resumeWith(Result.success("Baeldung"))
        }
        assertEquals("Baeldung", result)
    }

    // using resumeWith()
    private suspend fun simulateNetworkRequestResume(url: String): String {
        return withContext(Dispatchers.IO) {
            suspendCoroutine { continuation ->
                val result = try {
                    val connection = (URL(url).openConnection() as HttpURLConnection).apply {
                        requestMethod = "GET"
                        connectTimeout = 5000
                        readTimeout = 5000
                    }

                    val responseCode = connection.responseCode
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        Result.success("$responseCode")
                    } else {
                        Result.failure(Exception("$responseCode - Failed"))
                    }
                } catch (e: Exception) {
                    Result.failure(Exception("${e.message} - Failed"))
                }

                continuation.resumeWith(result)
            }
        }
    }

    // using resume() and resumeWithException()
    private suspend fun simulateNetworkRequest(url: String): String {
        return withContext(Dispatchers.IO) {
            suspendCoroutine { continuation ->
                try {
                    val connection = (URL(url).openConnection() as HttpURLConnection).apply {
                        requestMethod = "GET"
                        connectTimeout = 5000
                        readTimeout = 5000
                    }

                    val responseCode = connection.responseCode
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        continuation.resume("$responseCode")
                    } else {
                        continuation.resumeWithException(Exception("HTTP response code $responseCode - Failed"))
                    }
                } catch (e: Exception) {
                    continuation.resumeWithException(Exception("${e.message} - Failed"))
                }
            }
        }
    }

    @Test
    fun `test continuation using suspendFunction network call`(): Unit = runBlocking {
        assertEquals("200", simulateNetworkRequest("https://hangga.github.io"))

        val thrown = assertThrows<Exception> {
            simulateNetworkRequestResume("https://hangga.github.io/fail")
        }

        assertEquals("404 - Failed", throwed.message)
    }
}