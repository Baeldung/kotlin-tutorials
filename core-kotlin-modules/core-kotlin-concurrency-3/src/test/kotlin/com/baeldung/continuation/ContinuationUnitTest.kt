package com.baeldung.continuation

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.test.assertEquals

class ContinuationUnitTest {

    private val logger = LoggerFactory.getLogger("")

    @Test
    fun `test create continuation manually`() {
        val simpleContinuation = Continuation<Int>(Dispatchers.IO) { result ->
            assertEquals(45, result.getOrNull())
        }
        simpleContinuation.resume(45)
    }

    @Test
    fun `test continuation using suspendCoroutine`() = runBlocking {
        val result = suspendCoroutine { continuation ->
            thread {
                Thread.sleep(1000) //  is just for demo purposes
                continuation.resume("Baeldung")
            }
        }
        assertEquals("Baeldung", result)
    }

    private suspend fun simpleSuspendFunction(): String {
        return suspendCoroutine { continuation ->
            thread {
                Thread.sleep(1000) //  is just for demo purposes
                continuation.resume("Baeldung")
            }
        }
    }

    @Test
    fun `test continuation using simple suspend function`() = runBlocking {
        val result = simpleSuspendFunction()
        assertEquals("Baeldung", result)
    }

    @Test
    fun `test continuation using async`() = runBlocking{
        val deferred = async {
            suspendCoroutine { continuation ->
                continuation.resume("Baeldung")
            }
        }.await()
        assertEquals("Baeldung", deferred)
    }

    private suspend fun simulateNetworkRequestResume(url: String): String {
        return suspendCoroutine { continuation ->
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val connection = (URL(url).openConnection() as HttpURLConnection).apply {
                        requestMethod = "GET"
                        connectTimeout = 5000
                        readTimeout = 5000
                    }

                    val responseCode = connection.responseCode
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        continuation.resumeWith(Result.success("$url : HTTP response code $responseCode - Ok"))
                    } else {
                        continuation.resumeWith(Result.failure(Exception("$url: HTTP response code $responseCode - Failed")))
                    }
                } catch (e: Exception) {
                    continuation.resumeWithException(Exception("$url: ${e.message} - Failed"))
                }
            }
        }
    }

    private suspend fun simulateNetworkRequest(url: String): String {
        return suspendCoroutine { continuation ->
            CoroutineScope(Dispatchers.IO).launch {
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