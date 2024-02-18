package com.baeldung.equalsandhashcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PersonWithUniqueNameUnitTest {
    @Test
    fun `equal when name field is equal`() {
        val person1 = PersonWithUniqueName("John", 18, "Address")
        val person2 = PersonWithUniqueName("John", 19, "Another Address")
        assertEquals(person1, person2)
    }
}