package com.baeldung.coroutine

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.future.future
import java.util.concurrent.CompletableFuture


suspend fun checkIn(): String {
    delay(10)
    return "Welcome"
}

suspend fun checkInClosed(): String {
    delay(10)
    throw Exception("Sorry! We are closed.")
}

@DelicateCoroutinesApi
fun checkInAsync(): CompletableFuture<String> = GlobalScope.future { checkIn() }


