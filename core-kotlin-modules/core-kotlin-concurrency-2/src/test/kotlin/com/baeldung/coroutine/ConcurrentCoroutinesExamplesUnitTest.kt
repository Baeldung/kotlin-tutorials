package com.baeldung.coroutine

import com.baeldung.coroutine.ConcurrentCoroutinesExamples.executeManyCoroutinesInParallelUsingAsync
import com.baeldung.coroutine.ConcurrentCoroutinesExamples.executeTwoCoroutinesInParallelUsingAsync
import com.baeldung.coroutine.ConcurrentCoroutinesExamples.executeTwoCoroutinesInParallelUsingLaunch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis
import kotlin.test.assertEquals

class ConcurrentCoroutinesExamplesUnitTest {
    @RepeatedTest(5)
    fun `executeTwoCoroutinesInParallelUsingLaunch - should be executable without exceptions`() {
        Assertions.assertDoesNotThrow {
            runBlocking {
                val executionTime = measureTimeMillis {
                    executeTwoCoroutinesInParallelUsingLaunch()
                }
                println(" - The whole example took $executionTime milliseconds")
            }
        }
    }

    @RepeatedTest(5)
    fun `executeTwoCoroutinesInParallelUsingAsync - should be executable without exceptions`() {
        Assertions.assertDoesNotThrow {
            runBlocking {
                val executionTime = measureTimeMillis {
                    executeTwoCoroutinesInParallelUsingAsync()
                }
                println(" - The whole example took $executionTime milliseconds")
            }
        }
    }

    @Test
    fun `executeManyCoroutinesInParallelUsingAsync - should be executable without exceptions`() {
        assertEquals(
            expected = (1..5).map { it * it },
            actual = runBlocking {
                executeManyCoroutinesInParallelUsingAsync()
            },
        )
    }
}
