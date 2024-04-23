@file:Suppress("UNUSED_VARIABLE", "UNUSED_ANONYMOUS_PARAMETER")

package com.baeldung.variableshadowing

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VariableShadowingUnitTest{

    @Test
    fun `Top-level variable shadowing`(){
        val number = 10 // Top-level variable

        fun upNumber() : Int { // top-level function
            val number = 20 // shadowing top-level variable
            return number
        }

        assertEquals(20, upNumber())
        assertEquals(10, number)
    }

    @Test
    fun `shadowing class member`(){
        class Car {
            val speed: Int = 100

            fun upSpeed() : Int {
                val speed = speed * 2 // Shadowing class member named 'speed'
                return speed
            }
        }

        assertEquals(100, Car().speed)
        assertEquals(200, Car().upSpeed())
    }

    @Test
    fun `local variable shadowing`(){
        fun calculateTotalPrice(discount: Int) {
            val discount = discount + 10 // Shadowing the parameter 'discount'
            assertEquals(30, discount)

            val price = 100 // local variable
            val discountRate = 0.1

            fun applyDiscount(price: Int): Double { // Nested function with parameter named 'price'
                val discountRate = 0.2  // shadowing the outer variable discountRate
                return price * (1 - discountRate) // 'price' here refers to the parameter, not the outer variable
            }

            assertEquals(80.0, applyDiscount(price))
        }

        calculateTotalPrice(20)
    }


    @Test
    fun `shadowing in loop`(){
        val numbers = listOf(1, 2, 3, 4, 5)

        // shadowing in loop
        for (number in numbers) {
            val number = number * 2 // Shadowing the loop variable 'number'
        }
    }

    @Test
    fun `shadowing in extension`(){
        val numbers = listOf(1, 2, 3, 4, 5)

        assertEquals(15, numbers.sum())

        fun List<Int>.sum(): Int { // shadowing built-in function sum()
            var sum = 0
            this.forEach { sum += it * 2 }
            return sum
        }

        assertEquals(30, numbers.sum())
    }

    @Test
    fun `shadowing in lambda`(){
        val numbers = listOf(1, 2, 3, 4, 5)

        var sum = 0

        numbers.forEach { number ->
            val number = 0 // shadowing value
            sum += number
        }

        assertEquals(0, sum)

        val lambda = { number : Int ->
            val number = 1// Local variable in lambda
        }
    }


    @Test
    fun `solution to avoid shadowing`(){
        val topLevelNumber = 10 // Top-level variable

        fun upNumber() : Int {
            val localNumber = 20 // Local variable without shadowing
            return localNumber
        }

        assertEquals(20, upNumber())
        assertEquals(10, topLevelNumber)

        // in class member
        class Car {
            val speed: Int = 100

            fun newSpeed() : Int {
                val newSpeed = speed * 2 // Using a new variable name to avoid shadowing
                return newSpeed
            }

            fun upSpeed() : Int {
                return this.speed * 2 // Use the outer speed directly with this keyword
            }
        }

        assertEquals(100, Car().speed)
        assertEquals(200, Car().upSpeed())
        assertEquals(200, Car().newSpeed())

        fun calculateTotalPrice(discount: Int) {
            val updatedDiscount = discount + 10 // Using a new variable name to avoid shadowing
            assertEquals(30, updatedDiscount)

            val price = 100 // local variable
            val discountRate = 0.1

            fun applyDiscount(price: Int): Double {
                return price * (1 - discountRate) // Use the outer discountRate directly
            }

            assertEquals(90.0, applyDiscount(price))
        }

        calculateTotalPrice(20)

        val numbers = listOf(1, 2, 3, 4, 5)

        // in loop
        for (number in numbers) {
            val newNumber = number * 2 // Using a new variable name to avoid shadowing
        }

        // in extension
        assertEquals(15, numbers.sum())

        fun List<Int>.sumByTwo(): Int { // shadowing built-in function sum()
            var sum = 0
            this.forEach { sum += it * 2 }
            return sum
        }

        assertEquals(30, numbers.sumByTwo())

        val doubledSum = numbers.sumOf { it * 2 } // Modify lambda in sum
        assertEquals(30, doubledSum)

        // in lambda
        var sum = 0

        numbers.forEach { number ->
            val newNumber = 0 // Using a new variable name to avoid shadowing
            sum += newNumber
        }

        assertEquals(0, sum)

        numbers.forEach { number ->
            sum += number // Directly access the current element in the loop
        }

        assertEquals(15, sum) // Assuming we want the sum of the original numbers

    }

}