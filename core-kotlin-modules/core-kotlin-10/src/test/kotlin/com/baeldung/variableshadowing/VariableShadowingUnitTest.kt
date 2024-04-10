package com.baeldung.variableshadowing

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VariableShadowingUnitTest{

    @Test
    fun `local variable shadowing`() {
        val price = 100
        fun applyDiscount(price: Int): Int { // Inner function with parameter named 'price'
            val discount = 20
            return price - discount // 'price' here refers to the parameter, not the outer variable
        }
        val totalPrice = applyDiscount(200)
        assertEquals(180, totalPrice)
    }

    @Test
    fun `loop variable shadowing`() {
        val numbers = listOf(1, 2, 3, 4, 5)
        for (number in numbers) {
            println(number * 2)

            val doubledNumber = number * 2

            val number = number * 2 // Shadowing the loop variable 'number'

            assertEquals(doubledNumber, number)
        }
    }


    @Test
    fun `parameter shadowing`() {
        fun calculateTotalPrice(discount: Int) {
            val discount = 10 // Shadowing the parameter 'discount'
            val price = 100
            val totalPrice = price - discount
            assertEquals(90, totalPrice)
        }

        calculateTotalPrice(300)
    }

    @Test
    fun `nested function variable shadowing`(){
        fun calculateTotalPrice() {
            val price = 100
            fun applyDiscount(price: Int): Int { // Inner function with parameter named 'price'
                val discount = 20
                return price - discount // 'price' here refers to the parameter, not the outer variable
            }
            val totalPrice = applyDiscount(price)
            println("Total price after discount: $$totalPrice")
        }

        calculateTotalPrice()
    }
}