package com.baeldung.math.leapyear

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class LeapYearUnitTest {

    @Test
    fun `test isLeapYearUsingSingleExpression for a leap year should be true`() {
        Assertions.assertTrue(isLeapYearUsingSingleExpression(2024))
        Assertions.assertTrue(isLeapYearUsingSingleExpression(2000))
    }

    @Test
    fun `test isLeapYearUsingSingleExpression for a non-leap year should be false`() {
        Assertions.assertFalse(isLeapYearUsingSingleExpression(2023))
        Assertions.assertFalse(isLeapYearUsingSingleExpression(1900))
    }

    @Test
    fun `test isLeapYearUsingIfElse for a leap year should be true`() {
        Assertions.assertTrue(isLeapYearUsingIfElse(2024))
        Assertions.assertTrue(isLeapYearUsingIfElse(2000))
    }

    @Test
    fun `test isLeapYearUsingIfElse for a non-leap year should be false`() {
        Assertions.assertFalse(isLeapYearUsingIfElse(2023))
        Assertions.assertFalse(isLeapYearUsingIfElse(1900))
    }

    @Test
    fun `test isLeapYear extension function for a leap year should be true`() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 2024)
        Assertions.assertTrue(calendar.isLeapYearCalendar())

        calendar.set(Calendar.YEAR, 2000)
        Assertions.assertTrue(calendar.isLeapYearCalendar())
    }

    @Test
    fun `test isLeapYear extension function for a non-leap year should be false`() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 2023)
        Assertions.assertFalse(calendar.isLeapYearCalendar())

        calendar.set(Calendar.YEAR, 1900)
        Assertions.assertFalse(calendar.isLeapYearCalendar())
    }

    @Test
    fun `test isLeapYearUsingWhenExpression for a leap year should be true`() {
        Assertions.assertTrue(isLeapYearUsingWhenExpression(2024))
        Assertions.assertTrue(isLeapYearUsingWhenExpression(2000))
    }

    @Test
    fun `test isLeapYearUsingWhenExpression for a non-leap year should be false`() {
        Assertions.assertFalse(isLeapYearUsingWhenExpression(2023))
        Assertions.assertFalse(isLeapYearUsingWhenExpression(1900))
    }

    @Test
    fun `test isLeapYearRecursive for a leap year should be true`() {
        Assertions.assertTrue(isLeapYearRecursive(2024))
        Assertions.assertTrue(isLeapYearRecursive(2000))
    }

    @Test
    fun `test isLeapYearRecursive for a non-leap year should be false`() {
        Assertions.assertFalse(isLeapYearRecursive(2023))
        Assertions.assertFalse(isLeapYearRecursive(1900))
    }
}
