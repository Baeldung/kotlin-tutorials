package com.baeldung.coroutines.cancellation

import kotlinx.coroutines.*

internal var acquired = 0

class Resource {
    init {
        acquired++
    }

    fun close() {
        acquired--
    }
}

fun main() = runBlocking {
    val job = launch {
        var resource: Resource? = null
        try {
            resource = Resource()
            repeat(1000) { i ->
                println("Job: Working on task #$i ...")
                println("Job: Number of acquired resources = $acquired")
                delay(400L)
            }
        } catch (e: CancellationException) {
            println("Job: Canceling job")
        } finally {
            resource?.close()
        }
    }
    delay(1100L)
    println("Cancel work")
    job.cancelAndJoin()
    println("Number of acquired resources = $acquired")
    println("Done")
}
