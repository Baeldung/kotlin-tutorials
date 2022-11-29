package com.baeldung.dates.datetime

import org.junit.Test
import java.util.Calendar
import kotlin.test.assertEquals

class DateDifferenceTest {

    @Test
    fun given2DateStrings_whenUsingJava8DateTimeClasses_thenReturnDateDifference() {
        val dateDiff = DateDifference()
        val from = "02/01/2007"
        val to = "11/25/2022"
        val diffMap = dateDiff.dateDiffUsingJava8(from, to)
        val years = diffMap[Calendar.YEAR]
        val months = diffMap[Calendar.MONTH]
        val days = diffMap[Calendar.DATE]

        assertEquals(15, years)
        assertEquals(9, months)
        assertEquals(24, days)
    }

    @Test
    fun given2DateStrings_whenUsingJodaDateTimeClasses_thenReturnDateDifference() {
        val dateDiff = DateDifference()
        val from = "02/01/2007"
        val to = "11/25/2022"
        val diffMap = dateDiff.dateDiffUsingJodaTime(from, to)
        val years = diffMap[Calendar.YEAR]
        val months = diffMap[Calendar.MONTH]
        val days = diffMap[Calendar.DATE]

        assertEquals(15, years)
        assertEquals(9, months)
        assertEquals(24, days)
    }
}