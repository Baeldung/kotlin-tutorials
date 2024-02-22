package com.baeldung.privateConstructor

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Student private constructor(val name: String, val age: Int) {

    companion object {
        fun createInstance(pair: Pair<String, Int>): Student {
            return Student(pair.first.uppercase(), pair.second)
        }
    }
}

data class StudentData private constructor(val name: String, val age: Int) {

    companion object {
        fun createInstance(pair: Pair<String, Int>): StudentData {
            return StudentData(pair.first.uppercase(), pair.second)
        }
    }
}

class PrivateConstructorUnitTest {
    @Test
    fun `when using private constructor then instance can only be created via companion function`() {
        // val kai = Student("Kai" to 18)
        // Kotlin: Cannot access '<init>': it is private in 'Student'

        val kai = Student.createInstance("Kai" to 18)

        assertEquals("KAI", kai.name)
        assertEquals(18, kai.age)
    }

    @Test
    fun `when using private constructor in data class then copy() exposes the private constructor`() {
        // val kaiData = StudentData("Kai" to 18)
        // Kotlin: Cannot access '<init>': it is private in 'StudentData'

        val kaiData = StudentData.createInstance("Kai" to 18)
        assertEquals("KAI", kaiData.name)
        assertEquals(18, kaiData.age)

        val liam = kaiData.copy(name = "Liam", age = 20)
        assertEquals("Liam", liam.name)
        assertEquals(20, liam.age)
    }
}