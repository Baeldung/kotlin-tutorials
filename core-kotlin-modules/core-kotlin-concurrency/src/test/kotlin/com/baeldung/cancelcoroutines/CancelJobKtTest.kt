package com.baeldung.cancelcoroutines

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test

class CancelJobKtTest {
    @Test
    fun test_cancel_job() = runBlocking {
        val job = launch {
            repeat(100) {
                println(it)
                delay(50)
            }
        }
        delay(300)
        job.cancel()
        job.join()
    }

    @Test

    fun test_cancellation_cooperative() = runBlocking {

        val startTime = System.currentTimeMillis()

        val job = launch(Dispatchers.Default) {

            var nextPrintTime = startTime

            var i = 0

//            while (i < 5 && isActive) {

            while (i < 5) {
                ensureActive()
//                yield()

                if (System.currentTimeMillis() >= nextPrintTime) {

                    println(i++)

                    nextPrintTime += 50L

                }

            }

        }

        delay(100)
        job.cancelAndJoin()
    }
}