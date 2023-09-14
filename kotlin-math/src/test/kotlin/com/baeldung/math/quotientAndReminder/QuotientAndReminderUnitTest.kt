package com.baeldung.math.quotientAndReminder

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class QuotientAndReminderUnitTest {

    @Test
    fun `when using 'division' and 'modulus' operators then get expected quotient and reminder`() {
        val quotient1 = 42 / 4
        val reminder1 = 42 % 4
        assertEquals(10, quotient1)
        assertEquals(2, reminder1)


        val quotient2 = -42 / 4
        val reminder2 = -42 % 4
        assertEquals(-10, quotient2)
        assertEquals(-2, reminder2)

        val quotient3 = -42 / 2
        val reminder3 = -42 % 2
        assertEquals(-21, quotient3)
        assertEquals(0, reminder3)
    }

    fun getQuotientAndReminder(dividend: Int, divisor: Int): Pair<Int, Int> {
        require(divisor != 0) { "The divisor must not be zero" }
        return dividend / divisor to dividend % divisor
    }


    @Test
    fun `when calling getQuotientAndReminder then get expected quotient and reminder`() {
        getQuotientAndReminder(42, 4).apply {
            assertEquals(10, first)
            assertEquals(2, second)
        }

        getQuotientAndReminder(-42, 4).apply {
            assertEquals(-10, first)
            assertEquals(-2, second)
        }

        getQuotientAndReminder(-42, 2).apply {
            assertEquals(-21, first)
            assertEquals(0, second)
        }
    }

    @Test
    fun `when calling getQuotientAndReminder with 0 as the divisor then get expected exception`() {
        assertThrows<IllegalArgumentException> {
            getQuotientAndReminder(42, 0)
        }.also { assertEquals("The divisor must not be zero", it.message) }
    }


    infix fun Int.dividedBy(divisor: Int): Pair<Int, Int> {
        require(divisor != 0) { "The divisor must not be zero" }
        return this / divisor to this % divisor
    }

    @Test
    fun `when calling dividedBy() then get expected quotient and reminder`() {
        (42 dividedBy 4).apply {
            assertEquals(10, first)
            assertEquals(2, second)
        }

        (-42 dividedBy 4).apply {
            assertEquals(-10, first)
            assertEquals(-2, second)
        }

        (-42 dividedBy 2).apply {
            assertEquals(-21, first)
            assertEquals(0, second)
        }
    }

}