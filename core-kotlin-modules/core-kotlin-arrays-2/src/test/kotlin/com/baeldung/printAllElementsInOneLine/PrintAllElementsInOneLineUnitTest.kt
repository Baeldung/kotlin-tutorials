package com.baeldung.printAllElementsInOneLine

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals


class PrintAllElementsInOneLineUnitTest {
    val myArray = arrayOf("A", "B", "C", "D", "E", "F")
    val arrayWithComma = arrayOf("A", "B, C", "D, E", "F")

    val newLine = System.lineSeparator()
    val stdOut = System.out
    val myOutput = ByteArrayOutputStream()

    @BeforeEach
    fun setup() {
        System.setOut(PrintStream(myOutput))
    }

    @AfterEach
    fun restore() {
        System.setOut(stdOut)
    }


    @Test
    fun `when using myOutput then we can verify println() result`() {
        println("Hello, world")
        assertEquals("Hello, world$newLine", myOutput.toString())

        myOutput.reset()

        println("Kotlin rocks")
        assertEquals("Kotlin rocks$newLine", myOutput.toString())
    }

    @Test
    fun `when converting array to list then all elements are in one line`() {
        println(myArray.asList())
        assertEquals("[A, B, C, D, E, F]$newLine", myOutput.toString())

        myOutput.reset()

        println(arrayWithComma.asList())
        // cannot customize the output format
        assertEquals("[A, B, C, D, E, F]$newLine", myOutput.toString())
    }


    @Test
    fun `when using print() then get the expected result`() {
        myArray.forEachIndexed { idx, e ->
            print(if (idx == myArray.lastIndex) "$e$newLine" else "$e, ")
        }
        assertEquals("A, B, C, D, E, F$newLine", myOutput.toString())

        myOutput.reset()

        arrayWithComma.forEachIndexed { idx, e ->
            print(if (idx == arrayWithComma.lastIndex) """"$e"$newLine""" else """"$e", """)
        }
        assertEquals(""""A", "B, C", "D, E", "F"$newLine""", myOutput.toString())
    }

    @Test
    fun `when using joinToString() then get the expected result`() {
        println(myArray.joinToString { it })
        assertEquals("A, B, C, D, E, F$newLine", myOutput.toString())

        myOutput.reset()

        println(arrayWithComma.joinToString { """"$it"""" })
        assertEquals(""""A", "B, C", "D, E", "F"$newLine""", myOutput.toString())
    }

}