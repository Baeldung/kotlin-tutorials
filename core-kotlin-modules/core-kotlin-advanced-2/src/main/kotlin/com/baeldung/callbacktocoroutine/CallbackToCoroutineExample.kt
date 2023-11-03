package com.baeldung.callbacktocoroutine

import kotlinx.coroutines.CoroutineExceptionHandler
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
        val callback = createCallbackWithContinuation(
            onSuccess = continuation::resume,
            onFailure = continuation::resumeWithException
        )

        fetchDataWithCallback(callback)
    }

    fun createCallbackWithContinuation(
        onSuccess: (String) -> Unit,
        onFailure: (Throwable) -> Unit
    ) = object : Callback {
        // Resume the coroutine with the result
        override fun onSuccess(result: String) = onSuccess(result)

        // Resume the coroutine with an exception
        override fun onFailure(error: Throwable) = onFailure(error)
    }

    fun processData(): String {
        Thread.sleep(1000)
        return "processed-data"
    }

    fun main() {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            // Handle exception
        }

        // Calling the fetchData function within a coroutine scope with an exception handler
        GlobalScope.launch(exceptionHandler) {
            val result = fetchDataWithCoroutine()
            // Handle successful result
        }
    }


}