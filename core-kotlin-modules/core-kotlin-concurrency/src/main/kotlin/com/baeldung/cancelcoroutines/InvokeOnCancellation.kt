package com.baeldung.cancelcoroutines

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.cancelFutureOnCancellation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit


private suspend fun startSuspendCancellableCoroutine(taskResult: Int): Int {
    return suspendCancellableCoroutine { cancellableContinuation: CancellableContinuation<Int> ->
        println("start coroutine")
        val callback = Callback(cancellableContinuation)
        val task = FakeAsyncTask(taskResult, callback)
        task.start()
        cancellableContinuation.invokeOnCancellation {
            task.future.cancel(true)
        }
    }
}

fun main() = runBlocking {
    val job = launch {
        val result = startSuspendCancellableCoroutine(1)
        println("result = $result")
    }

    delay(100)
    println("cancelling")
    job.cancelAndJoin()
    delay(100)
}