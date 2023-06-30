package com.baeldung.swapfunction.tests

import com.baeldung.swapfunction.classes.SwapWithAlso
import org.junit.Assert.assertEquals
import org.junit.Test

class SwapWithAlsoTest {
    @Test
    fun whenValuesAreSwappedUsingAlsoScope_thenTheseValuesShouldSwapCorrectly() {
        val swap = SwapWithAlso()

        swap.swapWithAlso()

        assertEquals(2, swap.a)
        assertEquals(1, swap.b)
    }
}
