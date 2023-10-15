package com.baeldung.callbacktocoroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class CallbackToCoroutineExample {
    interface Callback {
        fun onSuccess(result: String)
        fun onFailure(error: Throwable)
    }

    fun fetchDataWithCallback(callback: Callback) {
        // Simulating an asynchronous operation
        Thread {
            try {
                // Perform a long-running operation
                val processedData = processData()
                callback.onSuccess(processedData)
            } catch (e: Exception) {
                callback.onFailure(e)
            }
        }.start()
    }

    suspend fun fetchDataWithCoroutine(): String = suspendCoroutine { continuation ->
        fetchDataWithCallback(object : Callback {
            override fun onSuccess(result: String) {
                continuation.resume(result) // Resume the coroutine with the result
            }

            override fun onFailure(error: Throwable) {
                continuation.resumeWithException(error) // Resume the coroutine with an exception
            }
        })
    }


    fun processData(): String {
        Thread.sleep(1000)
        return "processed-data"
    }

    fun main() {
        // Calling the fetchData function within a coroutine scope
        GlobalScope.launch {
            try {
                val result = fetchDataWithCoroutine()
                // Handle successful result
            } catch (error: Throwable) {
                // Handle error
            }
        }

    }


}