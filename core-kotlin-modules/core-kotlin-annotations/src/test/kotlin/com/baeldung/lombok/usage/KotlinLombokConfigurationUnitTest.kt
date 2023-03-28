package com.baeldung.lombok.usage

import javaclass.Person
import javaclass.Person1
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class KotlinLombokConfigurationUnitTest {

    @Test
    fun `lombok usage for getter and setter in kotlin`() {
        val person = Person1()
        person.name = "Nappy"
        person.age = 20
        person.gender = "Male"

        assertEquals("Nappy", person.name)
        assertEquals(20, person.age)
        assertEquals("Male", person.gender)
    }

    @Test
    fun `lombok usage for all arg constructor`() {
        val person = Person("Nappy", 20, "Male", 1)

        assertEquals("Nappy", person.name)
        assertEquals(20, person.age)
        assertEquals("Male", person.gender)
    }

    @Test
    fun `lombok usage for toString method`() {
        val person = Person("Nappy", 20, "Male", 1)

        assertEquals("Person(name=Nappy, age=20, gender=Male, id=1)", person.toString())
    }

    @Test
    fun `lombok usage for equals and hashcode methods`() {
        val p = Person1()
        p.name = "Nappy"
        p.age = 20
        p.gender = "Male"

        val p1 = Person1()
        p1.name = "Nappy"
        p1.age = 20
        p1.gender = "Male"

        assertEquals(p, p1)
        assertEquals(p.hashCode(), p1.hashCode())
    }

}