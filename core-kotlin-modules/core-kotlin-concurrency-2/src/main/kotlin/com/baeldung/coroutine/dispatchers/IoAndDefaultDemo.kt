package com.baeldung.coroutine.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.json.JSONObject

class IoAndDefaultDemo {
    private val ioDispatcher = Dispatchers.IO
    private val defaultDispatcher = Dispatchers.Default

    suspend fun switchDispatcher(dispatcher: CoroutineDispatcher) {
        println("Started execution on ${Thread.currentThread().name}")

        withContext(dispatcher) {
            // This code block will execute on the given dispatcher's thread
            println("Now executing on ${Thread.currentThread().name}")
        }
    }

    suspend fun fetchData(): String {
        return withContext(ioDispatcher) {
            // perform network request to fetch data
            val response = networkRequest()

            // parse response into a String
            val result = parseResponse(response)

            return@withContext result
        }
    }

    suspend fun calculateSum(a: Int, b: Int): Int {
        return withContext(defaultDispatcher) {
            // perform CPU-bound calculation
            val sum = a + b

            return@withContext sum
        }
    }

    /**
     * Just an example of a function that parses a network response.
     */
    private fun parseResponse(response: String): String {
        return JSONObject(response).getString("result")
    }

    /**
     * Simulates a long-running network request, returns a sample JSON response.
     */
    private suspend fun networkRequest(): String {
        delay(1000)
        return "{\"result\":\"result string\"}"
    }

}