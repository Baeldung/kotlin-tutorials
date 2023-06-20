package com.baeldung.swapfunction

import org.junit.Test
import org.junit.Assert.assertEquals


class SwapValueTest {
    @Test
    fun whenValuesAreSwapped_thenNewValuesShouldInterchange() {
        val swapValue = SwapValue()

        swapValue.swapValues()


        assertEquals(2, swapValue.a)
        assertEquals(1, swapValue.b)
    }
}

