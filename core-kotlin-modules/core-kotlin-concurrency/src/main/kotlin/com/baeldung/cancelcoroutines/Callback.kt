package com.baeldung.cancelcoroutines

import kotlinx.coroutines.CancellableContinuation

class Callback(private val cancellableContinuation: CancellableContinuation<Int>) {
    fun completed(result: Int) {
        println("complete result = $result")
        cancellableContinuation.resumeWith(Result.success(result))
    }

    fun cancelled() {
        println("cancelled")
        cancellableContinuation.cancel()
    }

    fun failed() {
        cancellableContinuation.resumeWith(Result.failure(Exception("Failed")))
    }
}
