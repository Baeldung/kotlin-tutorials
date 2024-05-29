package com.baeldung.functionAsParameter

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

fun joinByOperation(theList: List<String>, operation: (List<String>) -> String): String {
    return operation(theList)
}

class MessageParser {
    fun joinWithoutPlaceholder(segments: List<String>): String {
        return segments.joinToString(separator = " ").replace(" [SPACE] ", " ")
    }

    companion object {
        fun simplyJoin(segments: List<String>): String {
            return segments.joinToString(separator = " ")
        }
    }
}

object ParserInObject {
    fun joinWithoutComma(segments: List<String>): String {
        return segments.joinToString(separator = " ") { it.replace(",", "") }
    }
}

fun decrypt(segments: List<String>): String {
    return segments.reversed().joinToString(separator = " ") { it.reversed() }
}

class FunctionAsParameterUnitTest {

    @Test
    fun `when passing lambda as parameters then get expected result`() {
        val input = listOf("a b c", "d e f", "x y z")
        val result1 = joinByOperation(input) { theList ->
            theList.joinToString(separator = " ") { str -> str.reversed() }.replace(" ", ", ")
        }
        assertEquals("c, b, a, f, e, d, z, y, x", result1)

        val result2 = joinByOperation(input) { theList ->
            theList.reversed().joinToString(separator = " ") { str -> str }.uppercase()
        }
        assertEquals("X Y Z D E F A B C", result2)

    }

    @Test
    fun `when passing instance function ref as parameters then get expected result`() {
        val messageParser = MessageParser()
        val input = listOf("a [SPACE] b [SPACE] c", "d [SPACE] e [SPACE] f", "x [SPACE] y [SPACE] z")
        val result = joinByOperation(input, messageParser::joinWithoutPlaceholder)
        assertEquals("a b c d e f x y z", result)
    }

    @Test
    fun `when passing companion object function ref as parameters then get expected result`() {
        val input = listOf("a b c", "d e f", "x y z")
        val result = joinByOperation(input, MessageParser::simplyJoin)
        assertEquals("a b c d e f x y z", result)
    }

    @Test
    fun `when passing object function ref as parameters then get expected result`() {
        val input = listOf("a, b, c", "d, e, f", "x, y, z")
        val result = joinByOperation(input, ParserInObject::joinWithoutComma)
        assertEquals("a b c d e f x y z", result)
    }

    @Test
    fun `when passing top-level function ref as parameters then get expected result`() {
        val input = listOf("z y x", "f e d", "c b a")
        val result = joinByOperation(input, ::decrypt)
        assertEquals("a b c d e f x y z", result)
    }

    @Test
    fun `when passing variable with function type as parameter then get expected result`() {
        val input = listOf("a, b, c", "d, e, f", "x, y, z")

        val funRef = ParserInObject::joinWithoutComma
        val resultFunRef = joinByOperation(input, funRef)
        assertEquals("a b c d e f x y z", resultFunRef)

        val funLambda = { theList: List<String> -> theList.reversed().joinToString(separator = ", ") { str -> str }.uppercase() }
        val resultFunLambda = joinByOperation(input, funLambda)
        assertEquals("X, Y, Z, D, E, F, A, B, C", resultFunLambda)
    }
}