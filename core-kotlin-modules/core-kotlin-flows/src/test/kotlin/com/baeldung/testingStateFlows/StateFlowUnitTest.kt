package com.baeldung.testingStateFlows

import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StateFlowUnitTest {
    private lateinit var stateFlowExample: StateFlowExample

    @Test
    fun `test initial state`() = runTest {
        stateFlowExample = StateFlowExample()
        assertEquals("Initial State", stateFlowExample.getCurrentState())
    }


    @Test
    fun `test state update`() = runTest {
        stateFlowExample = StateFlowExample()

        stateFlowExample.updateState("New State")

        assertEquals("New State", stateFlowExample.getCurrentState())
    }


    @Test
    fun `test collecting state updates`() = runTest {
        stateFlowExample = StateFlowExample()
        val collectedStates = mutableListOf<String>()

        val collectJob = launch {
            stateFlowExample.state.collect { state ->
                collectedStates.add(state)
            }
        }
        // Advance the time to make the initial state available for collection
        advanceUntilIdle()

        stateFlowExample.updateState("First Update")
        advanceUntilIdle() // Advance the time to make the first update available

        stateFlowExample.updateState("Second Update")
        advanceUntilIdle() // Advance the time to make the second update available

        stateFlowExample.updateState("Third Update")
        advanceUntilIdle() // Advance the time to make the third update available

        assertEquals(4, collectedStates.size) // Initial state + 3 updates
        assertEquals("Initial State", collectedStates[0])
        assertEquals("First Update", collectedStates[1])
        assertEquals("Second Update", collectedStates[2])
        assertEquals("Third Update", collectedStates[3])

        // Cancel the job to stop collecting states and let the test finish
        collectJob.cancel()
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test stateIn transformation`() = runTest {
        val testScope = TestScope(UnconfinedTestDispatcher())
        val transformer = DataTransformer(testScope)
        val collectedStates = mutableListOf<String>()

        val collectJob = launch {
            transformer.transformedData.collect { state ->
                collectedStates.add(state)
            }
        }
        advanceUntilIdle() // Wait for initial collection

        transformer.emit("first")
        advanceUntilIdle() // Wait for first emission

        transformer.emit("second")
        advanceUntilIdle() // Wait for second emission

        assertEquals(3, collectedStates.size)
        assertEquals("INITIAL", collectedStates[0])
        assertEquals("FIRST", collectedStates[1])
        assertEquals("SECOND", collectedStates[2])

        collectJob.cancel()
        testScope.cancel()
    }


}
