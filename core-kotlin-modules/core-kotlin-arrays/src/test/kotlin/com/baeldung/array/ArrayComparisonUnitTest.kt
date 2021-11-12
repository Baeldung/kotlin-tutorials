package com.baeldung.array

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ArrayComparisonUnitTest {

    @Test
    fun `Given arrays of strings, Then check referential equality with === operator`() {
        val first = arrayOf("java", "python", "kotlin")
        val second = arrayOf("java", "python", "kotlin")
        val third = first

        Assertions.assertTrue(first === third)
        Assertions.assertFalse(first === second)
        Assertions.assertFalse(second === third)
    }

    @Test
    fun `Given arrays of strings, Then check structural equality with == operator`() {
        val first = arrayOf("java", "python", "kotlin")
        val second = arrayOf("java", "python", "kotlin")
        val third = first

        Assertions.assertTrue(first == third)
        Assertions.assertFalse(first == second)
        Assertions.assertFalse(second == third)
    }

    @Test
    fun `Given arrays of strings, Then check structural equality with contentEquals`() {
        val first = arrayOf("java", "python", "kotlin")
        val second = arrayOf("java", "python", "kotlin")
        val third = first

        Assertions.assertTrue(first contentEquals third)
        Assertions.assertTrue(first contentEquals second)
        Assertions.assertTrue(second contentEquals third)
    }

    @Test
    fun `Given nested arrays of strings, Then check structural equality with contentEquals`() {
        val r1 = arrayOf(arrayOf("R1", "R2"), arrayOf("R3", "R4"))
        val r2 = arrayOf(arrayOf("R1", "R2"), arrayOf("R3", "R4"))
        val r3 = r1

        Assertions.assertTrue(r1 contentEquals r3)
        Assertions.assertFalse(r1 contentEquals r2)
        Assertions.assertFalse(r2 contentEquals r3)
    }

    @Test
    fun `Given nested arrays of strings, Then check structural equality with contentDeepEquals`() {
        val r1 = arrayOf(arrayOf("R1", "R2"), arrayOf("R3", "R4"))
        val r2 = arrayOf(arrayOf("R1", "R2"), arrayOf("R3", "R4"))
        val r3 = r1

        Assertions.assertTrue(r1 contentDeepEquals r3)
        Assertions.assertTrue(r1 contentDeepEquals r2)
        Assertions.assertTrue(r2 contentDeepEquals r3)
    }

    @Test
    fun `Given arrays of user-defined objects, Then check structural equality with contentEquals`() {
        class Person (var name: String?, var age: Int?)
        val first = arrayOf(Person("John", 20), Person("Mary", 15))
        val second = arrayOf(Person("John", 20), Person("Mary", 15))

        Assertions.assertFalse(first contentEquals second)
    }

    @Test
    fun `Given arrays of data class objects, Then check structural equality with contentEquals`() {
        data class Person (var name: String?, var age: Int?)
        val first = arrayOf(Person("John", 20), Person("Mary", 15))
        val second = arrayOf(Person("John", 20), Person("Mary", 15))

        Assertions.assertTrue(first contentEquals second)
    }

    @Test
    fun `Given nested arrays of data class objects, Then check structural equality with contentEquals`() {
        data class Person (var name: String?, var age: Int?)
        val p1 = arrayOf(arrayOf(Person("John", 20), Person("Mary", 15)))
        val p2 = arrayOf(arrayOf(Person("John", 20), Person("Mary", 15)))
        Assertions.assertTrue(p1 contentDeepEquals p2)
    }
}