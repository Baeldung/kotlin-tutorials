package com.baeldung.pair.itandthiskeyword

import org.junit.Assert.assertEquals
import org.junit.Test

class ConstructorChainingTest {

    data class PersonData(val name: String, val age: Int) {
        constructor(name: String) : this(name, 0)
    }
    @Test
    fun testSecondaryConstructor() {
        val person = PersonData("Alice")
        assertEquals("Alice", person.name)
        assertEquals(0, person.age)
    }

    @Test
    fun testPersonWithAge() {
        val person = PersonData("Alice", 30)
        assertEquals("Alice", person.name)
        assertEquals(30, person.age)
    }

}