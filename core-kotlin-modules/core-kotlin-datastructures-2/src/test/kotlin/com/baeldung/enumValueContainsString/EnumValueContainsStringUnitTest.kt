package com.baeldung.enumValueContainsString

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class EnumValueContainsStringUnitTest {

    @Test
    fun `checks if enum value contains a specific string correctly using for loop`() {
        assertFalse(containsValueUsingForLoop("RED"))
        assertTrue(containsValueUsingForLoop("WEDNESDAY"))
        assertFalse(containsValueUsingForLoop("AUGUST"))
        assertTrue(containsValueUsingForLoop("SATURDAY"))
    }

    @Test
    fun `checks if enum value contains a specific string correctly using when expression`() {
        assertFalse(containsValueUsingWhenExpression("RED"))
        assertTrue(containsValueUsingWhenExpression("WEDNESDAY"))
        assertFalse(containsValueUsingWhenExpression("AUGUST"))
        assertTrue(containsValueUsingWhenExpression("SATURDAY"))
    }

    @Test
    fun `checks if enum value contains a specific string correctly using any method`() {
        assertFalse(DaysOfWeek.values().any { it.name == "RED" })
        assertTrue(DaysOfWeek.values().any { it.name == "WEDNESDAY" })
        assertFalse(DaysOfWeek.values().any { it.name == "AUGUST" })
        assertTrue(DaysOfWeek.values().any { it.name == "SATURDAY" })
    }

    @Test
    fun `checks if enum value contains a specific string correctly using map method`() {
        assertFalse(DaysOfWeek.values().map { it.name}.contains("RED"))
        assertTrue(DaysOfWeek.values().map { it.name}.contains("WEDNESDAY"))
        assertFalse(DaysOfWeek.values().map { it.name}.contains("AUGUST"))
        assertTrue(DaysOfWeek.values().map { it.name}.contains("SATURDAY"))
    }

    @Test
    fun `checks if enum value contains a specific string correctly using a map`() {
        assertFalse(containsValueUsingMap("RED"))
        assertTrue(containsValueUsingMap("WEDNESDAY"))
        assertFalse(containsValueUsingMap("AUGUST"))
        assertTrue(containsValueUsingMap("SATURDAY"))
    }

}
enum class DaysOfWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}


fun containsValueUsingForLoop(value: String): Boolean {
    for (day in DaysOfWeek.values()) {
        if (day.name == value) {
            return true
        }
    }
    return false
}

fun containsValueUsingWhenExpression(value: String): Boolean {
    return when (value) {
        DaysOfWeek.MONDAY.name, DaysOfWeek.TUESDAY.name, DaysOfWeek.WEDNESDAY.name,
        DaysOfWeek.THURSDAY.name, DaysOfWeek.FRIDAY.name, DaysOfWeek.SATURDAY.name, DaysOfWeek.SUNDAY.name-> true
        else -> false
    }
}
fun containsValueUsingMap(value: String): Boolean {
    val map = DaysOfWeek.values().associateBy(DaysOfWeek::name)
    return map.contains(value)
}