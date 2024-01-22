package com.baeldung.privateFunction

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PrivateFunctionUnitTest {

    @Test
    fun `Makes call to private method Using Reflection`() {
        val obj = MyClass()
        val privateMethod = MyClass::class.java.getDeclaredMethod("privateMethod")
        privateMethod.isAccessible = true

        val output = privateMethod.invoke(obj)

        assertEquals("This is a private method", output)
    }

    @Test
    fun `Makes call to private method Using Inner Class`() {
        val obj = MyClassWithInnerClass()
        val innerObj = obj.MyInnerClass()
        val output = innerObj.callPrivateMethod()

        assertEquals("This is a private method", output)
    }

    @Test
    fun `Makes call to private method Using public method`() {
        val obj = MyPublicClass()

        assertEquals("This is a private method", obj.callPrivateMethod())
    }
}

class MyClass {
    private fun privateMethod(): String {
        return "This is a private method"
    }
}

class MyClassWithInnerClass {
    private fun privateMethod(): String {
        return "This is a private method"
    }

    inner class MyInnerClass {
        fun callPrivateMethod(): String {
           return privateMethod()
        }
    }
}

class MyPublicClass {
    private fun privateMethod(): String {
        return "This is a private method"
    }

    fun callPrivateMethod(): String {
        return privateMethod()
    }
}
