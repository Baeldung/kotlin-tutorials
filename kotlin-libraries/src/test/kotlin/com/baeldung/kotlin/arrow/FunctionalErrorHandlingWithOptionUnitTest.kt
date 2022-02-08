package com.baeldung.kotlin.arrow

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class FunctionalErrorHandlingWithOptionUnitTest {

    private val operator = FunctionalErrorHandlingWithOption()

    @Test
    fun givenInvalidInput_thenErrorMessageIsPresent(){
        val useComputeOption = operator.computeWithOptionClient("foo")
        assertEquals("Not an even number!", useComputeOption)
    }

    @Test
    fun givenOddNumberInput_thenErrorMessageIsPresent(){
        val useComputeOption = operator.computeWithOptionClient("539")
        assertEquals("Not an even number!", useComputeOption)
    }

    @Test
    fun givenEvenNumberInputWithNonSquareNum_thenFalseMessageIsPresent(){
        val useComputeOption = operator.computeWithOptionClient("100")
        assertEquals("The greatest divisor is square number: false", useComputeOption)
    }

    @Test
    fun givenEvenNumberInputWithSquareNum_thenTrueMessageIsPresent(){
        val useComputeOption = operator.computeWithOptionClient("242")
        assertEquals("The greatest divisor is square number: true", useComputeOption)
    }

}
