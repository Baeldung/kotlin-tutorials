package com.baeldung.privateFunction

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class PrivateFunctionUnitTest {

    @Test
    fun `Makes call to private method Using Reflection`() {
        val obj = MyClass()
        val privateMethod = MyClass::class.java.getDeclaredMethod("privateMethod")
        privateMethod.isAccessible = true
        val outputStreamCaptor = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStreamCaptor))
        privateMethod.invoke(obj)
        val output = outputStreamCaptor.toString().trim()

        assertEquals("This is a private method", output)
    }

    @Test
    fun `Makes call to private method Using Inner Class`() {
        val obj = MyClassWithInnerClass()
        val innerObj = obj.MyInnerClass()
        val outputStreamCaptor = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStreamCaptor))
        innerObj.callPrivateMethod()
        val output = outputStreamCaptor.toString().trim()

        assertEquals("This is a private method", output)
    }

    @Test
    fun `Makes call to private method Using Inheritance`() {
        val obj = MySubClass()
        val outputStreamCaptor = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStreamCaptor))
        obj.callPrivateMethod()
        val output = outputStreamCaptor.toString().trim()

        assertEquals("This is a private method", output)
    }
}

class MyClass {
    private fun privateMethod() {
        println("This is a private method")
    }
}

class MyClassWithInnerClass {
    private fun privateMethod() {
        println("This is a private method")
    }

    inner class MyInnerClass {
        fun callPrivateMethod() {
            privateMethod()
        }
    }
}

open class MySuperClass {
    private fun privateMethod() {
        println("This is a private method")
    }

    fun callPrivateMethod() {
        privateMethod()
    }
}

class MySubClass : MySuperClass()
