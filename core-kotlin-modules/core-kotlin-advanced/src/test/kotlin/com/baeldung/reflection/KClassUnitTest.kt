package com.baeldung.reflection

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.math.BigDecimal
import kotlin.reflect.full.*

class KClassUnitTest {
    private val LOG = LoggerFactory.getLogger(KClassUnitTest::class.java)

    @Test
    fun testKClassDetails() {
        val stringClass = String::class
        assertEquals("kotlin.String", stringClass.qualifiedName)
        assertFalse(stringClass.isData)
        assertFalse(stringClass.isCompanion)
        assertFalse(stringClass.isAbstract)
        assertTrue(stringClass.isFinal)
        assertFalse(stringClass.isSealed)

        val listClass = List::class
        assertEquals("kotlin.collections.List", listClass.qualifiedName)
        assertFalse(listClass.isData)
        assertFalse(listClass.isCompanion)
        assertTrue(listClass.isAbstract)
        assertFalse(listClass.isFinal)
        assertFalse(listClass.isSealed)
    }

    @Test
    fun testGetRelated() {
        LOG.info("Companion Object: {}", TestSubject::class.companionObject)
        LOG.info("Companion Object Instance: {}", TestSubject::class.companionObjectInstance)
        LOG.info("Object Instance: {}", TestObject::class.objectInstance)

        assertSame(TestObject, TestObject::class.objectInstance)
    }

    @Test
    fun testNewInstance() {
        val listClass = ArrayList::class

        val list = listClass.createInstance()
        assertTrue(list is ArrayList)
    }

    @Test
    @Disabled
    fun testMembers() {
        val bigDecimalClass = BigDecimal::class

        LOG.info("Constructors: {}", bigDecimalClass.constructors)
        LOG.info("Functions: {}", bigDecimalClass.functions)
        LOG.info("Properties: {}", bigDecimalClass.memberProperties)
        LOG.info("Extension Functions: {}", bigDecimalClass.memberExtensionFunctions)
    }
}

class TestSubject {
    companion object {
        val name = "TestSubject"
    }
}

object TestObject {
    val answer = 42
}
