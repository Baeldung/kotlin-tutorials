package com.baeldung.lists

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ListCopyUnitTest {

    private val cities = listOf("Berlin", "Munich", "Hamburg")

    @Test
    fun `should be able to shallow copy a list using toList extension functions`() {
        val copied = cities.toList()
        val mutableCopy = cities.toMutableList()

        assertThat(copied).containsAll(cities)
        assertThat(mutableCopy).containsAll(cities)

        assertThat(copied).isNotSameAs(cities)
        assertThat(copied[0]).isSameAs(cities[0])
    }

    @Test
    fun `should deep copy a data class with only primitive fields`() {
        data class Person(var name: String, var age: Int)
        val persons = listOf(Person("Bob", 20), Person("Alice", 21))
        val personsCopy = persons.map { it.copy() }

        assertThat(personsCopy).isNotSameAs(persons)
        assertThat(personsCopy[0]).isNotSameAs(persons[0])
        assertThat(persons[0].age).isEqualTo(personsCopy[0].age)

        personsCopy[0].age = 22

        assertThat(persons[0].age).isNotEqualTo(personsCopy[0].age)
    }

    @Test
    fun `should deep copy a data class using a custom clone method`() {
        data class Address(var streetName: String, var streetNumber: Int, var city: String)

        data class Person(val name: String, val age: Int, val address: Address) {
            fun clone(): Person {
                return Person(this.name, this.age, this.address.copy())
            }
        }

        val address1 = Address("Sesame Street", 1, "CartoonLand")
        val address2 = Address("Sesame Street", 2, "CartoonLand")
        val persons = listOf(Person("Bob", 20, address1), Person("Alice", 21, address2))

        val personsCopy = persons.map { it.clone() }

        assertThat(personsCopy).isNotSameAs(persons)
        assertThat(personsCopy[0]).isNotSameAs(persons[0])
        assertThat(personsCopy[0].address).isNotSameAs(persons[0].address)
        assertThat(personsCopy[0].address).isEqualTo(persons[0].address)

        personsCopy[0].address.streetNumber = 10

        assertThat(personsCopy[0].address).isNotEqualTo(persons[0].address)
    }
}
