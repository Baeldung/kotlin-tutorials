package com.baeldung.combineTwoStateFlows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
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
    fun `combine operator retains previous value when one flow emits`() = runTest {
        val stateFlow1 = MutableStateFlow(5)
        val stateFlow2 = MutableStateFlow("Hi")

        val combinedStateFlow = combine(stateFlow1, stateFlow2) { value1, value2 ->
            value1 to value2
        }

        assertEquals(Pair(5, "Hi"), combinedStateFlow.first())

        stateFlow1.value = 11
        assertEquals(Pair(11, "Hi"), combinedStateFlow.first())

        stateFlow2.value = "World"
        assertEquals(Pair(11, "World"), combinedStateFlow.first())
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

//    @Test
//    fun `zip operator waits for both flows to emit`() = runTest {
//        val stateFlow1 = MutableStateFlow(5)
//        val stateFlow2 = MutableStateFlow("Hi")
//
//        val zippedStateFlow = stateFlow1.zip(stateFlow2) { value1, value2 ->
//            value1 to value2
//        }
//
//        assertEquals(Pair(5, "Hi"), zippedStateFlow.first())
//
//        stateFlow1.value = 11
//
//        assertEquals(Pair(11, "Hi"), zippedStateFlow.first())
//
//        stateFlow2.value = "World"
//        assertEquals(Pair(11, "World"), zippedStateFlow.first())
//    }

        @Test
        fun `zip operator only emits when both flows emit`() = runTest {
            val stateFlow1 = MutableStateFlow(5)
            val stateFlow2 = MutableStateFlow("Hi")

            val zippedStateFlow = stateFlow1.zip(stateFlow2) { value1, value2 ->
                value1 to value2
            }

            val emissions = mutableListOf<Pair<Int, String>>()

            val job = launch {
                zippedStateFlow.collect {
                    emissions.add(it)
                }
            }

            delay(100)
            stateFlow1.value = 11

            delay(100)
            stateFlow2.value = "Hey"

            delay(100)
            stateFlow1.value = 12

            delay(100)
            stateFlow2.value = "Hello"

            delay(100)

            assertEquals(listOf(Pair(5, "Hi"), Pair(11, "Hey"), Pair(12, "Hello")), emissions)

            job.cancel()
        }
}