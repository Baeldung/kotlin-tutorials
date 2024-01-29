package com.baeldung.convertAnyToInt

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class ConvertAnyToIntTest {

    @Test
    fun `when casting to Int then it works`() {
        val anyValue: Any = 11
        val intValue: Int = anyValue as Int
        assertThat(intValue).isEqualTo(11)
    }

    @Test
    fun `when casting to Int then exception is thrown`() {
        val anyValue: Any = "Not a number"
        assertThrows<ClassCastException> {
            val intValue: Int = anyValue as Int
        }
    }

    @Test
    fun `when casting to Int then exception is handled`() {
        val anyValue: Any = "Not a number"
        assertThrows<NumberFormatException> {
            if (anyValue is Int) {
                val intValue: Int = anyValue
            } else {
                throw NumberFormatException("Provided value is not a number")
            }
        }
    }

    @Test
    fun `when converting to Int then it works`() {
        val anyValue: Any = 11
        val stringValue: String = anyValue.toString()
        val intValue = stringValue.toInt()
        assertThat(intValue).isEqualTo(11)
    }

    @Test
    fun `when converting to Int then exception is thrown`() {
        val anyValue: Any = "Not a number"
        assertThrows<NumberFormatException> {
            anyValue.toString().toInt()
        }
    }

    @Test
    fun `when converting to Int then exception is handled`() {
        val anyValue: Any = "Not a number"
        assertDoesNotThrow {
            try {
                anyValue.toString().toInt()
            } catch (e: NumberFormatException) {
                println("Provided value is not an number")
            }
        }
    }
}