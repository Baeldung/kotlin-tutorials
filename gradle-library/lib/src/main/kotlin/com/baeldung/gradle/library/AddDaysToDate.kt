package com.baeldung.gradle.library

import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus

fun addDaysToDate(date: LocalDate, daysToAdd: Int): LocalDate {
    val period = DatePeriod(days = daysToAdd)
    return date.plus(period)
}