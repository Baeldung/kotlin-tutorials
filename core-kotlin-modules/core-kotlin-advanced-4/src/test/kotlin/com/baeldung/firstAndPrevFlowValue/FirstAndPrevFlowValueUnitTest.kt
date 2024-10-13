package com.baeldung.firstAndPrevFlowValue

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class FirstAndPrevFlowValueUnitTest {
    @Test
    fun `test manually tracking previous and current value`() = runTest {
        val flow = flowOf(1, 2, 3, 4, 5)
        var previousValue: Int? = null
        val results = mutableListOf<Pair<Int?, Int>>()

        flow.collect { currentValue ->
            results.add(Pair(previousValue, currentValue))
            previousValue = currentValue
        }

        assertEquals(listOf(Pair(null, 1), Pair(1, 2), Pair(2, 3), Pair(3, 4), Pair(4, 5)), results)
    }

    @Test
    fun `test runningFold for previous and current value`() = runTest {
        val flow = flowOf(1, 2, 3, 4, 5)
        val initial: Pair<Int?, Int?> = null to null
        val results = mutableListOf<Pair<Int?, Int?>>()

        flow.runningFold(initial) { lastPair, next ->
            lastPair.run {
                val (_, last) = this
                last to next
            }
        }
            .drop(1)
            .collect { results.add(it) }

        assertEquals(listOf(Pair(null, 1), Pair(1, 2), Pair(2, 3), Pair(3, 4), Pair(4, 5)), results)
    }
}