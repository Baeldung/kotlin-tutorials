package com.baeldung.kclass

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class KClassUnitTest {

    @Test
    fun `class reference syntax should retrieve the KClass as expected`() {
        val aString = "42"
        val stringType = String::class

        assertEquals(stringType, aString::class)
    }

    @Test
    fun `javaClass should retrieve the KClass as expected`() {
        val aString = "42"
        val type = aString.javaClass.kotlin
        assertEquals("String", type.simpleName)
    }
}
