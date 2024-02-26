package com.baeldung.cloningobject

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

data class Address(var street: String, var city: String)

data class Person(var name: String, var address: Address) : Cloneable {
    public override fun clone(): Person {
        return Person(name, address)
    }

    fun deepCopy(name: String = this.name, address: Address = this.address.copy()): Person {
        return Person(name, address)
    }

    constructor(person: Person) : this(person.name, person.address)
}

class CloningObjectUnitTest {

    private val address = Address("Jln. Kemasan", "Yogyakarta")
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
    fun `when using secondary constructor then proves that shallow copy`() {
        val clonedPerson = Person(person)
        clonedPerson.address.city = "Surabaya"
        clonedPerson.address.street = "Jln. Ahmad Yani"
        println("clonedPerson.address.city -> " + clonedPerson.address.city)
        println("person.address.city -> " + person.address.city)
        assertThat(clonedPerson).isNotSameAs(person)
        assertThat(clonedPerson.address.city).isEqualTo(person.address.city)
        assertThat(clonedPerson.address.street).isEqualTo(person.address.street)
    }

    @Test
    fun `when using copy then proves that shallow copy`() {
        val clonedPerson = person.copy()
        clonedPerson.address.city = "Palembang"
        clonedPerson.address.street = "Jln. Abi Hasan"
        println("clonedPerson.address.city -> " + clonedPerson.address.city)
        println("person.address.city -> " + person.address.city)
        assertThat(clonedPerson).isNotSameAs(person)
        assertThat(clonedPerson.address.city).isEqualTo(person.address.city)
        assertThat(clonedPerson.address.street).isEqualTo(person.address.street)
    }

    @Test
    fun `with using clone then proves that shallow copy`() {
        val clonedPerson = person.clone()
        clonedPerson.address.city = "Palembang"
        clonedPerson.address.street = "Jln. Abi Hasan"
        println("clonedPerson.address.street -> " + clonedPerson.address.street)
        println("person.address.street -> " + person.address.street)
        assertThat(clonedPerson).isNotSameAs(person)
        assertThat(clonedPerson.address.city).isEqualTo(person.address.city)
        assertThat(clonedPerson.address.street).isEqualTo(person.address.street)
    }

    @Test
    fun `with own function then proves that deep copy`() {
        val clonedPerson = person.deepCopy()
        clonedPerson.address.city = "Bandung"
        clonedPerson.address.street = "Jln. Siliwangi"
        println("clonedPerson.address.city -> " + clonedPerson.address.city)
        println("clonedPerson.address.street -> " + clonedPerson.address.street)
        println("person.address.city -> " + person.address.city)
        println("person.address.street -> " + person.address.street)
        assertThat(clonedPerson).isNotSameAs(person)
        assertThat(clonedPerson.address.city).isNotEqualTo(person.address.city)
        assertThat(clonedPerson.address.street).isNotEqualTo(person.address.street)
    }

    @Test
    fun `when using serialize then proves that shallow copy`() {
        val serializedPerson = serializePerson(person)
        val clonedPerson = deserializePerson(serializedPerson)
        clonedPerson.address.city = "Palembang"
        clonedPerson.address.street = "Jln. Abi Hasan"
        println("clonedPerson -> " + clonedPerson.address.city)
        println("person -> " + person.address.city)
        assertThat(clonedPerson).isNotSameAs(person)
        assertThat(clonedPerson.address.city).isNotEqualTo(person.address.city)
        assertThat(clonedPerson.address.city).isNotSameAs(person.address.city)
        assertThat(clonedPerson.address.street).isNotEqualTo(person.address.street)
    }

}