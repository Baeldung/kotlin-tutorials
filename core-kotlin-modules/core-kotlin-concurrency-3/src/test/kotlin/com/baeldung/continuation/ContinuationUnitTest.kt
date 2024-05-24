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
import kotlin.random.Random
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
        return suspendCoroutine { continuation ->
            CoroutineScope(Dispatchers.IO).launch {
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

    private suspend fun simulateRandomDivisionResumeWith(): Double {
        return suspendCoroutine { continuation ->
            CoroutineScope(Dispatchers.Default).launch {
                delay(1000)
                val result: Result<Double> = try {
                    val numerator = Random.nextInt(0, 100)
                    val denominator = Random.nextInt(0, 10)

                    if (denominator == 0) throw ArithmeticException("Division by zero")

                    Result.success(numerator.toDouble() / denominator)
                } catch (e: ArithmeticException) {
                    Result.failure(e)
                } catch (e: Exception) {
                    Result.failure(e)
                }

                continuation.resumeWith(result)
            }
        }
    }

    private suspend fun simulateRandomDivisionResume(): Double {
        return suspendCoroutine { continuation ->
            CoroutineScope(Dispatchers.Default).launch {
                delay(1000)
                try {
                    val numerator = Random.nextInt(0, 100)
                    val denominator = Random.nextInt(0, 10)

                    if (denominator == 0) throw ArithmeticException("Division by zero")
                    continuation.resume(numerator.toDouble() / denominator)
                } catch (e: ArithmeticException) {
                    continuation.resumeWithException(e)
                } catch (e: Exception) {
                    continuation.resumeWithException(e)
                }
            }
        }
    }

    @Test
    fun `test using suspend function simulate random division`(): Unit = runBlocking{
        for (x in (0..20)) {
            try {
                logger.info(" ${simulateRandomDivisionResume()} ")
            }catch (e : Exception){
                logger.error(e.message)
            }

            try {
                logger.info(" ${simulateRandomDivisionResumeWith()} ")
            }catch (e : Exception){
                logger.error(e.message)
            }
        }
    }

}