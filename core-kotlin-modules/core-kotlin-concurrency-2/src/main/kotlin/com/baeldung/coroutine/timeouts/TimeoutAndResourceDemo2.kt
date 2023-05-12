package com.baeldung.coroutine.timeouts

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout


var acquired2 = 0

class Resource2 {

    fun acquire() {
        acquired2++
    }

    fun release() {
        acquired2--
    }
}


fun acquireAndReleaseWithLeak2() {
    runBlocking {
        repeat(10_000) {
            launch {
                val resource = withTimeout(60) {
                    delay(50)
                    Resource2().apply { acquire() }
                }
                resource.release()
            }
        }
    }

    println(acquired2)
}
