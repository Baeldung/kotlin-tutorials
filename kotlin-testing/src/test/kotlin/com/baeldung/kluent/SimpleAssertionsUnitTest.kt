package com.baeldung.kluent

import org.amshove.kluent.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class SimpleAssertionsUnitTest {
    @Test
    @Disabled // We expect this to fail
    fun `when comparing two strings they they should not be equal`() {
        val value = "Hello"

        value.shouldBeEqualTo("Expected")
        value shouldBeEqualTo  "Expected"
        value `should be equal to` "Expected"
    }

    @Test
    fun `when comparing two strings in a case inseneitive manner then they should be equal`() {
        val value = "Hello"

        value shouldBeEqualToIgnoringCase "Hello"
        value shouldBeEqualToIgnoringCase "HELLO"
        value shouldBeEqualToIgnoringCase "hello"
    }

    @Test
    @Disabled // We expect this to fail
    fun `when performing multiple related assertions then they should all run and report together`() {
        val set = setOf("a", "b", "c")

        assertSoftly {
            set shouldHaveSize 4
            set shouldContain "a"
            set shouldContain "b"
            set shouldContain "c"
            set shouldContain "d"
        }
    }
}