package com.baeldung.inline.classes

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CircleRadiusUnitTest {

    @Test
    fun givenRadius_ThenDiameterIsCorrectlyCalculated() {
        val radius = CircleRadius(5.0)
        assertEquals(10.0, radius.diameterOfCircle)
    }

    @Test
    fun givenRadius_ThenAreaIsCorrectlyCalculated() {
        val radius = CircleRadius(5.0)
        assertEquals(78.5, radius.areaOfCircle())
    }
}