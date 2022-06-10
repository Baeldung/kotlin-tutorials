package com.baeldung.coroutines

import kotlinx.coroutines.*
import org.junit.Test

class CancellingUnitTest {

    @Test
    fun cancel_job() = runBlocking {
        val job = launch {
            repeat(1000) {
                println(it)
                delay(50)
            }
        }
        delay(500)
        job.cancel()
        job.join()
    }
}