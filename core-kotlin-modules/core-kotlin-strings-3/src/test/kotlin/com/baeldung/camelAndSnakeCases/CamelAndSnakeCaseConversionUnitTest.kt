package com.baeldung.camelAndSnakeCases

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


// String extensions
fun String.camelToSnakeCase(): String {
    val pattern = "(?<=.)[A-Z]".toRegex()
    return this.replace(pattern, "_$0").lowercase()
}

fun String.camelToSnakeCaseNoRegex(): String {
    return this.fold(StringBuilder()) { acc, c ->
        acc.let {
            val lowerC = c.lowercase()
            acc.append(if (acc.isNotEmpty() && c.isUpperCase()) "_$lowerC" else lowerC)
        }
    }.toString()
}

fun String.snakeToCamelCase(): String {
    val pattern = "_[a-z]".toRegex()
    return replace(pattern) { it.value.last().uppercase() }
}

fun String.snakeToCamelCase2(): String {
    val pattern = "_([a-z])".toRegex()
    return replace(pattern) { it.groupValues[1].uppercase() }
}

class CamelAndSnakeCaseConversionUnitTest {
    val camelCaseInput1 = "myNiceCamelCaseString"
    val camelCaseInput2 = "MyNiceCamelCaseString"
    val expectedInSnakeCase = "my_nice_camel_case_string"

    val snakeCaseInput = "one_good_snake_case_string"
    val expectedInCamelCase = "oneGoodSnakeCaseString"

    @Test
    fun `given camelCase inputs, when convert to snake case, should get the expected result`() {
        assertEquals(expectedInSnakeCase, camelCaseInput1.camelToSnakeCase())
        assertEquals(expectedInSnakeCase, camelCaseInput2.camelToSnakeCase())
    }

    @Test
    fun `given camelCase inputs, when convert to snake case without regex, should get the expected result`() {
        assertEquals(expectedInSnakeCase, camelCaseInput1.camelToSnakeCaseNoRegex())
        assertEquals(expectedInSnakeCase, camelCaseInput2.camelToSnakeCaseNoRegex())
    }

    @Test
    fun `given snake case input, when convert to camel case using both regex approach, should get the expected result`() {
        assertEquals(expectedInCamelCase, snakeCaseInput.snakeToCamelCase())
        assertEquals(expectedInCamelCase, snakeCaseInput.snakeToCamelCase2())
    }
}