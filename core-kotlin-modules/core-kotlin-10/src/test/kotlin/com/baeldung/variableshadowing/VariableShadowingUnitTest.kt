package com.baeldung.variableshadowing

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VariableShadowingUnitTest{

    @Test
    fun `class member variable shadowing`(){
        class Car {
            val speed: Int = 100

            fun upSpeed() : Int {
                val speed = speed * 2 // Shadowing the constructor parameter 'speed'
                return speed
            }
        }

        assertEquals(100, Car().speed)
        assertEquals(200, Car().upSpeed())

    }

    @Test
    fun `local variable or nested function shadowing`() {

        fun calculateTotalPrice() {
            val price = 100
            val discountRate = 0.1  // Inner variable shadows the outer variable

            fun applyDiscount(price: Int): Double { // Nested function with parameter named 'price'
                val discountRate = 0.2  // Inner variable shadows the outer variable
                return price * (1 - discountRate) // 'price' here refers to the parameter, not the outer variable
            }

            assertEquals(80.0, applyDiscount(price))
        }

        calculateTotalPrice()
    }

    @Test
    fun `loop variable shadowing`() {
        val numbers = listOf(1, 2, 3, 4, 5)
        for (number in numbers) {
            val doubledNumber = number * 2
            val number = number * 2 // Shadowing the loop variable 'number'

            assertEquals(doubledNumber, number)
        }
    }


    @Test
    fun `parameter shadowing`() {
        fun calculateTotalPrice(discount: Int) {
            assertEquals(300, discount)

            val discount = 10 // Shadowing the parameter 'discount'
            assertEquals(10, discount)

            val price = 100
            val totalPrice = price - discount
            assertEquals(90, totalPrice)
        }

        calculateTotalPrice(300)
    }

    @Test
    fun `nested function variable shadowing`(){
        fun calculateDiscount(originalPrice: Double) {
            val discountRate = 0.1  // Outer variable

            fun applyDiscount(price: Double): Double {  // Nested function with parameter 'price'
                val discountRate = 0.2  // Inner variable shadows the outer variable
                return price * (1 - discountRate)
            }

            val discountedPrice = applyDiscount(originalPrice)
            println("Discounted price: $$discountedPrice")
        }

        calculateDiscount(200.0)
    }

    @Test
    fun `variable shadowing in lambda`(){
        var sum = 0
        var values = listOf(1, 2, 3, 4, 5)

        values.forEach { value ->
            val value = 0
            sum += value
        }

        assertEquals(0, sum)
    }

    @Test
    fun `shadowing in extension`(){
        val numbers = listOf(1, 2, 3, 4, 5)

        assertEquals(15, numbers.sum())

        fun List<Int>.sum(): Int { // Shadowing occur in here
            var sum = 0 // shadowing built-in function sum()
            this.forEach { sum += it * 2 }
            return sum
        }


        assertEquals(30, numbers.sum())
    }


    @Test
    fun `top level function variale shadowing`(){
        val number = 10 // Top-level function variable

        // Shadowing top-level function variable
        fun getNumber() : Int {
            val number = 20
            return number
        }

        assertEquals(20, getNumber())
        assertEquals(10, number)
    }
}