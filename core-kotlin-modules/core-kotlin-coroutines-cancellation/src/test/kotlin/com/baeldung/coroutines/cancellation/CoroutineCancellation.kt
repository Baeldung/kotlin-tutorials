package com.baeldung.coroutines.cancellation

import kotlinx.coroutines.*
import org.junit.Ignore
import org.junit.Test

class CoroutineCancellation {

    @Test
    fun `Simple coroutine cancellation`() {
        runBlocking {
            val job = launch {
                repeat(10) {
                    println("[$it] Job started")
                    delay(500)
                    println("[$it] Job's done")
                }
            }

            delay(1750)
            println("Time to cancel the job")
            job.cancelAndJoin()
        }
    }

    @Test
    @Ignore
    fun `Unable to cancel coroutine because it does not cooperate`() {
        runBlocking {
            val job = launch(Dispatchers.Default) { // Run on different dispatcher
                var timeToPrint = System.currentTimeMillis() + 1000
                while(true) {
                    if (System.currentTimeMillis() > timeToPrint) {
                        println("I've been waiting for this moment, for all my life")
                        timeToPrint = System.currentTimeMillis() + 1000
                    }
                }
            }
            delay(2500)
            println("Time to cancel the job")
            job.cancelAndJoin()
            println("I will never print")
        }
    }

    @Test
    fun `Making coroutine cooperative`() {
        runBlocking {
            val job = launch(Dispatchers.Default) { // Run on different dispatcher
                var timeToPrint = System.currentTimeMillis() + 1000
                while(isActive) {
                    if (System.currentTimeMillis() > timeToPrint) {
                        println("Doing work...")
                        timeToPrint = System.currentTimeMillis() + 1000
                    }
                }
            }
            delay(2500)
            println("Time to cancel the job")
            job.cancelAndJoin()
            println("I will print!")
        }
    }

    @Test
    fun `Timeout cancellation`() {
        runBlocking {
            launch {
                withTimeout(2500) {
                    repeat(10) {
                        println("[$it] Job started")
                        delay(1000)
                        println("[$it] Job's done")
                    }
                }
            }
        }
    }


}
