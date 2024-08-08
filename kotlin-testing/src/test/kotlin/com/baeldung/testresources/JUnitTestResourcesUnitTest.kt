package com.baeldung.testresources

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class JUnitTestResourcesUnitTest {

    companion object {
        @JvmStatic
        @BeforeAll
        fun setUpAll() {
            println("Initializing Resources Before All Tests")
        }

        @JvmStatic
        @AfterAll
        fun tearDownAll() {
            println("Cleaning Resources After All Tests")
        }
    }

    @BeforeEach
    fun setUp() {
        println("Initializing Resources Before Each Test")
    }

    @AfterEach
    fun tearDown() {
        println("Cleaning Resources After Each Test")
    }

    @Test
    fun testSomething() {
        println("Running Test")
    }
}