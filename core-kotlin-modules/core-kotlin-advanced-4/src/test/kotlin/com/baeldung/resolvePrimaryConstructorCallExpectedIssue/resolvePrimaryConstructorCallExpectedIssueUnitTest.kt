package com.baeldung.resolvePrimaryConstructorCallExpectedIssue

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class resolvePrimaryConstructorCallExpectedIssueUnitTest {

    @Test
    fun `correctly resolve compile error by properly defining constructors`() {
        val student = Student("Martial", 15)

        assertEquals("Martial", student.name)
        assertEquals(15, student.age)
    }

    @Test
    fun `correctly resolve compile error by properly using the secondary constructor`() {
        val dog = Dog("Max")
        assertEquals("Max", dog.species)
    }

    @Test
    fun `correctly resolve compile error by properly using default parameters`() {
        val car1 = Car("Toyota", "Corolla")

        assertEquals("Toyota", car1.make)
        assertEquals("Corolla", car1.model)

    }
}

class Student(var name: String) {
    var age: Int = 14

    constructor(name: String, age: Int) : this(name) {
        this.age = age
    }

    fun printMsg() {
        println("Name is $name. Age is $age.")
    }
}

open class Animal(val species: String)

class Dog(species: String) : Animal(species)

class Car(val make: String, val model: String = "Unknown")
