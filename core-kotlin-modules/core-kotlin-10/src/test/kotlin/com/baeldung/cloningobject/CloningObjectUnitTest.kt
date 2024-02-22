package com.baeldung.cloningobject

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
class CloningObjectUnitTest {
    data class Person(val name: String, val address: Address)
    data class Address(val street: String, val city: String)

    @Test
    fun `when copy an object then compare`(){
        val address = Address("Jalan Kemasan", "Yogyakarta")
        val firstPerson = Person("Hangga", address)
        val secondPerson = firstPerson.copy()

        assertTrue(firstPerson == secondPerson)
        assertTrue(firstPerson.name == secondPerson.name)
        assertTrue(firstPerson.address === secondPerson.address)
        assertTrue(firstPerson.address.street == secondPerson.address.street)
        assertTrue(firstPerson.address.city == secondPerson.address.city)
        assertFalse(firstPerson === secondPerson)
    }
}