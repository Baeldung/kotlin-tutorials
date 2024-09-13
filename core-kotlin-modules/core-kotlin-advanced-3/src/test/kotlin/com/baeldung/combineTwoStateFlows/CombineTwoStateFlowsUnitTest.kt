package com.baeldung.combineTwoStateFlows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CombineTwoStateFlowsUnitTest {

    @Test
    fun `merge two stateflows using combine operator`() = runTest {
        val stateFlow1 = MutableStateFlow(5)
        val stateFlow2 = MutableStateFlow("Hi")

        val combinedStateFlow = combine(stateFlow1, stateFlow2) { value1, value2 ->
            value1 to value2
        }

        val result = combinedStateFlow.first()

        assertEquals(Pair(5, "Hi"), result)
    }

    @Test
    fun `merge two stateflows using zip operator`() = runTest {
        val stateFlow1 = MutableStateFlow(5)
        val stateFlow2 = MutableStateFlow("Hi")

        val zippedStateFlow = stateFlow1.zip(stateFlow2) { value1, value2 ->
            value1 to value2
        }

        val result = zippedStateFlow.first()
        assertEquals(Pair(5, "Hi"), result)
    }

}