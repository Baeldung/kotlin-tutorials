package com.baeldung.swapfunction

import org.junit.Assert.assertEquals
import org.junit.Test

class SwapWithAlsoTest {

    @Test
    fun whenValuesAreSwappedUsingAlsoScope_thenTheseValuesShouldSwapCorrectly() {

        val testSwap = SwapWithAlso()

        testSwap.main()

        assertEquals(20, testSwap.x)
        assertEquals(10, testSwap.y)
    }
}
