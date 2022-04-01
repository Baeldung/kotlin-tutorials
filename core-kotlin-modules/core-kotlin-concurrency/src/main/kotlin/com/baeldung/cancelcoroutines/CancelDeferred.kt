package com.baeldung.cancelcoroutines

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    val deferred = async {
        val result = 100
        repeat(result) {
            println(it)
            delay(50)
        }
        result
    }
    delay(300)
    deferred.cancelAndJoin()
    try {
        deferred.await()
    } catch (e: CancellationException) {
        println(e)
    }
    Unit
}