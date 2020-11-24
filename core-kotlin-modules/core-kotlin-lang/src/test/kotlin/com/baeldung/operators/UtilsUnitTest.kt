package com.baeldung.operators

import java.math.BigInteger
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UtilsUnitTest {

    @Test
    fun `We should be able to add an int value to an existing BigInteger using +`() {
        assertEquals(BigInteger.ZERO + 1, BigInteger.ONE)
    }
}