package com.baeldung.dates.datetime

import org.joda.time.format.DateTimeFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

class DateDifference {

    fun dateDiffUsingJava8(fromDate: String, toDate: String): Map<Int, Int> {
        val dateFormatter: DateTimeFormatter =  DateTimeFormatter.ofPattern("MM/dd/yyyy")

        val from = LocalDate.parse(fromDate, dateFormatter)
        val to = LocalDate.parse(toDate, dateFormatter)

        val period = Period.between(from, to)

        val years = period.years
        val months = period.months
        val days = period.days

        return mapOf(
            Calendar.YEAR to years,
            Calendar.MONTH to months,
            Calendar.DATE to days
        )
    }

    fun dateDiffUsingJodaTime(fromDate: String, toDate: String): Map<Int, Int> {
        val dateFormatter: org.joda.time.format.DateTimeFormatter? =  DateTimeFormat.forPattern("MM/dd/yyyy")

        val from = org.joda.time.LocalDate.parse(fromDate, dateFormatter)
        val to = org.joda.time.LocalDate.parse(toDate, dateFormatter)

        val period = org.joda.time.Period(from, to)

        val years = period.years
        val months = period.months
        val days = period.weeks * 7 + period.days

        return mapOf(
            Calendar.YEAR to years,
            Calendar.MONTH to months,
            Calendar.DATE to days
        )
    }
}