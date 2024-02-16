package com.baeldung.equalsandhashcode

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class PersonUnitTest {

    @Test
    fun `equal when fields are equal`() {
        val person1 = Person("John", 18, "Address")
        val person2 = Person("John", 18, "Address")
        assertEquals(person1, person2)
    }

    @Test
    fun `not equal when fields are different`() {
        val person1 = Person("John", 18, "Address")
        val person2 = Person("John", 18, "Another Address")
        assertNotEquals(person1, person2)
    }
}