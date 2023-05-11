package com.baeldung.comparable

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ComparingWithDelegationUnitTest {

    private open class Animal(val weight: Double)

    private class Pet(weight: Double): Animal(weight), Comparable<Pet> by WeightComparable(weight)

    private class WeightComparable(private val value: Double): Comparable<Animal> {
        override fun compareTo(other: Animal): Int = value.compareTo(other.weight)
    }

    @Test
    fun whenComparingPetWithinDelegatedComparable_thenCorrect() {
        Assertions.assertTrue(Pet(50.0) > Pet(20.0))
        Assertions.assertTrue(Pet(50.0) >= Pet(50.0))
        Assertions.assertFalse(Pet(50.0) > Pet(50.0))
        Assertions.assertTrue(Pet(20.0) < Pet(50.0))
    }
}