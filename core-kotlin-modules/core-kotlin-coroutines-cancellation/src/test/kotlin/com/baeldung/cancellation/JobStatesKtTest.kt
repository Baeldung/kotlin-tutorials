package com.baeldung.cancellation

import kotlinx.coroutines.*
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class JobStatesKtTest {
    @Test
    fun `when Coroutines finish it goes to complete state`() {
        var job: Job? = null
        runBlocking {
            job = launch {
                assertEquals(true, job?.isActive)
                // doing some staff
                delay(500)
            }
            delay(1000)
            assertEquals(true, job?.isCompleted)
        }
    }

    @Test
    fun `when Coroutines cancelled while working it goes to cancel state`() {
        var job: Job? = null
        runBlocking {
            job = launch {
                assertEquals(true, job?.isActive)
                // doing some staff
                delay(2000)
            }
            delay(1000)
            job?.cancel()
            assertEquals(true, job?.isCancelled)
            delay(1000)
            assertEquals(true, job?.isCompleted)

        }
    }
}