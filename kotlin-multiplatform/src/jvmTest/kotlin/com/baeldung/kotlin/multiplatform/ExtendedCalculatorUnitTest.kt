package com.baeldung.kotlin.multiplatform

import kotlin.test.Test
import kotlin.test.assertEquals

class ExtendedCalculatorUnitTest {

    @Test
    fun testSquare() {
        assertEquals(4.0, ExtendedCalculator.square(2.0))
    }

}