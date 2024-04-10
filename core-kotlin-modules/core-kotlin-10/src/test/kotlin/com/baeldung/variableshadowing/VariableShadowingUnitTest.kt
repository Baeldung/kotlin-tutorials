package com.baeldung.variableshadowing

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VariableShadowingUnitTest{

    class Person(private val name: String) {
        fun printShadowName() {
            val name = "John Doe" // Shadowing the 'name' property with a local variable 'name'
            assertEquals("John Doe", name)
        }

        fun printOriginalName() {
            assertEquals("Alice", name) // Accessing the original 'name' property
        }
    }

    @Test
    fun `class member variable shadowing`(){
        val person = Person("Alice")
        person.printShadowName() // "John Doe"
        person.printOriginalName() // "Original Name: Alice"
    }

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
            assertEquals(80, totalPrice)
        }

        calculateTotalPrice()
    }


    @Test
    fun `top lebel function variale shadowing`(){
        val number = 10 // Top-level function variable

        // Shadowing top-level function variable
        fun printNumber() {
            val number = 20
            assertEquals(20, number)
        }

        printNumber()
        assertEquals(10, number)
    }
}