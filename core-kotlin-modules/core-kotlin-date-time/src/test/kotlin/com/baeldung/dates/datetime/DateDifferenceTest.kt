package com.baeldung.dates.datetime

import org.junit.Test
import kotlin.test.assertEquals

class DateDifferenceTest {

    @Test
    fun given2DateStrings_whenUsingJava8DateTimeClasses_thenReturnDateDifference() {
        val dateDiff = DateDifference()
        val from = "02/01/2007"
        val to = "11/25/2022"
        val period = dateDiff.dateDiffUsingJava8(from, to)

        val years = period.years
        val months = period.months
        val days = period.days

        assertEquals(15, years)
        assertEquals(9, months)
        assertEquals(24, days)
    }

    @Test
    fun given2DateStrings_whenUsingJodaDateTimeClasses_thenReturnDateDifference() {
        val dateDiff = DateDifference()
        val from = "02/01/2007"
        val to = "11/25/2022"
        val period = dateDiff.dateDiffUsingJodaTime(from, to)

        val years = period.years
        val months = period.months
        val days = period.weeks * 7 + period.days

        assertEquals(15, years)
        assertEquals(9, months)
        assertEquals(24, days)
    }
}