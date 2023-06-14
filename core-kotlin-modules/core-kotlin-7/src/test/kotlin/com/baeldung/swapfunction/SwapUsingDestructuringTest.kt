package com.baeldung.swapfunction

import org.junit.Test
import kotlin.test.assertEquals

class SwapUsingDestructuringTest {

    @Test
    fun whenValuesAreSwappedUsingDestructuring_thenNewValuesShouldInterchangeCorrectly() {

        val swapInstance = SwapWithToOperator()
        swapInstance.swapUsingDestructuring()

        assertEquals(200, swapInstance.x)
        assertEquals(100, swapInstance.y)
    }
}