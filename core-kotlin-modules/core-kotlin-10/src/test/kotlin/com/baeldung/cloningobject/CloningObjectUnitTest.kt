package com.baeldung.cloningobject

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

data class Address(var street: String, var city: String) : Cloneable {
    public override fun clone(): Address = super.clone() as Address
}

data class Person(var name: String, var address: Address) : Cloneable {
    public override fun clone() = Person(name, this.address.clone())

    fun deepCopy(name: String = this.name, address: Address = this.address.copy()): Person {
        return Person(name, address)
    }

    constructor(person: Person) : this(person.name, person.address.copy())
}

class CloningObjectUnitTest {

    private val address = Address("Jln. Kemasan", "Yogyakarta")
    private val person = Person("Hangga Aji Sayekti", address)

    @Test
    fun `when using secondary constructor then proves that shallow copy`() {
        val clonedPerson = Person(person)
        assertThat(clonedPerson).isNotSameAs(person)

        person.address.city = "Surabaya"
        person.address.street = "Jln. Ahmad Yani"

        assertThat(clonedPerson.address.city)
            .isNotEqualTo("Surabaya")

        assertThat(clonedPerson.address.street)
            .isNotEqualTo("Jln. Ahmad Yani")
    }

    @Test
    fun `when using copy then proves that shallow copy or deep copy`() {
        val clonedPerson = person.copy()
        assertThat(clonedPerson).isNotSameAs(person)

        person.address.city = "Jakarta"
        person.address.street = "Jln. Abdul Muis"

        assertThat(clonedPerson.address.city)
            .isEqualTo("Jakarta")

        assertThat(clonedPerson.address.street)
            .isEqualTo("Jln. Abdul Muis")

        val deepClonedPerson = person.copy(address = address.copy())
        assertThat(deepClonedPerson).isNotSameAs(person)

        person.address.city = "Banda Aceh"
        person.address.street = "Jln. Cut Nyak Dhien"

        assertThat(deepClonedPerson.address.city)
            .isNotEqualTo("Banda Aceh")

        assertThat(deepClonedPerson.address.street)
            .isNotEqualTo("Jln. Cut Nyak Dhien")
    }

    @Test
    fun `when using clone then proves that shallow copy`() {
        val clonedPerson = person.clone()
        assertThat(clonedPerson).isNotSameAs(person)

        person.address.city = "Palembang"
        person.address.street = "Jln. Abi Hasan"

        assertThat(clonedPerson.address.city)
            .isNotEqualTo("Palembang")

        assertThat(clonedPerson.address.street)
            .isNotEqualTo("Jln. Abi Hasan")
    }

    @Test
    fun `when own function then proves that deep copy`() {
        val clonedPerson = person.deepCopy()
        assertThat(clonedPerson).isNotSameAs(person)

        person.address.city = "Bandung"
        person.address.street = "Jln. Siliwangi"

        assertThat(clonedPerson.address.city)
            .isNotEqualTo("Bandung")

        assertThat(clonedPerson.address.street)
            .isNotEqualTo("Jln. Siliwangi")
    }

}