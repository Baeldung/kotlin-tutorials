package com.baeldung.coroutine.composing

import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors

class AsyncWorker(val writerFunction: (Pair<UserInput, String>) -> Int) {
    private val executor = Executors.newSingleThreadExecutor()

    fun submit(input: Pair<UserInput, String>): CompletableFuture<Int> =
      CompletableFuture.supplyAsync({ writerFunction(input) }, executor)
}