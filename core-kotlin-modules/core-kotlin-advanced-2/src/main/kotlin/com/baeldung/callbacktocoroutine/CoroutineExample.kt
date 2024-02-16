package com.baeldung.callbacktocoroutine

import kotlinx.coroutines.*

class CoroutineExample {
    suspend fun fetchData(): String {
        // Simulating an asynchronous operation
        return processData()
    }

    suspend fun processData(): String = withContext(Dispatchers.IO) {
        // Simulating a long-running operation
        delay(1000)
        return@withContext "processed-data"
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun main() {
        // Calling the fetchData function within a coroutine scope
        GlobalScope.launch {
            try {
                val result = fetchData()
                // Handle successful result
                println("Result is: $result")
            } catch (error: Throwable) {
                // Handle error
            }
        }
    }

}