package com.baeldung.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WeekdayUnitTest {

    @Test
    fun givenArrayValuesOfWeekend_whenMatchAgainstValuesMethod_thenFindExactMatch() {
        val expectedWeekdayValues = arrayOf(
            Weekday.SUNDAY,
            Weekday.MONDAY,
            Weekday.TUESDAY,
            Weekday.WEDNESDAY,
            Weekday.THURSDAY,
            Weekday.FRIDAY,
            Weekday.SATURDAY
        )
        val actualWeekdayValues = Weekday.values()
        Assertions.assertArrayEquals(expectedWeekdayValues, actualWeekdayValues)
    }

    @Test
    fun givenWeekday_whenInvokeCompareTo_thenReturnOrdinalValueDiff() {
        Assertions.assertEquals(1, Weekday.MONDAY.compareTo(Weekday.SUNDAY))
        Assertions.assertEquals(1, Weekday.TUESDAY.compareTo(Weekday.MONDAY))
        Assertions.assertEquals(1, Weekday.WEDNESDAY.compareTo(Weekday.TUESDAY))
        Assertions.assertEquals(1, Weekday.THURSDAY.compareTo(Weekday.WEDNESDAY))
        Assertions.assertEquals(1, Weekday.FRIDAY.compareTo(Weekday.THURSDAY))
        Assertions.assertEquals(1, Weekday.SATURDAY.compareTo(Weekday.FRIDAY))
    }

    @Test
    fun givenWeekday_whenGetOrdinalValue_thenReturnZeroBasedIndex() {
        Assertions.assertEquals(0, Weekday.SUNDAY.ordinal)
        Assertions.assertEquals(1, Weekday.MONDAY.ordinal)
        Assertions.assertEquals(2, Weekday.TUESDAY.ordinal)
        Assertions.assertEquals(3, Weekday.WEDNESDAY.ordinal)
        Assertions.assertEquals(4, Weekday.THURSDAY.ordinal)
        Assertions.assertEquals(5, Weekday.FRIDAY.ordinal)
        Assertions.assertEquals(6, Weekday.SATURDAY.ordinal)
    }

    @Test
    fun givenOrdinalValue_whenGetWeekdayFromValues_thenReturnWeekdayEnum() {
        Assertions.assertEquals(Weekday.SUNDAY, Weekday.values()[0])
        Assertions.assertEquals(Weekday.MONDAY, Weekday.values()[1])
        Assertions.assertEquals(Weekday.TUESDAY, Weekday.values()[2])
        Assertions.assertEquals(Weekday.WEDNESDAY, Weekday.values()[3])
        Assertions.assertEquals(Weekday.THURSDAY, Weekday.values()[4])
        Assertions.assertEquals(Weekday.FRIDAY, Weekday.values()[5])
        Assertions.assertEquals(Weekday.SATURDAY, Weekday.values()[6])
    }

    @Test
    fun givenEnumClass_whenCheckForFinalModififer_thenFindFinal() {
        val weekdayClazz = Weekday::class.java
        val isFinal = java.lang.reflect.Modifier.isFinal(weekdayClazz.modifiers)
        Assertions.assertTrue(isFinal)
    }
}
