package com.baeldung.cancelcoroutines

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.cancelFutureOnCancellation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine


private suspend fun startSuspendCancellableCoroutine(taskResult: Int): Int {
    return suspendCancellableCoroutine { cancellableContinuation: CancellableContinuation<Int> ->
        println("start coroutine")
        val callback = Callback(cancellableContinuation)
        val task = FakeAsyncTask(taskResult, callback)
        task.start()
        cancellableContinuation.cancelFutureOnCancellation(task.future)
    }
}

fun main() = runBlocking {
    val job1 = launch {
        val result = startSuspendCancellableCoroutine(1)
        println("result1 = $result")
    }

    delay(10)
    println("cancelling-1")
    job1.cancelAndJoin()

    val job2 = launch {
        val result = startSuspendCancellableCoroutine(1)
        println("result2 = $result")
    }

    delay(100)
    println("cancelling-2")
    job2.cancelAndJoin()

    delay(100)
}