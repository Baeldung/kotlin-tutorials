package com.baeldung.capitalization

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CapitalizeEveryWordUnitTest {

    @Test
    fun when_memory_is_not_a_problem_then_a_simple_solution_suffices() {
        assertEquals(ALL_FIRST_CAPITAL, capitalizeSimpleWay(INPUT_STRING))
    }

    @Test
    fun when_memory_is_scarce_then_a_more_frugal_way_is_possible() {
        assertEquals(ALL_FIRST_CAPITAL, capitalizeWithSequence(INPUT_STRING))
    }

    @Test
    fun when_input_contains_multiple_spaces_then_split_by_regex() {
        assertEquals(ALL_FIRST_CAPITAL, capitalizeWithDoubleSpaces(INPUT_STRING_DOUBLE_SPACES))
    }

    @Test
    fun when_input_is_dirty_then_lower_memory_solution_is_still_possible() {
        assertEquals(ALL_FIRST_CAPITAL, capitalizeWithDoubleSpacesAndSequence(INPUT_STRING_DOUBLE_SPACES))
    }

    @Test
    fun when_complex_capitalization_is_needed_then_dictionaries_are_needed() {
        assertEquals(TITLE_CAPITAL, capitalizeWithPressRules(INPUT_STRING_DOUBLE_SPACES))
        assertEquals(COMPLEX_TITLE_CAPITALIZED, capitalizeWithPressRules(COMPLEX_TITLE_INPUT))
    }

    companion object {
        const val INPUT_STRING = "The quick brown fox jumps over the lazy dog"
        const val INPUT_STRING_DOUBLE_SPACES = "The quick  brown fox jumps  over the  lazy dog"
        const val ALL_FIRST_CAPITAL = "The Quick Brown Fox Jumps Over The Lazy Dog"
        const val TITLE_CAPITAL = "The Quick Brown Fox Jumps Over the Lazy Dog"
        const val COMPLEX_TITLE_INPUT = "Make your DOM look like an eye that can focus on anything"
        const val COMPLEX_TITLE_CAPITALIZED = "Make Your DOM Look Like an Eye That Can Focus on Anything"
    }
}