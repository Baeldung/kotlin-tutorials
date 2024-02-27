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
        val names = DaysOfWeek.values()

        assertFalse(names.any { it.name == "RED" })
        assertTrue(names.any { it.name == "WEDNESDAY" })
        assertFalse(names.any { it.name == "AUGUST" })
        assertTrue(names.any { it.name == "SATURDAY" })
    }

    @Test
    fun `checks if enum value contains a specific string correctly using map method`() {
        val names = DaysOfWeek.values().map { it.name}

        assertFalse(names.contains("RED"))
        assertTrue(names.contains("WEDNESDAY"))
        assertFalse(names.contains("AUGUST"))
        assertTrue(names.contains("SATURDAY"))
    }

    @Test
    fun `checks if enum value contains a specific string correctly using a hashset`() {
        assertFalse(containsValueUsingHashSet("RED"))
        assertTrue(containsValueUsingHashSet("WEDNESDAY"))
        assertFalse(containsValueUsingHashSet("AUGUST"))
        assertTrue(containsValueUsingHashSet("SATURDAY"))
    }

}
enum class DaysOfWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    companion object {
        // generate names once.
        val names by lazy { DaysOfWeek.values().map{ it.name } }
    }
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
        in DaysOfWeek.names -> true
        else -> false
    }
}

fun containsValueUsingHashSet(value: String): Boolean {
    val set = HashSet<String>()
    DaysOfWeek.values().forEach { set.add(it.name) }
    return set.contains(value)
}