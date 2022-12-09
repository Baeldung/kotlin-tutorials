package com.baeldung.dates.datetime

import org.joda.time.format.DateTimeFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class DateDifference {

    fun dateDiffUsingJavaClasses(fromDate: String, toDate: String): Period {
        val dateFormatter: DateTimeFormatter =  DateTimeFormatter.ofPattern("MM/dd/yyyy")

        val from = LocalDate.parse(fromDate, dateFormatter)
        val to = LocalDate.parse(toDate, dateFormatter)

        return Period.between(from, to)
    }

    fun dateDiffUsingJodaTime(fromDate: String, toDate: String): org.joda.time.Period {
        val dateFormatter: org.joda.time.format.DateTimeFormatter? =  DateTimeFormat.forPattern("MM/dd/yyyy")

        val from = org.joda.time.LocalDate.parse(fromDate, dateFormatter)
        val to = org.joda.time.LocalDate.parse(toDate, dateFormatter)

        return org.joda.time.Period(from, to)
    }
}