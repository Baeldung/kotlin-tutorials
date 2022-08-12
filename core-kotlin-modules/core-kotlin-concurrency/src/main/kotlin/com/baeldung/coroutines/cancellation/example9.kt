package com.baeldung.coroutines.cancellation

import kotlinx.coroutines.*

internal var acquiredWithSuspension = 0

class ResourceWithSuspension {
    init {
        acquiredWithSuspension++
    }

    suspend fun close() {
        delay(2000L)
        acquiredWithSuspension--
    }
}

fun main() = runBlocking {
    val job = launch {
        var resource: ResourceWithSuspension? = null
        try {
            resource = ResourceWithSuspension()
            repeat(1000) { i ->
                println("Job: Working on task #$i ...")
                println("Job: Number of acquired resources = $acquiredWithSuspension")
                delay(400L)
            }
        } catch (e: CancellationException) {
            println("Job: Canceling job")
        } finally {
            withContext(NonCancellable) {
                resource?.close()
            }
        }
    }
    delay(1100L)
    println("Cancel work")
    job.cancelAndJoin()
    println("Number of acquired resources = $acquiredWithSuspension")
    println("Done")
}
