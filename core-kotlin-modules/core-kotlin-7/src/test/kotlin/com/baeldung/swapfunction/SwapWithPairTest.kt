package com.baeldung.swapfunction

import org.junit.Test
import kotlin.test.assertEquals

class SwapWithPairTest {

    @Test
    fun whenValuesAreSwappedUsingDestructuring_thenNewValuesShouldInterchangeCorrectly() {

        val swapInstance = SwapWithPair()
        swapInstance.swapUsingDestructuring()

        assertEquals(200, swapInstance.x)
        assertEquals(100, swapInstance.y)
    }
}