package com.baeldung.atrium

import ch.tutteli.atrium.api.infix.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class InfixAssertionUnitTest {
    @Test
    fun equalsSuccess() {
        val value = "Hello"

        expect(value) toEqual "Hello"
    }

    @Test
    @Disabled
    fun equalsFailure() {
        val value = "Hello"

        expect(value) toEqual "Hello, World!"
    }
}