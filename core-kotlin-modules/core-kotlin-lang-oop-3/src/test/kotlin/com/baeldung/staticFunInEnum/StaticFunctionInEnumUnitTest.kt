package com.baeldung.staticFunInEnum

import com.baeldung.staticFunInEnum.MagicNumber.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


enum class MagicNumber(val value: Int) {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6);

    companion object {

        fun pickOneRandomly(): MagicNumber {
            return values().random()
        }

        @JvmStatic
        fun greaterThan(n: Int): List<MagicNumber> {
            return values().filter { it.value > n }
        }
    }
}

class StaticFunctionInEnumUnitTest {
    @Test
    fun `given enum with companion object when call functions in companion object, should get expected result`() {
        assertNotNull(MagicNumber.pickOneRandomly())
        assertEquals(listOf(THREE, FOUR, FIVE, SIX), MagicNumber.greaterThan(2))
    }
}