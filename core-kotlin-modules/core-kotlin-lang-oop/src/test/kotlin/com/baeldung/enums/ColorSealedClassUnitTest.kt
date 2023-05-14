package com.baeldung.enums

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ColorSealedClassUnitTest {

    @Test
    fun givenPrimaryColorObject_whenCheckForType_thenGetPrimaryColorType() {
        val type: ColorType = Color.RED.type()
        Assertions.assertEquals(ColorType.PRIMARY, type)
    }

    @Test
    fun givenSecondaryColorObject_whenCheckForType_thenGetSecondaryColorType() {
        val type: ColorType = Color.GREEN.type()
        Assertions.assertEquals(ColorType.SECONDARY, type)
    }

    @Test
    fun givenAnyColorObject_whenCallPaint_thenPaintRespectiveColor() {
        val colors = listOf(Color.RED, Color.GREEN)
        for (color in colors) {
            val myColor = color.paint()
            Assertions.assertNotNull(myColor)
        }
    }

}