package com.baeldung.dates

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class ConvertStringDateTest {

    @Test
    fun givenString_whenDefaultFormat_thenLocalDateCreated() {

        val localDate = LocalDate.parse("2022-01-06")

        Assertions.assertThat(localDate).isEqualTo("2022-01-06")
    }

    @Test
    fun givenString_whenCustomFormat_thenLocalDateCreated() {

        val localDate = LocalDate.parse("01-06-2022", DateTimeFormatter.ofPattern("MM-dd-yyyy"))

        Assertions.assertThat(localDate).isEqualTo("2022-01-06")
    }

    @Test
    fun givenString_whenDefaultFormat_thenLocalDateTimeCreated() {

        val localDateTime = LocalDateTime.parse("2022-01-06T21:30:10")

        Assertions.assertThat(localDateTime.toString()).isEqualTo("2022-01-06T21:30:10")
    }

    @Test
    fun givenString_whenCustomFormat_thenLocalDateTimeCreated() {

        val text = "2022-01-06 20:30:45"
        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val localDateTime = LocalDateTime.parse(text, pattern)

        Assertions.assertThat(localDateTime).isEqualTo("2022-01-06T20:30:45")
    }

    @Test
    fun givenString_whenParseWithTimeZone_thenZonedDateTimeIsCreated() {

        val text = "2022-01-06 20:30:45 America/Los_Angeles"
        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")
        val zonedDateTime = ZonedDateTime.parse(text, pattern)

        Assertions.assertThat(zonedDateTime.zone).isEqualTo(ZoneId.of("America/Los_Angeles"))
    }

    @Test
    fun givenString_whenParseDate_thenUtilDateIsCreated() {

        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val text = "2022-01-06"
        val date = formatter.parse(text)

        Assertions.assertThat(formatter.format(date)).isEqualTo("2022-01-06")
    }

    @Test
    fun givenString_whenParseDateWithTimeZone_thenUtilDateIsCreated() {

        val formatter = SimpleDateFormat("yyyy-MM-dd")
        formatter.timeZone = TimeZone.getTimeZone("America/Los_Angeles");

        val text = "2022-01-06"
        val date = formatter.parse(text)

        Assertions.assertThat(formatter.format(date)).isEqualTo("2022-01-06")
    }
}