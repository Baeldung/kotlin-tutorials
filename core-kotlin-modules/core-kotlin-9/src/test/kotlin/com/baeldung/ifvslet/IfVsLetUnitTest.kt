package com.baeldung.ifvslet

import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.test.assertEquals
import kotlin.test.assertNull

class IfVsLetUnitTest {

    @Test
    fun `Using let to uppercase null string`() {
        val name = fetchNullNameFromDatabase()
        val uppercased = name?.let { it.uppercase() }

            assertNull(uppercased)
    }

    @Test
    fun `Using let to uppercase string`() {
        val name = fetchNameFromDatabase()
        val uppercased = name?.let { it.uppercase() }

            assertEquals("NAME", uppercased)
    }

    @Test
    fun `Using if x not null to uppercase null string`() {
        val name = fetchNullNameFromDatabase()
        val uppercased = if(name != null) name.uppercase() else null

            assertNull(uppercased)
    }

    @Test
    fun `Using if x not null to uppercase string`() {
        val name = fetchNameFromDatabase()
        val uppercased = if(name != null) name.uppercase() else null

            assertEquals("NAME", uppercased)
    }

    fun fetchNameFromDatabase(): String? = "name"
    fun fetchNullNameFromDatabase(): String? = null
}