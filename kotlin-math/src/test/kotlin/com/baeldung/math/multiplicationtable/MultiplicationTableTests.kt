package com.baeldung.math.multiplicationtable

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MultiplicationTableTests {

    @Test
    fun `generate multiplication table using for loop`() {
        val num = 6
        val expectedTable = """
        6 * 1 = 6
        6 * 2 = 12
        6 * 3 = 18
        6 * 4 = 24
        6 * 5 = 30
        6 * 6 = 36
        6 * 7 = 42
        6 * 8 = 48
        6 * 9 = 54
        6 * 10 = 60
    """.trimIndent()

        val actualTable = generateMultiplicationTableUsingForLoop(num)

        assertEquals(expectedTable, actualTable)
    }

    private fun generateMultiplicationTableUsingForLoop(num: Int): String {
        val table = StringBuilder()
        for (i in 1..10) {
            val product = num * i
            table.append("$num * $i = $product\n")
        }
        return table.toString().trim()
    }

    @Test
    fun `generate multiplication table using while loop`() {
        val num = 10
        val expectedTable = """
        10 * 1 = 10
        10 * 2 = 20
        10 * 3 = 30
        10 * 4 = 40
        10 * 5 = 50
        10 * 6 = 60
        10 * 7 = 70
        10 * 8 = 80
        10 * 9 = 90
        10 * 10 = 100
    """.trimIndent()

        val actualTable = generateMultiplicationTableUsingWhileLoop(num)

        assertEquals(expectedTable, actualTable)
    }

    private fun generateMultiplicationTableUsingWhileLoop(num: Int): String {
        val table = StringBuilder()
        var i = 1
        while (i <= 10) {
            val product = num * i
            table.append("$num * $i = $product\n")
            i++
        }
        return table.toString().trim()
    }

    @Test
    fun `generate multiplication table functionally`() {
        val num = 7
        val expectedTable = """
            7 * 1 = 7
            7 * 2 = 14
            7 * 3 = 21
            7 * 4 = 28
            7 * 5 = 35
            7 * 6 = 42
            7 * 7 = 49
            7 * 8 = 56
            7 * 9 = 63
            7 * 10 = 70
        """.trimIndent()

        val actualTable = generateMultiplicationTableFunctionally(num)

        assertEquals(expectedTable, actualTable)
    }

    private fun generateMultiplicationTableFunctionally(num: Int): String {
        return (1..10).map { i ->
            val product = num * i
            "$num * $i = $product"
        }.joinToString("\n")
    }

}