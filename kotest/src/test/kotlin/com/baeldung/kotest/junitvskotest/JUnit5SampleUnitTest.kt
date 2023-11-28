package com.baeldung.kotest.junitvskotest

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle

@TestInstance(Lifecycle.PER_CLASS)
class JUnit5SampleUnitTest {
    @BeforeAll
    fun setUpClass() {
        println("Setting up test class")
    }

    @BeforeEach
    fun setUp() {
        println("Setting up test")
    }

    @AfterEach
    fun tearDown() {
        println("Tearing down test")
    }

    @AfterAll
    fun tearDownClass() {
        println("Tearing down test class")
    }

    @Test
    fun testExample() {
        val result = 2 + 2

        assertEquals (4, result, "2 + 2 should be equal to 4")
        assertTrue(result > 0, "Result should be positive")
        assertFalse(result < 0, "Result should not be negative")
        assertNotNull(result, "Result should not be null")

        println("Executing testExample()")
    }
}
