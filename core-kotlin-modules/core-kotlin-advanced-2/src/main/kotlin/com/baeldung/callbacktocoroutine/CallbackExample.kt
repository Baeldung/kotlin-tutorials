package com.baeldung.callbacktocoroutine

class CallbackExample {
    interface Callback {
        fun onSuccess(result: String)
        fun onFailure(error: Throwable)
    }

    fun fetchData(callback: Callback) {
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

    fun processData(): String {
        Thread.sleep(1000)
        return "processed-data"
    }

    fun main() {
        // Creating the callback object
        val callback = createCallback()

        // Calling the fetchData function with a callback
        fetchData(callback)

    }

    fun createCallback() = object : Callback {
        override fun onSuccess(result: String) {
            // Handle successful result
        }

        override fun onFailure(error: Throwable) {
            // Handle error
        }
    }


}