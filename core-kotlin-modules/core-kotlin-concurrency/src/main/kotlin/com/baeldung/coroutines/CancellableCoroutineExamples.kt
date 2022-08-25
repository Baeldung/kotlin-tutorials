package com.baeldung.coroutines

import kotlinx.coroutines.*

suspend fun main() {
    cancelCoroutineWithIsActive()
    cancelCoroutineWithEnsureActive()
    cancelCoroutineWithSuspendingFunction()
    cancelCoroutineWithTimeout()
    cancelCoroutineWithTimeoutOrNull()
    ignoreFailedChildCoroutine()
    customExceptionHandler()
    suspendingFunctionsInFinallyBlock()
}

suspend fun cancelCoroutineWithIsActive() = coroutineScope {
    println("Cancel coroutine with isActive:")
    val job = launch {
        while (isActive) {
            //doSomething
        }
    }
    println(job)
    job.cancel()
    println(job)
    job.join()
    println(job.toString() + "\n")
}

suspend fun cancelCoroutineWithEnsureActive() = coroutineScope {
    println("Cancel coroutine with ensureActive():")
    val job = launch {
        while (true) {
            ensureActive() //throws CancellationException
            //doSomething
        }
    }
    println(job)
    job.cancel()
    println(job)
    job.join()
    println(job.toString() + "\n")
}

suspend fun cancelCoroutineWithSuspendingFunction() = coroutineScope {
    println("Cancel coroutine with suspending function:")
    val job = launch {
        while (true) {
            yield() //throws CancellationException
            //doSomething
        }
    }
    println(job)
    job.cancel()
    println(job)
    job.join()
    println(job.toString() + "\n")
}

suspend fun cancelCoroutineWithTimeout() {
    println("Cancel coroutine with timeout:")
    try {
        val result = withTimeout(1000) {
            try {
                repeat(100) { i ->
                    println("Repeating $i")
                    delay(500)
                }
            } catch (e: CancellationException) {
                println("Exception caught: $e")
                //fallback logic
            } finally {
                //doSomething
            }
            println("Executing outer code...")
            "IgnoredResult"
        }
        println("Result is: $result") //not reachable
    } catch (e: TimeoutCancellationException) {
        println(e.toString() + "\n")
    }
}

suspend fun cancelCoroutineWithTimeoutOrNull() = coroutineScope {
    println("Cancel coroutine with timeout or null:")
    val result = withTimeoutOrNull(1000) {
        delay(2000)
        "NonReachable" //implicit return
    }
    println("Result is: $result\n") //Result is: null
}

suspend fun ignoreFailedChildCoroutine() {
    println("Ignoring failed child coroutine:")
    supervisorScope {
        val firstDeferred: Deferred<String> = async {
            throw RuntimeException("Exception example")
        }
        val secondDeferred: Deferred<String> = async {
            delay(1000)
            "ActualResult"
        }
        println("First coroutine: $firstDeferred")
        println("Second coroutine: $secondDeferred")

        try {
            val firstResult = firstDeferred.await() //will throw exception here
            println("First result: $firstResult")
        } catch (e: Exception) {
            println("Exception caught: $e")
        }
        println("Second result: ${secondDeferred.await()}\n")
    }
}

fun customExceptionHandler() {
    println("Custom exception handler example:")
    val handler = CoroutineExceptionHandler { context, exception ->
        println("Exception caught by customExceptionHandler: $exception, context: $context\n")
    }
    //...
    CoroutineScope(handler).launch {
        throw RuntimeException("Exception message")
    }
    Thread.sleep(10) //to keep logs in sequential order
}

suspend fun suspendingFunctionsInFinallyBlock() = coroutineScope {
    println("Suspending functions in finally block example:")
    val job: Job = launch {
        println("Parent cancellable context: $coroutineContext")
        try {
            repeat(100) { i ->
                println("Repeat: $i")
                delay(500)
            }
        } catch (e: CancellationException) {
            println("Exception caught: $e")
            throw e
        } finally {
            withContext(NonCancellable) {
                delay(1000) //will not produce CancellationException
                println("Child non-cancellable context: $coroutineContext")
            }
        }
        println("Executing outer code...") //not reachable
    }

    delay(1000)
    job.cancel()
}
