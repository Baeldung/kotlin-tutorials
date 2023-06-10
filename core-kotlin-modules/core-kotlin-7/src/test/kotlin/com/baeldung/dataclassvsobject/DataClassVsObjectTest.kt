package com.baeldung.dataclassvsobject


import dataclassvsobject.Employee
import dataclassvsobject.Person
import dataclassvsobject.PersonManager
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DataClassVsObjectTest {
    @Test
    fun checkDataClassFunction() {
        val person1 = Person("Ada", "31")



        val person2 = Person("Ada", "31")
        assertEquals(true, person1.equals(person2))
        assertEquals(person1.hashCode(), person2.hashCode())
        assertEquals(person1.toString(), person2.toString())

        val person3 = person1.copy()
        assertEquals(person1.toString(), person3.toString())

        val (name, age) = person1
        assertEquals("Ada", name)
        assertEquals("31", age)
    }

    @Test
    fun checkDeclarationObject() {
        val person1 = Person("Ada", "31")
        val person2 = Person("Chris", "30")
        PersonManager.addPerson(person1)
        PersonManager.addPerson(person2)
        assertEquals(2, PersonManager.getAllPersons().size)
    }

    @Test
    fun checkExpressionObject() {
        val boss = object : Employee("Jack", "IT") {
            override fun greeting() = "I'm $name. I work in $department department and I'm the boss"
        }

        assertEquals("I'm Jack. I work in IT department and I'm the boss", boss.greeting())
    }
}

