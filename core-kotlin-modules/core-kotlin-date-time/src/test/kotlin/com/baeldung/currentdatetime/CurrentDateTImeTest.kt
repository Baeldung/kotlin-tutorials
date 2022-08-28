package com.baeldung.currentdatetime

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class CurrentDateTImeTest {

    @Test
    fun givenLocalDate_whenGettingLocalDateWithNow_CurrentDateShouldBeCreated() {

        val current = LocalDate.now()

        Assertions.assertThat(current).isNotNull()
    }

    @Test
    fun givenLocalDate_whenGettingLocalDateTimeWithNow_CurrentDateTimeShouldBeCreated() {

        val current = LocalDateTime.now()

        Assertions.assertThat(current).isNotNull()
    }

    @Test
    fun givenLocalDateTime_whenFormattingLocalDateTime_FormattedLocalDateTimeIsCreated() {

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val current = LocalDateTime.now().format(formatter)

        Assertions.assertThat(current).isNotNull()
    }

    @Test
    fun givenCalendar_whenGettingCurrentTimeWithFormat_CurrentDateTimeIsCreated() {

        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val current = formatter.format(time)

        Assertions.assertThat(current).isNotNull()
    }

    @Test
    fun givenCalendar_whenGettingCurrentTimeFromComponents_CurrentDateTimeIsCreated() {

        val calendar = Calendar.getInstance()

        val current = LocalDateTime.of(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            calendar.get(Calendar.SECOND)
        )

        Assertions.assertThat(current).isNotNull()
    }

    @Test
    fun givenDate_whenANewInstanceIsCreated_CurrentDateShouldBeCreated() {

        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val date = Date()
        val current = formatter.format(date)

        Assertions.assertThat(current).isNotNull()
    }
}