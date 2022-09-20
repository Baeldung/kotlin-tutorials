package com.baeldung.escapeStrTemplate

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class EscapeStringTemplateUnitTest {
    //Content: "In shell script, ${ parameter } is called parameter substitution."
    val expectedStr = this.javaClass.getResource("/test-data/literalStr.txt")?.readText() ?: IllegalStateException()

    @Test
    fun `given a str, when escape dollar char, should get expected output`() {
        val myStr = "In shell script, \${ parameter } is called parameter substitution."
        assertEquals(expectedStr, myStr)
    }


    @Test
    fun `given a str, when using identifier, should get expected output`() {
        val mySingleStr = "In shell script, ${'$'}{ parameter } is called parameter substitution."
        assertEquals(expectedStr, mySingleStr)

        val myRawStr = """In shell script, ${'$'}{ parameter } is called parameter substitution."""
        assertEquals(expectedStr, myRawStr)
    }

    @Test
    fun `given a raw str, when using escaped dollar in a var, should get expected output`() {
        val myVar = "\${ parameter }"
        val myRawStr = """In shell script, $myVar is called parameter substitution."""
        assertEquals(expectedStr, myRawStr)
    }

}