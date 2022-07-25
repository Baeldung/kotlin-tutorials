package com.baeldung.cancellation

import kotlinx.coroutines.*
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class CoroutinesScopeHierarchyKtTest {
    /*
    *                   Parent Job(Scope Job)
    *                           |
    *                           |
    * ---------------------------------------------------------
    * |                         |                             |
    * Job 1                    Job 2                        Job 3
    *
    * */

    @Test
    fun `when a child cancelled other children not affected`() {
        val parentJob = Job()
        val scope = CoroutineScope(parentJob)
        val job1 = scope.launch { delay(500) }

        val job2 = scope.launch { delay(600) }

        val job3 = scope.launch { delay(700) }

        job1.cancel()
        assertEquals(false, job1.isActive)
        assertEquals(true, job2.isActive)
        assertEquals(true, job3.isActive)
    }

    @Test
    fun `when scope is cancelled children and scope job would be cancelled`() {
        val parentJob = Job()
        val scope = CoroutineScope(parentJob)
        val job1 = scope.launch { delay(500) }

        val job2 = scope.launch { delay(600) }

        val job3 = scope.launch { delay(700) }

        scope.cancel()
        assertEquals(false, parentJob.isActive)
        assertEquals(false, job1.isActive)
        assertEquals(false, job2.isActive)
        assertEquals(false, job3.isActive)
    }

    @Test
    fun `when a child throw exception other children and parent job cancelled`() {
        val parentJob = Job()
        val scope = CoroutineScope(parentJob)
        val job1 = scope.launch {
            throw IndexOutOfBoundsException("error happened")
        }

        val job2 = scope.launch {
            delay(1000)
            // These won't be printed as the job would be cancelled
            println("doing job2")
        }

        val job3 = scope.launch {
            delay(1000)
            // These won't be printed as the job would be cancelled
            println("doing job3")
        }

        assertEquals(false, parentJob.isActive)
        assertEquals(false, job1.isActive)
        assertEquals(false, job2.isActive)
        assertEquals(false, job3.isActive)
    }

    @Test
    fun `when a child throw exception with Supervisor other won't affected`() {
        val parentJob = SupervisorJob()
        val scope = CoroutineScope(parentJob)
        val job1 = scope.launch {
            throw IndexOutOfBoundsException("error happened")
        }

        val job2 = scope.launch {
            delay(1000)
            // These won't be printed as the job would be cancelled
            println("doing job2")
        }

        val job3 = scope.launch {
            delay(1000)
            // These won't be printed as the job would be cancelled
            println("doing job3")
        }

        assertEquals(true, parentJob.isActive)
        assertEquals(false, job1.isActive)
        assertEquals(true, job2.isActive)
        assertEquals(true, job3.isActive)
    }
}