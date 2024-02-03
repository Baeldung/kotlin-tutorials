package com.baeldung.equalsandhashcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PersonWithUniqueNameAndAgeUnitTest {
    @Test
    fun `equal when name and age fields are equal`() {
        val person1 = PersonWithUniqueNameAndAge("John", 18)
        person1.address = "Address"
        val person2 = PersonWithUniqueNameAndAge("John", 18)
        person2.address = "Another Address"

        assertEquals(person1, person2)
    }

    @Test
    fun `not equal when name and age fields are not equal`() {
        val person1 = PersonWithUniqueNameAndAge("John", 18)
        person1.address = "Address"
        val person2 = PersonWithUniqueNameAndAge("John", 19)
        person2.address = "Address"

        assertNotEquals(person1, person2)
    }
}