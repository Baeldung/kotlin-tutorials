@file:Suppress("UNUSED_VARIABLE", "UNUSED_ANONYMOUS_PARAMETER")

package com.baeldung.variableshadowing

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VariableShadowingUnitTest{

    @Test
    fun `test variable shadowing`(){
        val number = 10 // Top-level variable

        fun getNumber() : Int { // top level function
            val number = 20 // shadowing top level variable
            return number
        }

        assertEquals(20, getNumber())
        assertEquals(10, number)

        // shadowing class member
        class Car {
            val speed: Int = 100

            fun upSpeed() : Int {
                val speed = speed * 2 // Shadowing class member -> speed
                return speed
            }
        }

        assertEquals(100, Car().speed)
        assertEquals(200, Car().upSpeed())


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

        val numbers = listOf(1, 2, 3, 4, 5)

        // shadowing in loop
        for (number in numbers) {
            val doubledNumber = number * 2
            val number = number * 2 // Shadowing the loop variable 'number'

            assertEquals(doubledNumber, number)
        }


        // shadowing in extension
        assertEquals(15, numbers.sum())

        fun List<Int>.sum(): Int { // shadowing built-in function sum()
            var sum = 0
            this.forEach { sum += it * 2 }
            return sum
        }

        assertEquals(30, numbers.sum())

        // shadowing in lambda
        var sum = 0

        numbers.forEach { number ->
            val number = 0 // shadowing value
            sum += number
        }

        assertEquals(0, sum)
    }

}