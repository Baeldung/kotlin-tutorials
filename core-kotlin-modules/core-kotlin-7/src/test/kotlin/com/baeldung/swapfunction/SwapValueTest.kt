package com.baeldung.swapfunction

import org.junit.Test
import org.junit.Assert.assertEquals


class SwapValueTest {

    @Test
    fun whenValuesAreSwapped_thenNewValuesShouldInterchange() {

        val swapValue = SwapValue()
        val expectedX = 20
        val expectedY = 10

        swapValue.SwapValues()

        assertEquals(expectedX, swapValue.x)
        assertEquals(expectedY, swapValue.y)
    }
}