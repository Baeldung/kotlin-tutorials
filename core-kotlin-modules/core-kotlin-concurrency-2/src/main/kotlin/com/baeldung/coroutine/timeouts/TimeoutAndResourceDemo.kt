package com.baeldung.coroutine.timeouts

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

var acquired = 0

class Resource {
    fun acquire() {
        acquired++
    }

    fun release() {
        if (acquired > 0) {
            acquired--
        }
    }
}

fun acquireAndReleaseWithLeak() {
    runBlocking {
        repeat(1000) {
            launch {
                val resource = withTimeout(60) {
                    delay(50)
                    // Acquire a resource right before timeout happens
                    Resource().apply { acquire() }
                }
                resource.release() // Release the resource
            }
        }
    }
    println(acquired)
}

fun acquireAndReleaseWithoutLeak() {
    runBlocking {
        repeat(1000) {
            launch {
                val resource = Resource() // Not acquired yet
                try {
                    withTimeout(60) {
                        delay(50)
                        resource.acquire()
                    }
                } finally {
                    resource.release()
                }
            }
        }
    }
    println(acquired)
}
