package com.baeldung.preconditions

class AverageCalculator {
    private val numbers: MutableList<Int> = mutableListOf()

    fun add(number: Int) {
        numbers.add(number)
    }

    fun clear() {
        numbers.clear()
    }

    fun average(): Double {
        check(numbers.size > 0) { "Must have numbers to be able to perform an average." }
        println("Computing averages of numbers: $numbers")
        return numbers.average()
    }
}