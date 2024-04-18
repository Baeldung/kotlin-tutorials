package com.baeldung.variableshadowing

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VariableShadowingUnitTest{

    class Car {
        val speed: Int = 100

        fun upSpeed() : Int {
            val speed = speed * 2 // Shadowing the constructor parameter 'speed'
            return speed
        }
    }

    @Test
    fun `class member variable shadowing`(){
        assertEquals(200, Car().upSpeed())
    }

    @Test
    fun `local variable shadowing`() {
        val name = "Budi"
        fun getNameShadow(): String {
            val name = "Ani"
            return name
        }

        fun getName(): String {
            return name
        }

        assertEquals("Budi", getName())
        assertEquals("Ani", getNameShadow())
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