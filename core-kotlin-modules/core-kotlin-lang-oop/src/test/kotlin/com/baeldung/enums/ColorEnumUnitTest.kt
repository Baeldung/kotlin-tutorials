package com.baeldung.enums

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ColorEnumUnitTest {

    @Test
    fun givenPrimaryColorEnum_whenCheckForType_thenGetPrimaryColorType() {
        val type: ColorType = PrimaryColor.RED.type()
        Assertions.assertEquals(ColorType.PRIMARY, type)
    }

    @Test
    fun givenSecondaryColorEnum_whenCheckForType_thenGetSecondaryColorType() {
        val type: ColorType = SecondaryColor.GREEN.type()
        Assertions.assertEquals(ColorType.SECONDARY, type)
    }

    @Test
    fun givenAnyColorEnum_whenCallPaint_thenPaintRespectiveColor() {
        val colors = listOf(PrimaryColor.RED, SecondaryColor.GREEN)
        for(color in colors) {
            @Suppress("USELESS_IS_CHECK")
            Assertions.assertTrue(color is IColor)
            val myColor = color.paint()
            Assertions.assertNotNull(myColor)
        }
    }
}