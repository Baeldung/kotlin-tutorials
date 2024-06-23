package com.baeldung.continuation

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.test.assertEquals

class ContinuationUnitTest {

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
        simpleContinuation.resume(45)
    }

    @Test
    fun `test continuation using suspendCoroutine`() = runBlocking {
        val result = suspendCoroutine { continuation ->
            continuation.resume("Baeldung")
        }
        assertEquals("Baeldung", result)
    }

    private suspend fun simpleSuspendFunctionContinuation(): String {
        delay(1000L)
        return suspendCoroutine { continuation ->
            continuation.resume("Baeldung")
        }
    }

    @Test
    fun `test continuation using simple suspend function`() = runBlocking {
        val result = simpleSuspendFunctionContinuation()
        assertEquals("Baeldung", result)
    }

    @Test
    fun `test continuation using async`() = runBlocking {
        val deferred = async {
            suspendCoroutine { continuation ->
                continuation.resume("Baeldung")
            }
        }.await()
        assertEquals("Baeldung", deferred)
    }

    // using resumeWith()
    private suspend fun simulateNetworkRequestResume(url: String): String {
        return withContext(Dispatchers.IO){
            suspendCoroutine { continuation ->
                val result = try {
                    val connection = (URL(url).openConnection() as HttpURLConnection).apply {
                        requestMethod = "GET"
                        connectTimeout = 5000
                        readTimeout = 5000
                    }

                    val responseCode = connection.responseCode
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        Result.success("$url : HTTP response code $responseCode - Ok")
                    } else {
                        Result.failure(Exception("$url: HTTP response code $responseCode - Failed"))
                    }
                } catch (e: Exception) {
                    Result.failure(Exception("$url: ${e.message} - Failed"))
                }

                continuation.resumeWith(result)
            }
        }
    }

    // using resume() and resumeWithException()
    private suspend fun simulateNetworkRequest(url: String): String {
        return withContext(Dispatchers.IO){
            suspendCoroutine { continuation ->
                try {
                    val connection = (URL(url).openConnection() as HttpURLConnection).apply {
                        requestMethod = "GET"
                        connectTimeout = 5000
                        readTimeout = 5000
                    }

                    val responseCode = connection.responseCode
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        continuation.resume("$url : HTTP response code $responseCode - OK")
                    } else {
                        continuation.resumeWithException(Exception("$url: HTTP response code $responseCode - Failed"))
                    }
                } catch (e: Exception) {
                    continuation.resumeWithException(Exception("$url: ${e.message} - Failed"))
                }
            }
        }
    }

    @Test
    fun `test continuation using suspendFunction network call`() = runBlocking {
        val urls = listOf(
            "https://example.com",
            "https://example.com/fail",
            "https://example.com/fail1",
            "https://baeldung.com",
            "https://www.baeldung.com/linux/",
            "https://www.baeldung.com/kotlin/",
            "https://www.baeldung.com/scala/",
            "https://hangga.github.io"
        )

        for (url in urls) {
            try {
                val response = simulateNetworkRequestResume(url)
                logger.info("0. $response")
            } catch (e: Exception) {
                logger.error("0. ${e.message}")
            }

            try {
                val response = simulateNetworkRequest(url)
                logger.info("1. $response")
            } catch (e: Exception) {
                logger.error("1. ${e.message}")
            }
        }
    }
}