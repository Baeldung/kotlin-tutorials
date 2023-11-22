package com.baeldung.pair.itandthiskeyword

import org.junit.Assert.assertEquals
import org.junit.Test

class ConstructorChainingUnitTest {

    data class PersonData(val name: String, val age: Int) {
        constructor(name: String) : this(name, 0)
    }
    @Test
    fun testSecondaryConstructor() {
        val person = PersonData("Alice")
        assertEquals("Alice", person.name)
        assertEquals(0, person.age)
    }
}