package com.baeldung.reflection

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.nio.charset.Charset
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.starProjectedType

class KMethodUnitTest {

    @Test
    fun testCallMethod() {
        val str = "Hello"
        val lengthMethod = str::length

        assertEquals(5, lengthMethod())
    }

    @Test
    fun testReturnType() {
        val str = "Hello"
        val method = str::byteInputStream

        assertEquals(ByteArrayInputStream::class.starProjectedType, method.returnType)
        assertFalse(method.returnType.isMarkedNullable)
    }

    @Test
    fun testParams() {
        val str = "Hello"
        val method = str::byteInputStream

        method.isSuspend
        assertEquals(1, method.parameters.size)
        assertTrue(method.parameters[0].isOptional)
        assertFalse(method.parameters[0].isVararg)
        assertEquals(Charset::class.starProjectedType, method.parameters[0].type)
    }

    @Test
    fun testMethodDetails() {
        val codePoints = String::codePoints
        assertEquals("codePoints", codePoints.name)
        assertFalse(codePoints.isSuspend)
        assertFalse(codePoints.isExternal)
        assertFalse(codePoints.isInline)
        assertFalse(codePoints.isOperator)

        val byteInputStream = String::byteInputStream
        assertEquals("byteInputStream", byteInputStream.name)
        assertFalse(byteInputStream.isSuspend)
        assertFalse(byteInputStream.isExternal)
        assertTrue(byteInputStream.isInline)
        assertFalse(byteInputStream.isOperator)
    }

    val readOnlyProperty: Int = 42
    lateinit var mutableProperty: String

    @Test
    fun testPropertyDetails() {
        val roProperty = this::readOnlyProperty
        assertEquals("readOnlyProperty", roProperty.name)
        assertFalse(roProperty.isLateinit)
        assertFalse(roProperty.isConst)
        assertFalse(roProperty is KMutableProperty<*>)

        val mProperty = this::mutableProperty
        assertEquals("mutableProperty", mProperty.name)
        assertTrue(mProperty.isLateinit)
        assertFalse(mProperty.isConst)
        assertTrue(mProperty is KMutableProperty<*>)
    }

    @Test
    fun testProperty() {
        val prop = this::mutableProperty

        assertEquals(String::class.starProjectedType, prop.getter.returnType)

        prop.set("Hello")
        assertEquals("Hello", prop.get())

        prop.setter("World")
        assertEquals("World", prop.getter())
    }
}
