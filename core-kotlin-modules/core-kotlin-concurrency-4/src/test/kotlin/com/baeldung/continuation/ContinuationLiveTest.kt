package com.baeldung.continuation

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.slf4j.LoggerFactory
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.test.assertEquals

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
        val dispatcher = Dispatchers.Default

        val job1 = async(dispatcher) {
            doWork("Job 1", 2000)
        }

        val job2 = async(dispatcher) {
            doWork("Job 2", 600)
        }

        val job3 = async(dispatcher) {
            doWork("Job 3", 100)
        }

        assertEquals("Job 1", job1.await())
        assertEquals("Job 2", job2.await())
        assertEquals("Job 3", job3.await())

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

    private suspend fun usingResumeWith(url: String): String {
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
                    Result.failure(Exception(e.message))
                }

                continuation.resumeWith(result)
            }
        }
    }

    private suspend fun usingResumeAndResumeWithException(url: String): String {
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
                    continuation.resumeWithException(Exception(e.message))
                }
            }
        }
    }

    @Test
    fun `test continuation using suspendFunction network call success for usingResumeAndResumeWithException`() = runBlocking {
        assertEquals("200", usingResumeAndResumeWithException("https://hangga.github.io"))
    }

    @Test
    fun `test continuation using suspendFunction network call success for usingResumeWith`() = runBlocking {
        assertEquals("200", usingResumeWith("https://hangga.github.io"))
    }

    @Test
    fun `test continuation using suspendFunction network call failure for usingResumeAndResumeWithException`() = runBlocking {
        val thrown = assertThrows<Exception> {
            usingResumeAndResumeWithException("https://hangga.github.io/fail")
        }
        assertEquals("HTTP response code 404 - Failed", thrown.message)
    }

    @Test
    fun `test continuation using suspendFunction network call failure for usingResumeWith`() = runBlocking {
        val thrown = assertThrows<Exception> {
            usingResumeWith("https://hangga.github.io/fail")
        }
        assertEquals("404 - Failed", thrown.message)
    }

    @Test
    fun `test invalid URL for usingResumeAndResumeWithException`() = runBlocking {
        val thrown = assertThrows<Exception> {
            usingResumeAndResumeWithException("invalid-url")
        }
        assertEquals("no protocol: invalid-url", thrown.message)
    }

    @Test
    fun `test invalid URL for usingResumeWith`() = runBlocking {
        val thrown = assertThrows<Exception> {
            usingResumeWith("invalid-url")
        }
        assertEquals("no protocol: invalid-url", thrown.message)
    }

    @Test
    fun `test timeout for usingResumeAndResumeWithException`() = runBlocking {
        val thrown = assertThrows<Exception> {
            usingResumeAndResumeWithException("https://10.255.255.1") // An IP address that will timeout
        }
        assertEquals("Connect timed out", thrown.message)
    }

    @Test
    fun `test timeout for usingResumeWith`() = runBlocking {
        val thrown = assertThrows<Exception> {
            usingResumeWith("https://10.255.255.1")
        }
        assertEquals("Connect timed out", thrown.message)
    }
}