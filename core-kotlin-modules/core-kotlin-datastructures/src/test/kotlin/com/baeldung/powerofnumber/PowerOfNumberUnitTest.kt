package com.baeldung.powerofnumber

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.pow

class PowerOfNumberUnitTest {
    private val DELTA = 1e-15

    @Test
    fun `get power of number algorithmically`(){
        assertEquals(4, PowerOfNumber.power(2,2))
        assertEquals(1024, PowerOfNumber.power(4, 5))
    }

    @Test
    fun `get power of number recursively`(){
        assertEquals(4, PowerOfNumber.powerRec(2,2))
        assertEquals(1024, PowerOfNumber.powerRec(4, 5))
    }

    @Test
    fun `get power of number using pow() function`(){

        val baseDouble = 5.0
        val exponentDouble = 2.5
        assertEquals(55.90169943749474, baseDouble.pow(exponentDouble), DELTA)

        val baseInt = 3
        val exponentInt = -4
        assertEquals(0.012345679012345678, baseInt.toDouble().pow(exponentInt.toDouble()), DELTA)
    }

    @Test
    fun `get power of number using Math pow() function`(){
        assertEquals(55.90169943749474, Math.pow(5.0, 2.5), DELTA)
        assertEquals(0.012345679012345678, Math.pow(3.0, -4.0), DELTA)

        val baseInt = 3
        val exponentInt = -4
        assertEquals(0.012345679012345678, Math.pow(baseInt.toDouble(), exponentInt.toDouble()), DELTA)
    }
}