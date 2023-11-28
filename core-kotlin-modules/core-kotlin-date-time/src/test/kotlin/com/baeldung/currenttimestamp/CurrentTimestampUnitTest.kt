package com.baeldung.currenttimestamp

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CurrentTimestampUnitTest {

    @Test
    fun givenCurrentUTCDateFromUtilLibrary_whenAccessTimeProperty_thenGetCurrentTimestamp() {
        val currentDateTime: java.util.Date = java.util.Date() // UTC time zone
        val currentTimestamp: Long = currentDateTime.time
        Assertions.assertNotNull(currentTimestamp)
    }


    @Test
    fun givenCurrentInstantFromTimeLibrary_whenInvokeNowMethod_thenGetCurrentTimestamp() {
        val currentInstant: java.time.Instant = java.time.Instant.now()
        val currentTimestamp: Long = currentInstant.toEpochMilli()
        Assertions.assertNotNull(currentTimestamp)
    }

    @Test
    fun givenSystemDefaultZonedDateTime_whenInvokeToEpochMilliMethod_thenGetCurrentTimestamp() {
        val zoneId: java.time.ZoneId = java.time.ZoneId.systemDefault()
        val currentZonedDateTime: java.time.ZonedDateTime = java.time.ZonedDateTime.now(zoneId)
        val currentTimestamp: Long = currentZonedDateTime.toInstant().toEpochMilli()
        Assertions.assertNotNull(currentTimestamp)
    }

    @Test
    fun givenCustomZonedDateTime_whenInvokeToEpochMilliMethod_thenGetCurrentTimestamp() {
        val zoneId: java.time.ZoneId = java.time.ZoneId.of("America/New_York")
        val currentZonedDateTime: java.time.ZonedDateTime = java.time.ZonedDateTime.now(zoneId)
        val currentTimestamp: Long = currentZonedDateTime.toInstant().toEpochMilli()
        Assertions.assertNotNull(currentTimestamp)
    }

    @Test
    fun givenLocalDateTime_whenInvokeToEpochMilliMethod_thenGetCurrentTimestamp() {
        val localDateTime: java.time.LocalDateTime = java.time.LocalDateTime.now()
        val zonedDateTime: java.time.ZonedDateTime = localDateTime.atZone(java.time.ZoneId.of("Asia/Kolkata"))
        val currentTimestamp: Long = zonedDateTime.toInstant().toEpochMilli()
        Assertions.assertNotNull(currentTimestamp)
    }

    @Test
    fun givenOffsetDateTime_whenInvokeToEpochMilliMethod_thenGetCurrentTimestamp() {
        val currentDate = java.time.LocalDate.now()
        val currentTime = java.time.OffsetTime.now()
        val currentOffsetDateTime = java.time.OffsetDateTime.of(currentDate, currentTime.toLocalTime(), currentTime.offset)
        val currentTimestamp = currentOffsetDateTime.toInstant().toEpochMilli()
        Assertions.assertNotNull(currentTimestamp)
    }

    @Test
    fun givenClockFromTimeLibrary_whenInvokeSystemUTCMethod_thenGetCurrentTimestamp() {
        val currentTimestamp = java.time.Clock.systemUTC()
        Assertions.assertNotNull(currentTimestamp)
    }

    @Test
    fun givenCurrentInstantFromJodaLibrary_whenAccessMillisProperty_thenGetCurrentTimestamp() {
        val currentInstant: org.joda.time.Instant = org.joda.time.Instant.now()
        val currentTimestamp: Long = currentInstant.millis
        Assertions.assertNotNull(currentTimestamp)
    }

    @Test
    fun givenCurrentDateFromJodaLibrary_whenInvokeNowMethod_thenGetCurrentTimestamp() {
        val currentDateTime: org.joda.time.DateTime = org.joda.time.DateTime.now()
        val currentTimestamp: Long = currentDateTime.millis
        Assertions.assertNotNull(currentTimestamp)
    }

}