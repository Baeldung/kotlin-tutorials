package com.baeldung.pair.itandthiskeyword

import org.junit.Assert.assertEquals
import org.junit.Test

class UsingThisTest {

    data class Person(val name: String) {
        // An instance variable
        var age: Int = 0
        fun printDetails() {
            println("Name: $name")
            println("Age: $age")
        }
        fun haveBirthday() {
            this.age++
        }
    }
    @Test
    fun testHaveBirthday() {
        val person = Person("Bob")
        person.haveBirthday()
        assertEquals(1, person.age)
    }
}