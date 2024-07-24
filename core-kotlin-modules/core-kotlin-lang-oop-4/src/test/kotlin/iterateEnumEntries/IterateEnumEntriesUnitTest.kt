package iterateEnumEntries

import iterateEnumEntries.DayInWeek.*
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

enum class DayInWeek {
    MON, TUE, WED, THU, FRI, SAT, SUN
}

class IterateEnumEntriesUnitTest {
    val expectedArray = arrayOf(MON, TUE, WED, THU, FRI, SAT, SUN)

    @Test
    fun `when using values() then get correct iterable`() {
        val result = DayInWeek.values()

        assertArrayEquals(expectedArray, result)

    }

}