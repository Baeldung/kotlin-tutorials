@file:Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE", "UNUSED_ANONYMOUS_PARAMETER")

package com.baeldung.variableshadowing

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VariableShadowingUnitTest{

    @Test
    fun `class member & top level function variable shadowing`(){
        val number = 10 // Top-level variable

        fun getNumber() : Int { // top level function
            val number = 20
            return number
        }

        assertEquals(20, getNumber())
        assertEquals(10, number)

        class Car {
            val speed: Int = 100

            fun upSpeed() : Int {
                val speed = speed * 2 // Shadowing the constructor parameter 'speed'
                return speed
            }
        }

        assertEquals(100, Car().speed)
        assertEquals(200, Car().upSpeed())


        fun calculateTotalPrice(discount: Int) {
            val discount = 10 // Shadowing the parameter 'discount'
            assertEquals(10, discount)

            val price = 100 // local variable
            val discountRate = 0.1

            fun applyDiscount(price: Int): Double { // Nested function with parameter named 'price'
                val discountRate = 0.2  // Inner variable shadows the outer variable
                return price * (1 - discountRate) // 'price' here refers to the parameter, not the outer variable
            }

            assertEquals(80.0, applyDiscount(price))
        }

        calculateTotalPrice(20)

        // in loop
        val numbers = listOf(1, 2, 3, 4, 5)
        for (number in numbers) {
            val doubledNumber = number * 2
            val number = number * 2 // Shadowing the loop variable 'number'

            assertEquals(doubledNumber, number)
        }


        // extension
        assertEquals(15, numbers.sum())

        fun List<Int>.sum(): Int { // Shadowing occur in here
            var sum = 0 // shadowing built-in function sum()
            this.forEach { sum += it * 2 }
            return sum
        }

        assertEquals(30, numbers.sum())

        // in lambda
        var sum = 0
        val values = listOf(1, 2, 3, 4, 5)

        values.forEach { value ->
            val value = 0
            sum += value
        }

        assertEquals(0, sum)
    }

}