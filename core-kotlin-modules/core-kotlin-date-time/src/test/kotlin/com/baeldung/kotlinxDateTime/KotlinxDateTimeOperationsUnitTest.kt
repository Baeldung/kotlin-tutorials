package com.baeldung.kotlinxDateTime

import com.baeldung.dates.kotlinxDatetime.KotlinxDateTimeOperations
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.format.DateTimeComponents
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.Duration

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
    fun `create TimeZone from different functions`() {
        val kotlinxDateTimeOperations = KotlinxDateTimeOperations()
        val timeZoneFromUTC = kotlinxDateTimeOperations.getTimeZoneFromUTC()
        assertTrue { timeZoneFromUTC is TimeZone }
        val timeZone = kotlinxDateTimeOperations.getBrazillianTimeZone()
        assertTrue { timeZone is TimeZone }
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
    fun `get Duration from function`() {
        val kotlinxDateTimeOperations = KotlinxDateTimeOperations()
        val duration = kotlinxDateTimeOperations.getDuration()
        assertTrue { duration is Duration }
    }

    @Test
    fun `get DateTimePeriod from function`() {
        val kotlinxDateTimeOperations = KotlinxDateTimeOperations()
        val dateTimePeriod = kotlinxDateTimeOperations.getDateTimePeriod()
        assertTrue { dateTimePeriod is DateTimePeriod }
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