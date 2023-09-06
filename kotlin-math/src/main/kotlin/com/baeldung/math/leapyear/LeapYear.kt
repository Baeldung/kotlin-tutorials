package com.baeldung.math.leapyear

import java.util.Calendar

fun isLeapYearUsingSingleExpression(year: Int): Boolean {
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
}

fun isLeapYearUsingIfElse(year: Int): Boolean {
    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
        return true
    } else {
        return false
    }
}

fun isLeapYearUsingWhenExpression(year: Int): Boolean {
    when {
        (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0) -> return true
        else -> return false
    }
}

fun Calendar.isLeapYearCalendar(): Boolean {
    val daysInYear = this.getActualMaximum(Calendar.DAY_OF_YEAR)
    return daysInYear > 365
}

tailrec fun isLeapYearRecursive(year: Int): Boolean = when {
    year < 0 -> false
    year == 0 -> true
    year % 100 == 0 -> isLeapYearRecursive(year - 400)
    else -> year % 4 == 0 || isLeapYearRecursive(year - 4)
}