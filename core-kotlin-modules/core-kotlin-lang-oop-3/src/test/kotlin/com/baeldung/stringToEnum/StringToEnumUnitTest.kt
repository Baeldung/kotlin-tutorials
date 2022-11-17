package com.baeldung.stringToEnum

import com.baeldung.stringToEnum.BaeldungNumber.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNull

enum class BaeldungNumber {
    ONE, TWO, THREE, FOUR, FIVE, UNKNOWN;

    companion object {
        fun byNameIgnoreCaseOrNull(input: String): BaeldungNumber? {
            return values().firstOrNull { it.name.equals(input, true) }
        }
    }
}

inline fun <reified T : Enum<T>> enumByNameIgnoreCase(input: String, default: T? = null): T? {
    return enumValues<T>().firstOrNull { it.name.equals(input, true) } ?: default
}

class StringToEnumUnitTest {
    @Test
    fun `given a string, when using valueOf(), should get the enum object`() {
        assertEquals(ONE, BaeldungNumber.valueOf("ONE"))

        assertThrows<IllegalArgumentException> {
            BaeldungNumber.valueOf("one")
        }
    }

    @Test
    fun `given a string, when using enumValueOf(), should get the enum object`() {
        val theOne = enumValueOf<BaeldungNumber>("ONE")
        assertEquals(ONE, theOne)

        assertThrows<IllegalArgumentException> {
            enumValueOf<BaeldungNumber>("one")
        }
    }

    @Test
    fun `given a string, when using values, should get the enum object`() {
        val theOne = BaeldungNumber.values().firstOrNull { it.name.equals("one", true) }
        assertEquals(ONE, theOne)

        val theTwo = BaeldungNumber.values().firstOrNull { it.name.equals("TWO", true) }
        assertEquals(TWO, theTwo)

        val theNull = BaeldungNumber.values().firstOrNull { it.name.equals("whatever", true) }
        assertNull(theNull)

        assertEquals(ONE, BaeldungNumber.byNameIgnoreCaseOrNull("one"))
        assertNull(BaeldungNumber.byNameIgnoreCaseOrNull("whatever"))
    }

    @Test
    fun `given a string, when using byNameIgnoreCase, should get the enum object`() {
        val theOne = enumByNameIgnoreCase<BaeldungNumber>("ONE")
        assertEquals(ONE, theOne)

        val theTwo = enumByNameIgnoreCase<BaeldungNumber>("two")
        assertEquals(TWO, theTwo)

        val theNull = enumByNameIgnoreCase<BaeldungNumber>("whatever")
        assertNull(theNull)

        val theDefault = enumByNameIgnoreCase("whatever", UNKNOWN)
        assertEquals(UNKNOWN, theDefault)
    }
}