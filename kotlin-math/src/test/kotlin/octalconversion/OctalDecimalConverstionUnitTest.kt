package com.baeldung.math.octalconversion

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigInteger

class OctalDecimalConverstionUnitTest {

    @Test
    fun `test octal to decimal conversion using octalToDecimalUsingParseInt should give correct result`() {
        Assertions.assertEquals(28, octalToDecimalUsingParseInt("34"))
    }

    @Test
    fun `test octal to decimal conversion using octalToDecimalUsingIteration should give correct result`() {
        Assertions.assertEquals(28, octalToDecimalUsingIteration("34"))
    }

    @Test
    fun `test octal to decimal conversion using octalToDecimal extension function should give correct result`() {
        Assertions.assertEquals(28, "34".octalToDecimal())
    }

    @Test
    fun `test octal to decimal conversion using octalToDecimalUsingToInt should give correct result`() {
        Assertions.assertEquals(28, octalToDecimalUsingToInt("34"))
    }

    @Test
    fun `test octal to decimal conversion using octalToDecimalUsingBigInteger should give correct result`() {
        Assertions.assertEquals(BigInteger.valueOf(28), octalToDecimalUsingBigInteger("34"))
    }

    @Test
    fun `test decimal to octal conversion using decimalToOctalUsingIntegerToString should give correct result`() {
        Assertions.assertEquals("34", decimalToOctalUsingIntegerToString(28))
    }

    @Test
    fun `test decimal to octal conversion using decimalToOctalUsingStringInterpolation should give correct result`() {
        Assertions.assertEquals("34", decimalToOctalUsingStringInterpolation(28))
    }

    @Test
    fun `test decimal to octal conversion using decimalToOctal extension function should give correct result`() {
        Assertions.assertEquals("34", 28.decimalToOctal())
    }

    @Test
    fun `test decimal to octal conversion using decimalToOctalUsingIteration should give correct result`() {
        Assertions.assertEquals("34", decimalToOctalUsingIteration(28))
    }
}