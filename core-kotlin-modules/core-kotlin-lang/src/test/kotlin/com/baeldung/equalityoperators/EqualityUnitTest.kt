package com.baeldung.equalityoperators

import org.junit.jupiter.api.Test
import kotlin.test.*

class EqualityUnitTest {

    // Checks referential equality
    @Test
    fun givenTwoIntegers_whenCheckReference_thenEqualReference() {
        val a = Integer.valueOf(10)
        val b = Integer.valueOf(10)

        assertSame(a, b)
    }

    // Checks structural equality
    @Test
    fun givenTwoIntegers_whenCheckValue_thenStructurallyEqual() {
        val a = Integer.valueOf(10)
        val b = Integer.valueOf(10)

        assertEquals(a, b)
    }

    @Test
    fun givenUser_whenCheckReference_thenEqualReference() {
        val user = User("John", 30, listOf("Hiking, Chess"))
        val user2 = User("Sarah", 28, listOf("Shopping, Gymnastics"))

        assertNotSame(user, user2)
    }

    @Test
    fun givenUser_whenCheckValue_thenStructurallyEqual() {
        val user = User("John", 30, listOf("Hiking, Chess"))
        val user2 = User("John", 30, listOf("Hiking, Chess"))

        assertTrue(user == user2)
    }

    @Test
    fun givenArray_whenCheckReference_thenEqualReference() {
        val hobbies = arrayOf("Riding motorcycle, Video games")
        val hobbies2 = arrayOf("Riding motorcycle, Video games")

        assertFalse(hobbies === hobbies2)
    }

    @Test
    fun givenArray_whenCheckContent_thenStructurallyEqual() {
        val hobbies = arrayOf("Hiking, Chess")
        val hobbies2 = arrayOf("Hiking, Chess")

        assertTrue(hobbies contentEquals hobbies2)
    }
}