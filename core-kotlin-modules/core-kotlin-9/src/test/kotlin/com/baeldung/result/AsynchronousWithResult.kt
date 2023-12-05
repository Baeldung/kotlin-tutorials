package com.baeldung.result

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert.*
import org.junit.Test
import kotlin.test.assertFailsWith

suspend fun fetchDataAsync(apiEndpoint: String): Result<String> {
    return withContext(Dispatchers.IO) {
        try {
            // Simulate making an asynchronous network request
            delay(1000) // Simulating network delay
            val responseData = makeNetworkRequest(apiEndpoint)
            Result.success(responseData)
        } catch (e: Exception) {
            Result.failure(Exception("Error fetching data from the server: ${e.message}"))
        }
    }
}

suspend fun makeNetworkRequest(apiEndpoint: String): String {
    // Simulate making a network request
    return if (apiEndpoint.startsWith("https://api.example.com/")) {
        "{\"data\": \"Async API response data\"}"
    } else {
        throw IllegalArgumentException("Invalid API endpoint: $apiEndpoint")
    }
}

class AsynchronousWithResultTests {

    @Test
    fun `shouldFailToFetchDataInvalidEndpoint`() {
        runBlocking {
            // Test case 2: Failure due to an invalid API endpoint
            val result: Result<String> = fetchDataAsync("https://invalid.endpoint")

            assertTrue(result.isFailure)
            assertNotNull(result.exceptionOrNull())
            assertEquals("Error fetching data from the server: Invalid API endpoint: https://invalid.endpoint", result.exceptionOrNull()?.message)
        }
    }
}
