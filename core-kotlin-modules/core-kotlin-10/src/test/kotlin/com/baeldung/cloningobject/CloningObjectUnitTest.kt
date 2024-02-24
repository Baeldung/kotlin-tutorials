package com.baeldung.cloningobject


import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

data class Address(val street: String, val city: String)

data class Person(val name: String, val address: Address): Cloneable {
    public override fun clone(): Person {
        return Person(name, address)
    }

    constructor(person: Person) : this(person.name, person.address)
}

class CloningObjectUnitTest {

    private val address = Address("Jalan Kemasan", "Yogyakarta")
    private val person = Person("Hangga", address)

    private fun serializePerson(person: Person): String {
        return "${person.name},${person.address.street},${person.address.city}"
    }

    private fun deserializePerson(data: String): Person {
        val parts = data.split(",")
        val name = parts[0]
        val street = parts[1]
        val city = parts[2]
        return Person(name, Address(street, city))
    }

    @Test
    fun `when using secondary constructor then proves that shallow copy`(){
        val clonedPerson = Person(person)

        // original object and cloned object should have the same values
        assertEquals(person.name, clonedPerson.name)
        assertEquals(person.address, clonedPerson.address)

        // But they should not refer to the same object
        assertNotSame(person, clonedPerson)
        assertSame(person.address, clonedPerson.address)
    }

    @Test
    fun `when using copy then proves that shallow copy`() {
        val clonedPerson = person.copy()

        // original object and cloned object should have the same values
        assertEquals(person.name, clonedPerson.name)
        assertEquals(person.address, clonedPerson.address)

        // But they should not refer to the same object
        assertNotSame(person, clonedPerson)
        assertSame(person.address, clonedPerson.address)
    }

    @Test
    fun `with using clone then proves that shallow copy`(){
        val clonedPerson = person.clone()

        // original object and cloned object should have the same values
        assertEquals(person.name, clonedPerson.name)
        assertEquals(person.address, clonedPerson.address)

        // But they should not refer to the same object
        assertNotSame(person, clonedPerson)
        assertSame(person.address, clonedPerson.address)
    }

    @Test
    fun `when using serialize then proves that deep copy`() {
        val serializedPerson = serializePerson(person)
        val clonedPerson = deserializePerson(serializedPerson)

        // original object and cloned object should have the same values
        assertEquals(person.name, clonedPerson.name)
        assertEquals(person.address.street, clonedPerson.address.street)
        assertEquals(person.address.city, clonedPerson.address.city)

        // But they should not refer to the same objects
        assertNotSame(person, clonedPerson)
        assertNotSame(person.address, clonedPerson.address)
    }
}