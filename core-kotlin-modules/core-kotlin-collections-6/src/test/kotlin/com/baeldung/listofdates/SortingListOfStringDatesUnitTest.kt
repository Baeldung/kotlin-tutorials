package com.baeldung.listofdates

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.test.assertNotNull

class SortingListOfStringDatesUnitTest {

    @Test
    fun `given a list of string dates when sorting with DateTimeFormatter then a sorted list is generated`() {
        val dates = listOf("31-12-2023", "01-01-2023", "15-06-2023")

        val dateFormat = SimpleDateFormat("dd-MM-yyyy")

        val sortedDates = dates.sortedByDescending { dateFormat.parse(it) }

        val expectedSortedDates = listOf("31-12-2023", "15-06-2023", "01-01-2023")

        assertNotNull(sortedDates)
        assertEquals(sortedDates, expectedSortedDates)
    }

    @Test
    fun `given a list of string dates when sorting with LocalDate then a sorted list is generated`() {
        val dates = listOf("31-12-2023", "01-01-2023", "15-06-2023")

        val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val sortedDates = dates.sortedByDescending { LocalDate.parse(it, dateFormatter) }

        val expectedSortedDates = listOf("31-12-2023", "15-06-2023", "01-01-2023")

        assertNotNull(sortedDates)
        assertEquals(sortedDates, expectedSortedDates)
    }

    @Test
    fun `given a list of string dates when sorting using custom comparator then a sorted list is generated`() {
        val dates = listOf("31-12-2023", "01-01-2023", "15-06-2023")

        val sortedDates = dates.sortedWith(compareByDescending {
            val (day, month, year) = it.split("-")
            "$year-$month-$day"
        })

        val expectedSortedDates = listOf("31-12-2023", "15-06-2023", "01-01-2023")

        assertNotNull(sortedDates)
        assertEquals(sortedDates, expectedSortedDates)
    }
}