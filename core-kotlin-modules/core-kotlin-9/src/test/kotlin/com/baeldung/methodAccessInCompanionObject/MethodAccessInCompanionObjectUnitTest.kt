package com.baeldung.methodAccessInCompanionObject

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MethodAccessInCompanionObjectUnitTest {
    @Test
    fun `calls outer method using reference to outer class`() {
        val myClass = OuterClass()
        OuterClass.outerClass = myClass
        val result = OuterClass.companionMethod()

        assertEquals("This is a method outside the companion object", result)
    }

    @Test
    fun `calls outer method using reflection`() {
        val result = ReflectionClass.companionMethod()

        assertEquals("This is a method outside the companion object", result)
    }

    @Test
    fun `calls outer method using interface`() {
        val myClass = OuterClassWIthInterface()
        val result = OuterClassWIthInterface.companionMethod(myClass)

        assertEquals("This is a method outside the companion object", result)
    }

    @Test
    fun `calls outer method using object declaration`() {
        val result = ClassWithObject.companionMethod()

        assertEquals("This is a method outside the companion object", result)
    }

    @Test
    fun `calls outer method using function reference`() {
        val myClass = ClassWithFunctionReference()
        val result = ClassWithFunctionReference.companionMethod(myClass::outerClassMethod)

        assertEquals("This is a method outside the companion object", result)
    }
}

class OuterClass {
    companion object {
        var outerClass: OuterClass? = null
        fun companionMethod(): String? {
            return outerClass?.outerClassMethod()
        }
    }

    fun outerClassMethod(): String {
        return "This is a method outside the companion object"
    }
}

class ReflectionClass {
    companion object {
        fun companionMethod(): String {
            val outerClass = ReflectionClass()
            val outerClassMethod = ReflectionClass::class.java.getDeclaredMethod("outerClassMethod")
            return outerClassMethod.invoke(outerClass) as String
        }
    }

    fun outerClassMethod(): String {
        return "This is a method outside the companion object"
    }
}
interface OuterClassInterface {
    fun outerClassMethod(): String
}

class OuterClassWIthInterface : OuterClassInterface {
    companion object {
        fun companionMethod(outerClassInterface: OuterClassInterface): String {
            return outerClassInterface.outerClassMethod()
        }
    }

    override fun outerClassMethod(): String {
        return "This is a method outside the companion object"
    }
}
class ClassWithObject {
    companion object {
        fun companionMethod(): String {
            return OuterClass.outerClassMethod()
        }
    }

    object OuterClass {
        fun outerClassMethod(): String {
            return "This is a method outside the companion object"
        }
    }
}

class ClassWithFunctionReference {
    companion object {
        fun companionMethod(outerClassMethod: () -> String): String {
            return outerClassMethod()
        }
    }

    fun outerClassMethod(): String {
        return "This is a method outside the companion object"
    }
}