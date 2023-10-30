package com.baeldung.multilinestringinterpolation

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class MultilineStringInterpolationUnitTest {


    @Test
    fun `test escaping dollar sign in double quoted string with newline chars should work`() {
        val dollarChar = "$"
        val findAdultsQuery = "db.people.find(\n{\n\"age\": { \$gte: 18 }\n }\n)"
        assertTrue(dollarChar in findAdultsQuery)
    }

    @Test
    fun `test interpolation with dollar sign in double quoted string with newline chars should work`() {
        val dollarChar = "$"
        val findAdultsQuery = "db.people.find(\n{\n\"age\": { ${'$'}gte: 18 }\n }\n)"
        assertTrue(dollarChar in findAdultsQuery)
    }

    @Test
    fun `test interpolation with dollar sign in raw string should work`() {
        val dollarChar = "$"
        val findAdultsQuery = """
        db.people.find(
          {
            "age": { ${'$'}gte: 18 }
          }
        )
        """
        assertTrue(dollarChar in findAdultsQuery)
    }

    /*
    @Test
    fun `test having dollar sign in raw string gives compilation error`() {

        val findAdultsQuery = """
        db.people.find(
          {
            "age": { $gte: 18 }
          }
        )
        """
    }
*/

/*
    @Test
    fun `test escaping dollar sign in raw string gives compilation error`() {

        val findAdultsQuery = """
        db.people.find(
          {
            "age": { \$gte: 18 }
          }
        )
        """
    }
*/


}
