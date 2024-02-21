package com.baeldung.comparable

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class ComparableContravarianceUnitTest {

    private open class Animal

    private class Pet: Animal()

    private val animalComparable = object: Comparable<Animal> {
        override fun compareTo(other: Animal): Int = 0
    }

    private val petComparable = object: Comparable<Pet> {
        override fun compareTo(other: Pet): Int = 0
    }

    @Test
    fun whenAssigningFromBaseClass_thenCorrect() {
        val a: Comparable<Animal> = animalComparable
        var p: Comparable<Pet> = petComparable
        println("Result for petComparable: $p")
        assertDoesNotThrow { p = a }
    }

}