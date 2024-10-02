package com.baeldung.firstAndPrevFlowValue

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class FirstAndPrevFlowValueUnitTest {

    @Test
    fun `test zip flow for previous and current value`() = runTest {
        val flow = flowOf(1, 2, 3, 4, 5)
        val results = mutableListOf<Pair<Int, Int>>()

        flow.zip(flow.drop(1)) { previous, current -> Pair(previous, current) }
            .collect { results.add(it) }

        assertEquals(listOf(Pair(1, 2), Pair(2, 3), Pair(3, 4), Pair(4, 5)), results)
    }

    @Test
    fun `test scan flow for previous and current value`() = runTest {
        val flow = flowOf(1, 2, 3, 4, 5)
        val results = mutableListOf<Pair<Int?, Int?>>()

        flow.scan(Pair<Int?, Int?>(null, null)) { previous, current ->
            Pair(previous.second, current)
        }.drop(1)
            .collect { results.add(it) }

        assertEquals(listOf(Pair(null, 1), Pair(1, 2), Pair(2, 3), Pair(3, 4), Pair(4, 5)), results)
    }
}