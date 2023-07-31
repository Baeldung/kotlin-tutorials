package com.baeldung.checkAllElementsInList

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class CheckAllElementsInList {

    @Test
    fun `when all numbers greater than 0 then it returns true`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val allGreaterThanZero = numbers.all { it > 0 }
        assertThat(allGreaterThanZero).isTrue
    }

    @Test
    fun `when all numbers not greater than 10 then it returns true`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val allLowerThanTen = numbers.none { it > 10 }
        assertThat(allLowerThanTen).isTrue
    }

    @Test
    fun `when all numbers greater than 5 then it returns true`() {
        val numbers = listOf(7, 8, 9, 10)
        val numbersGreaterThanFive = numbers.filter { it > 5 }
        assertThat(numbersGreaterThanFive).hasSize(numbers.size)
    }
}