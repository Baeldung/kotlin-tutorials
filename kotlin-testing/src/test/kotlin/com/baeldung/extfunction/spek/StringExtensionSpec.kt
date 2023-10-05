package com.baeldung.extfunction.spek

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert.assertEquals

fun String.reverse(): String {
    return this.reversed()
}

Class StringExtensionSpec : Spek({
    describe("String Extension Tests") {
        it("should reverse a string correctly") {
            val inputString = "Hello, World!"
            val result = inputString.reverse()
            assertEquals("!dlroW ,olleH", result)
        }
        it("should handle an empty string") {
            val inputString = ""
            val result = inputString.reverse()
            assertEquals("", result)
        }
        it("should handle a single character string") {
            val inputString = "a"
            val result = inputString.reverse()
            assertEquals("a", result)
        }
    }
})


