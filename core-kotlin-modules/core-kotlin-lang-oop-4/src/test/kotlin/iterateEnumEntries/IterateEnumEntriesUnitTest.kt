package iterateEnumEntries

import iterateEnumEntries.Holiday.*
import iterateEnumEntries.Weekday.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

enum class Weekday {
    MON, TUE, WED, THU, FRI
}

enum class Holiday {
    Eastern, Christmas, LaborDay, NewYear
}

class IterateEnumEntriesUnitTest {
    private val expectedWeekdayArray = arrayOf(MON, TUE, WED, THU, FRI)
    private val expectedWeekdayList = listOf(MON, TUE, WED, THU, FRI)

    private val expectedHolidayArray = arrayOf(Eastern, Christmas, LaborDay, NewYear)

    @Test
    fun `when using values() then get correct iterable`() {
        val result = Weekday.values()
        assertArrayEquals(expectedWeekdayArray, result)
    }

    @Test
    fun `when using entries() then get correct iterable`() {
        val result = Weekday.entries
        assertEquals(expectedWeekdayList, result)
    }

    @Test
    fun `when using enumValues() then get correct iterable`() {
        val weekdayResult = enumValues<Weekday>()
        assertArrayEquals(expectedWeekdayArray, weekdayResult)

        val holidayResult = enumValues<Holiday>()
        assertArrayEquals(expectedHolidayArray, holidayResult)
    }

    inline fun <reified E : Enum<E>> findEnumByName(name: String): E? {
        // E.values() or E.entries doesn't compile
        return enumValues<E>().firstOrNull { it.name.equals(name.replace(" ", ""), ignoreCase = true) }
    }


    @Test
    fun `when using the generic itemNames then get correct iterable`() {
        val tuesday = findEnumByName<Weekday>("t U e")
        assertEquals(TUE, tuesday)

        val newYear = findEnumByName<Holiday>("N E W Y E A R")
        assertEquals(NewYear, newYear)

        val unknown = findEnumByName<Holiday>("Thanksgiving")
        assertNull(unknown)
    }
}