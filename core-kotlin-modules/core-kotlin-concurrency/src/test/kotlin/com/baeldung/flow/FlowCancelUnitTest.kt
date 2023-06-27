package com.baeldung.flow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class FlowCancelUnitTest {

    @Test
    fun when_job_for_flow_cancel_then_stop_flow() = runBlocking {
        val flow: Flow<Int> = flow {
            emit(1)
            delay(1000)
            emit(2)
            delay(2000)
            emit(3)
        }

        val collectedValues = mutableListOf<Int>()
        val job = launch {
            flow.cancellable().collect { value ->
                collectedValues.add(value)
            }
        }
        delay(2000)

        job.cancel()
        assertEquals(listOf(1, 2), collectedValues)
    }
}