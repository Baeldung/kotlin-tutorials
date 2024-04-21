package com.baeldung.kotest.assertions.listwithelementproperty

import io.kotest.inspectors.*
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldHaveMinLength
import io.kotest.matchers.string.shouldNotBeBlank
import org.junit.jupiter.api.Test


data class Pet(val owner: String, val color: String)
data class Car(val color: String, val owner: String)
class ListContainsPropertyUnitTest{

    val cars = listOf(Car("Blue", "John"), Car("Red", "Peter"), Car("White", "James"))
    val pets = listOf(Pet("James", "Yellow"), Pet("James", "White"), Pet("John", "Red"))

    @Test
    fun `car list contains element with same owner and color as a pet`(){
        val pet = Pet("James", "White")
        cars.forAtLeastOne { car ->
            pet.owner shouldBe car.owner
            pet.color shouldBe car.color
        }

        cars.forAtLeast(1) { car ->
            pet.owner shouldBe car.owner
            pet.color shouldBe car.color
        }
    }

    @Test
    fun `pet list contains at most 2 elements with same owner as a car`(){
        val car = Car("Green", "James")
        pets.forAtMost(2){pet ->
            pet.owner shouldBe car.owner
        }

    }

    @Test
    fun `None of the car elements are green`(){
        cars.forNone {car ->
            car.owner shouldBe "Green"
        }
    }

    @Test
    fun `some cars are owned by james and peter`(){
        cars.forSome { car ->
            car.owner shouldBe "Peter"
        }
    }

    @Test
    fun `John owns exactly two pets`(){
        pets.forExactly(2) { pet ->
            pet.owner shouldBe "James"
        }
    }

    @Test
    fun `every pet has an owner and every car owner name should have at least 3 chars`(){
        pets.forAll { pet ->
            pet.owner.shouldNotBeBlank()
        }

        cars.forAll { car ->
            car.owner.shouldHaveMinLength(4)
        }
    }
}