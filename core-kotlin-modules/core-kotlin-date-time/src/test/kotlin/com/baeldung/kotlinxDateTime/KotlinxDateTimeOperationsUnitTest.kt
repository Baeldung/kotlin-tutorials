package com.baeldung.kotlinxDateTime

import com.baeldung.dates.kotlinxDatetime.KotlinxDateTimeOperations
import kotlinx.datetime.*
import kotlinx.datetime.format.DateTimeComponents
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class KotlinxDateTimeOperationsUnitTest {

    @Test
    fun `given an Instant convert it to LocalDateTime`() {
        val kotlinxDateTimeOperations = KotlinxDateTimeOperations()
        val instant = kotlinxDateTimeOperations.getInstant()
        assertTrue { instant is Instant }
        val localDateTime = kotlinxDateTimeOperations.getLocalDateTimeSystemTimeZoneFromInstant(instant)
        assertTrue { localDateTime is LocalDateTime }
        val localDateTimeUCT = kotlinxDateTimeOperations.getLocalDateTimeUTCFromInstant(instant)
        assertTrue { localDateTimeUCT is LocalDateTime }
    }

    @Test
    fun `compare two LocalDateTime from different from the same TimeZone`() {
        val kotlinxDateTimeOperations = KotlinxDateTimeOperations()
        val instant = kotlinxDateTimeOperations.getInstant()
        val localDateTimeBrazil = kotlinxDateTimeOperations.getLocalDateTimeBrazilTimeZoneFromInstant(instant = instant)
        val localDateTimeUtcMinus3 = kotlinxDateTimeOperations.getLocalDateTimeUtcMinus3TimeZoneFromInstant(instant = instant)
        assertEquals(0, localDateTimeBrazil.compareTo(localDateTimeUtcMinus3))
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
        val localDateFromCustom = kotlinxDateTimeOperations.parseLocalDateFromCustom()
        assertTrue { localDateFromCustom is LocalDate }
    }

    @Test
    fun `get DateTimeComponents from function`() {
        val kotlinxDateTimeOperations = KotlinxDateTimeOperations()
        val dateTimeComponents = kotlinxDateTimeOperations.getDateTimeComponents()
        assertTrue { dateTimeComponents is DateTimeComponents }
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
        val kotlinxDateTimeOperations = KotlinxDateTimeOperations()
        val monthsBetween = kotlinxDateTimeOperations.getMonthsBetween()
        assertEquals(4, monthsBetween)
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