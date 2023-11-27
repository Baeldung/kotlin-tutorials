package com.baeldung.timeconversion

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.DurationUnit
import kotlin.time.toDuration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.milliseconds

class TimeConversionUnitTest {


    @Test
    fun `given milliseconds, when convert to minutes and seconds using primitive arithmetic operations, then give correct minutes and seconds`() {
        val millis: Long = 123456L
        val mins = millis / 60000
        val secs = (millis % 60000) / 1000

        assertEquals(2, mins)
        assertEquals(3, secs)
    }

    @Test
    fun `given epoch milliseconds, when convert to minutes and seconds using kotlinx-datetime, then give correct minutes and seconds`() {
        val epochMillis: Long = 123456L

        val instant = Instant.fromEpochMilliseconds(epochMillis)
        val localDateTime = instant.toLocalDateTime(TimeZone.UTC)

        val mins = localDateTime.hour * 60 + localDateTime.minute.toLong()
        val secs = localDateTime.second.toLong()

        assertEquals(2, mins)
        assertEquals(3, secs)
    }

    @Test
    fun `given milliseconds, when convert to minutes and seconds using kotlin DurationUnit, then give correct minutes and seconds`() {
        val millis: Long = 123456L
        val duration = millis.toDuration(DurationUnit.MILLISECONDS)
        val mins = duration.inWholeMinutes
        val secs = duration.minus(mins.toDuration(DurationUnit.MINUTES)).inWholeSeconds

        assertEquals(2, mins)
        assertEquals(3, secs)
    }

    @Test
    fun `given milliseconds, when convert to minutes and seconds using Duration extension properties, then give correct minutes and seconds`() {
        val millis: Long = 123456L
        val mins = millis.milliseconds.inWholeMinutes
        val secs = millis.milliseconds.inWholeSeconds - mins.minutes.inWholeSeconds

        assertEquals(2, mins)
        assertEquals(3, secs)
    }
}