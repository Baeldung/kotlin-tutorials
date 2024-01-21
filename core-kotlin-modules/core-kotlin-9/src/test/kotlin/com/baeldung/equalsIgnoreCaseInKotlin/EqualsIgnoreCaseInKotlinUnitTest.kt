package com.baeldung.equalsIgnoreCaseInKotlin

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.*

class EqualsIgnoreCaseInKotlinUnitTest {

    @Test
    fun `test string case insensitive comparison using equals and lowercase methods`() {
        assertTrue(equalsIgnoreCaseUsingLowerCaseMethod("Hello", "hello"))
        assertFalse(equalsIgnoreCaseUsingLowerCaseMethod("Hello", "world"))
    }

    @Test
    fun `test string case insensitive comparison using equals methods`() {
        assertTrue(equalsIgnoreCase("Hello", "hello"))
        assertFalse(equalsIgnoreCase("Hello", "world"))
    }

    @Test
    fun `test string case insensitive comparison using compareTo method`() {
        assertTrue(equalsIgnoreCaseUsingCompareToMethod("Hello", "hello"))
        assertFalse(equalsIgnoreCaseUsingCompareToMethod("Hello", "world"))

}

fun equalsIgnoreCaseUsingLowerCaseMethod(str1: String, str2: String): Boolean {
    return str1.lowercase().equals(str2.lowercase())
}

fun equalsIgnoreCaseUsingCompareToMethod(str1: String, str2: String): Boolean {
    return str1.compareTo(str2, true) == 0
}

fun equalsIgnoreCase(str1: String, str2: String): Boolean {
    return str1.equals(str2, true)
}