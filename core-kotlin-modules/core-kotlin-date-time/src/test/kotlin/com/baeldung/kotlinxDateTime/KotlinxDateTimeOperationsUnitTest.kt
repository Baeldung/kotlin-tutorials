package com.baeldung.kotlinxDateTime

import com.baeldung.dates.kotlinxDatetime.KotlinxDateTimeOperations
import kotlinx.datetime.*
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class KotlinxDateTimeOperationsUnitTest {

    @Test
    fun `given an Instant convert it to LocalDateTime`() {
        java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("UTC"));

        val instant = Instant.fromEpochSeconds(1722427200)
        assertEquals("2024-07-31T12:00:00Z", instant.toString())
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault()) // System is in UTC
        assertEquals("2024-07-31T12:00", localDateTime.toString())
    }

    @Test
    fun `compare two LocalDateTime from different from the same TimeZone`() {
        val kotlinxDateTimeOperations = KotlinxDateTimeOperations()
        val instant = Instant.fromEpochSeconds(1722427200)
        val localDateTimeBrazil = kotlinxDateTimeOperations.getLocalDateTimeBrazilTimeZoneFromInstant(instant = instant)
        assertEquals("2024-07-31T09:00", localDateTimeBrazil.toString())
        val localDateTimeUtcMinus3 = kotlinxDateTimeOperations.getLocalDateTimeUtcMinus3TimeZoneFromInstant(instant = instant)
        assertEquals("2024-07-31T09:00", localDateTimeUtcMinus3.toString())
    }

    @Test
    fun `create a LocalDateTime from function`() {
        val kotlinxDateTimeOperations = KotlinxDateTimeOperations()
        val localDateTime = kotlinxDateTimeOperations.createLocalDateTime()
        assertTrue { localDateTime is LocalDateTime }
    }

    @Test
    fun `given an Instant create a LocalDate`() {
        val kotlinxDateTimeOperations = KotlinxDateTimeOperations()
        val localDate = kotlinxDateTimeOperations.getLocalDate()
        assertTrue { localDate is LocalDate }
        val instant = kotlinxDateTimeOperations.getInstant()
        val localDateFromInstant = kotlinxDateTimeOperations.convertInstantToLocalDate(instant)
        assertTrue { localDateFromInstant is LocalDate }
    }

    @Test
    fun `given an Instant create a LocalTime`() {
        val kotlinxDateTimeOperations = KotlinxDateTimeOperations()
        val localTime = kotlinxDateTimeOperations.getLocalTime()
        assertTrue { localTime is LocalTime }
        val instant = kotlinxDateTimeOperations.getInstant()
        val localTimeFromInstant = kotlinxDateTimeOperations.convertInstantToLocalTime(instant)
        assertTrue { localTimeFromInstant is LocalTime }
    }

    @Test
    fun `get EpochMilliSeconds from function`() {
        val kotlinxDateTimeOperations = KotlinxDateTimeOperations()
        val epochMilliseconds = kotlinxDateTimeOperations.compareInstantAndEpochMilliseconds()
        assertEquals(1722475458286, epochMilliseconds)
    }

    @Test
    fun `parse String using different functions`() {
        val kotlinxDateTimeOperations = KotlinxDateTimeOperations()
        val instant = kotlinxDateTimeOperations.parseStringToInstant()
        assertTrue { instant is Instant }
        val localDateTime = kotlinxDateTimeOperations.parseStringToLocalDateTime()
        assertTrue { localDateTime is LocalDateTime }
        val localDate = kotlinxDateTimeOperations.parseStringToLocalDate()
        assertTrue { localDate is LocalDate }
        val localTime = kotlinxDateTimeOperations.parseStringToLocalTime()
        assertTrue { localTime is LocalTime }
        val dateTimeFormat = kotlinxDateTimeOperations.getDateTimeFormat()
        assertEquals("31/07/2024", dateTimeFormat.format(localDate))
    }

    @Test
    fun `get DateTimeComponents from function`() {
        val monthDay = DateTimeComponents.Format {
            dayOfMonth()
            char('-')
            monthName(MonthNames.ENGLISH_FULL)
        }.parse("31-July")
        assertEquals("7", monthDay.monthNumber.toString())
        assertEquals("JULY", monthDay.month.toString())
        assertEquals("31", monthDay.dayOfMonth.toString())
    }

    @Test
    fun `compare Duration from two Instants`() {
        val instant = Instant.parse("2024-07-31T22:00:00.000Z")
        val olderInstant = Instant.parse("2024-03-15T22:00:00.000Z")
        val duration = instant - olderInstant
        assertEquals(138, duration.inWholeDays)
        assertEquals(11923200000000000, duration.inWholeNanoseconds)
    }

    @Test
    fun `get DateTimePeriod from two Instants`() {
        val instant = Instant.parse("2024-07-31T22:00:00.000Z")
        val olderInstant = Instant.parse("2022-03-15T12:05:01.050Z")
        val dateTimePeriod = olderInstant.periodUntil(instant, TimeZone.UTC)
        assertEquals(2, dateTimePeriod.years)
        assertEquals(4, dateTimePeriod.months)
        assertEquals(16, dateTimePeriod.days)
        assertEquals(9, dateTimePeriod.hours)
        assertEquals(54, dateTimePeriod.minutes)
        assertEquals(58, dateTimePeriod.seconds)
        assertEquals(950000000, dateTimePeriod.nanoseconds)
    }

    @Test
    fun `get months between function result`() {
        val instant = Instant.parse("2024-07-31T22:00:00.000Z")
        val olderInstant = Instant.parse("2024-03-15T22:00:00.000Z")
        val monthsUntil = olderInstant.monthsUntil(instant, TimeZone.UTC)
        val months = olderInstant.until(instant, DateTimeUnit.MONTH, TimeZone.UTC)
        assertEquals(4, monthsUntil.toLong())
        assertEquals(4, months)
    }

    @Test
    fun `run arithmetic operations funcitons`() {
        val kotlinxDateTimeOperations = KotlinxDateTimeOperations()
        assertDoesNotThrow {
            kotlinxDateTimeOperations.addAndSubtract()
            kotlinxDateTimeOperations.addMinutesToLocalDateTime()
        }
    }

}