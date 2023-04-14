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

fun acquireAndReleaseWithLeak(): Int {
    runBlocking {
        launch {
            val resource = withTimeout(50) {
                // Acquire a resource right before timeout happens
                Resource()
                    .apply { acquire() }
                    .also { delay(60) }
            }
            resource.release() // Release the resource
        }
    }
    return acquired
}

fun acquireAndReleaseWithoutLeak(): Int {
    runBlocking {
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
    return acquired
}
