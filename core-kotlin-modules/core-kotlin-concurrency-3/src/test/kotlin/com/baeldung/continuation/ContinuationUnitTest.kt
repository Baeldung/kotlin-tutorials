package com.baeldung.continuation

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.test.assertEquals

class ContinuationUnitTest {

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
            continuation.resume("Baeldung")
        }
        assertEquals("Baeldung", result)
    }

    private suspend fun simpleSuspendFunction(): String {
        return suspendCoroutine { continuation ->
            continuation.resume("Baeldung")
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

    private suspend fun simulateNetworkRequest(url: String): String {
        return suspendCoroutine { continuation ->
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val urlObj = URL(url)
                    val connection = urlObj.openConnection() as HttpURLConnection
                    connection.apply {
                        requestMethod = "GET"
                        connectTimeout = 5000
                        readTimeout = 5000
                    }

                    val responseCode = connection.responseCode
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        continuation.resume("Successful request to $url : HTTP response code $responseCode")
                    } else {
                        continuation.resumeWithException(Exception("Failed to fetch data from $url: HTTP response code $responseCode"))
                    }
                } catch (e: Exception) {
                    continuation.resumeWithException(Exception("Failed to fetch data from $url: ${e.message}"))
                }
            }
        }
    }

    @Test
    fun `test continuation using suspendFunction network call`() = runBlocking {
        val urls = listOf(
            "https://example.com",
            "https://example.com/fail",
            "https://example.com/fail2",
            "https://baeldung.com",
            "https://hangga.github.io"
        )

        for (url in urls) {
            try {
                val response = simulateNetworkRequest(url)
                println(response)
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }
}