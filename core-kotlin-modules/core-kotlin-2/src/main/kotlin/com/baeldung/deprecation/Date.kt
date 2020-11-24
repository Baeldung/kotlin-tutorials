package com.baeldung.deprecation

import java.time.Instant
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneId
import java.time.temporal.ChronoField

data class Date(val millisSinceEpoch: Long) {

    @Deprecated("No longer valid")
    private val zeroBased = true

    private val internal = LocalDateTime.ofInstant(Instant.ofEpochMilli(millisSinceEpoch), ZoneId.of("UTC"))

    @Deprecated("Use the new month() method", replaceWith = ReplaceWith("month()"))
    fun monthNumber(): Int = internal.get(ChronoField.MONTH_OF_YEAR)

    fun month(): Month = internal.month

    companion object {

        @Deprecated("Use java.time instead",
          replaceWith = ReplaceWith("LocalDateTime.now()", imports = ["java.time.LocalDateTime"]))
        fun now(): Date = Date(0)
    }
}

@Deprecated("Use plain string instead")
typealias DateFormat = String

fun main() {
    val epoch = Date(0)
    println(epoch.monthNumber())
    println(epoch.month())

    Date.now()
}
