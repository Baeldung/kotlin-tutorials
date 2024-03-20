package com.baeldung.listofdates

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.test.assertNotNull

class SortingListOfStringDatesTest {

    @Test
    fun `given a list of string dates when sorting with sortedDescending then a sorted list is generated`() {
        val dates = listOf("31-12-2023", "01-01-2023", "15-06-2023")

        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val sortedDates = dates.map { LocalDate.parse(it, formatter) }
                .sortedDescending()
                .map { it.format(formatter) }

        val expectedSortedDates = listOf("31-12-2023", "15-06-2023", "01-01-2023")

        assertNotNull(sortedDates)
        assertEquals(sortedDates, expectedSortedDates)
    }

    @Test
    fun `given a list of string dates when sorting with sortedByDescending then a sorted list is generated`() {
        val dates = listOf("31-12-2023", "01-01-2023", "15-06-2023")

        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

        val sortedDates = dates.sortedByDescending {
            LocalDate.parse(it, dateTimeFormatter)
        }

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