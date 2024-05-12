package com.baeldung.iterateDataClassProperties

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


data class Person(val name: String, val age: Int)
class IteratePropertiesOfDataClass {

    @Test
    fun `iterate fields using destructuring declaration`() {
        val person = Person("Robert", 28)
        val (name, age) = person
        assertEquals("Robert", name)
        assertEquals(28, age)
    }

    @Test
    fun `iterate fields using componentN methods`() {
        val person = Person("Robert", 28)
        val fields = listOf(person.component1(), person.component2())

        assertEquals("Robert", fields[0])
        assertEquals(28, fields[1])
    }

    @Test
    fun `iterate fields using copy method`() {
        val person = Person("Robert", 28)
        var output: String? = null
        iterateFields(person) { field -> output = field }

        assertEquals("Name: Robert, Age: 28", output)
    }
}

fun iterateFields(person: Person, action: (String) -> Unit) {
    val defaultPerson = person.copy()
    action("Name: ${defaultPerson.name}, Age: ${defaultPerson.age}")
}