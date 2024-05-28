package com.baeldung.duration

import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.time.*
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import java.time.Duration as JavaDuration
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

class DurationUnitTest {
    @Test
    fun `given numeric values, create duration instance using companion`() {
        val tenMinutes = 10.minutes
        assertEquals(10L, tenMinutes.inWholeMinutes)
        assertEquals(600L, tenMinutes.inWholeSeconds)
        val tenSeconds = 10.seconds
        assertEquals(10L, tenSeconds.inWholeSeconds)
        val zero = Duration.ZERO
        assertEquals(0L, zero.inWholeMicroseconds)
    }

    @Test
    fun `given numeric values, create duration instance using toDuration and unit`() {
        val tenMinutes = 10.toDuration(DurationUnit.MINUTES)
        assertEquals(10L, tenMinutes.inWholeMinutes)
        assertEquals(600L, tenMinutes.inWholeSeconds)
        val tenSeconds = 10.toDuration(DurationUnit.SECONDS)
        assertEquals(10L, tenSeconds.inWholeSeconds)
        val zero = Duration.ZERO
        assertEquals(0L, zero.inWholeMicroseconds)
        val infinite = Duration.INFINITE
        assertEquals(true, infinite.isInfinite())
    }

    @Test
    fun `given ISO8601 string values, create duration instance`() {
        val tenMinDuration = Duration.parseIsoString("PT10M")
        assertEquals(600L, tenMinDuration.inWholeSeconds)
        assertEquals("PT10M", tenMinDuration.toIsoString())
        val tenDays = Duration.parseIsoString("P10D")
        assertEquals(10L, tenDays.inWholeDays)
        val tenDaysAndOneHour = Duration.parseIsoString("P10DT1H")
        assertEquals(241L, tenDaysAndOneHour.inWholeHours)
        assertEquals("PT241H", tenDaysAndOneHour.toIsoString())
        val tenDaysWithAllUnits = Duration.parseIsoString("P10DT1H5M7S")
        assertEquals(867907L, tenDaysWithAllUnits.inWholeSeconds)
    }

    @Test
    fun `given a duration, convert into java duration`() {
        val sixHundredSeconds = 600.seconds
        val javaDuration: java.time.Duration = sixHundredSeconds.toJavaDuration()
        val javaDurationInst = JavaDuration.ofSeconds(600)
        assertEquals(javaDuration, javaDurationInst)
    }

    @Test
    fun `given two different durations, combine them into single duration`() {
        val tenMinutes = 10.minutes
        val fiveHours = 5.hours
        val fiveHoursPlusTenMin = tenMinutes + fiveHours
        assertEquals(310L, fiveHoursPlusTenMin.inWholeMinutes)
        val fiveHoursMinusTenMin = fiveHours - tenMinutes
        assertEquals(290L, fiveHoursMinusTenMin.inWholeMinutes)
        val timesMinutes = tenMinutes.times(3)
        assertEquals(30L, timesMinutes.inWholeMinutes)
        val sixSecs = tenMinutes.div(100)
        assertEquals(6, sixSecs.inWholeSeconds)
    }

    @Test
    fun `given two different durations, check if they are equal`() {
        val tenMinutes = 10.minutes
        val sixHundredSeconds = 600.seconds
        assert(tenMinutes == sixHundredSeconds)
    }

    @Test
    fun `given two different durations, compare them`() {
        val tenMinutes = 10.minutes
        val fiveHours = 5.hours
        assertTrue { fiveHours > tenMinutes }
        assertFalse { fiveHours < tenMinutes }
        assertTrue { fiveHours == 300.minutes }
        assertTrue { fiveHours.isPositive() }
    }

    @Test
    fun `given a duration, break down into smaller parts`() {
        val seventyMinutes = 70.minutes
        val asStr = seventyMinutes.toComponents { hrs, min, sec, nanos -> "${hrs}:${min}" }
        assertEquals("1:10", asStr)
    }

    @Test
    fun `given a two datetime, calculate the duration between them`() {
        val datetime1 = LocalDateTime.now()
        val datetime2 = datetime1.minusDays(1).minusHours(1)
        val duration = java.time.Duration.between(datetime2, datetime1).toKotlinDuration()
        val expectedDuration = 25.hours
        assertEquals(expectedDuration, duration)
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `use duration api to measure execution time`() {
        val operationDuration = kotlin.time.measureTime {
            Thread.sleep(510)
        }
        assertTrue(operationDuration > 500.milliseconds)
    }

}
