package com.baeldung.backticks

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BackticksUnitTest {
    @Test
    fun `use backticks to escape reserved keywords`() {
        val `class` = "Hello"
        assertEquals("Hello", `class`)
    }

    @Test
    fun `use backticks to allow special characters in identifier`() {
        val `special Name$and@` = "Hello"
        assertEquals("Hello", `special Name$and@`)
    }

    @Test
    fun `use backticks to refer to Java method that is a keyword in Kotlin`() {
        val backTickUsageJavaClass = BackTickUsage()
        assertTrue(backTickUsageJavaClass.`is`())
    }
}