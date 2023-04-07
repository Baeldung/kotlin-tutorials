package com.baeldung.console

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.Console
import java.io.InputStreamReader
import java.io.PrintStream
import java.util.*
import java.lang.RuntimeException


class ConsoleIOUnitTest {

    @Test
    fun givenText_whenPrint_thenPrintText() {
        val expectedTest = "Hello from Kotlin"
        val out = ByteArrayOutputStream()
        System.setOut(PrintStream(out))

        print(expectedTest)
        out.flush()
        val printedText = String(out.toByteArray())

        assertThat(printedText).isEqualTo(expectedTest)
    }

    @Test
    fun givenInput_whenRead_thenReadText() {
        val expectedTest = "Hello from Kotlin"
        val input = ByteArrayInputStream(expectedTest.toByteArray())
        System.setIn(input)

        val readText = readLine()

        assertThat(readText).isEqualTo(expectedTest)
    }

    @Test
    fun givenInput_whenReadln_thenReadText() {
        val expectedInput = "Hello from Kotlin"
        val input = ByteArrayInputStream(expectedInput.toByteArray())
        System.setIn(input)

        val readText = readln()

        assertThat(readText).isEqualTo(expectedInput)
    }

    @Test
    fun givenInput_whenReadlnOrNull_thenReadText() {
        val expectedInput = "Hello from Kotlin"
        val input = ByteArrayInputStream(expectedInput.toByteArray())
        System.setIn(input)

        val readText = readlnOrNull()

        assertThat(readText).isEqualTo(expectedInput)
    }

    @Test
    fun givenEofReachedInput_whenReadlnOrNull_thenReadNull() {
        val readText = readlnOrNull()
        assertThat(readText).isEqualTo(null)
    }

    @Test
    fun givenEofReachedInput_whenReadln_thenThrowsRunTimeException() {
        assertThrows<RuntimeException> {  var readText = readln()}
    }

    @Test
    fun givenInput_whenReadWithScanner_thenReadText() {
        val expectedTest = "Hello from Kotlin"
        val scanner = Scanner(ByteArrayInputStream(expectedTest.toByteArray()))

        val readText = scanner.nextLine()

        assertThat(readText).isEqualTo(expectedTest)
    }

    @Test
    fun givenInput_whenReadWithBufferedReader_thenReadText() {
        val expectedTest = "Hello from Kotlin"
        val reader = BufferedReader(InputStreamReader(ByteArrayInputStream(expectedTest.toByteArray())))

        val readText = reader.readLine()

        assertThat(readText).isEqualTo(expectedTest)
    }

    @Test
    fun givenInput_whenReadWithConsole_thenReadText() {
        val expectedTest = "Hello from Kotlin"
        val console = mock(Console::class.java)
        `when`(console.readLine()).thenReturn(expectedTest)

        val readText = console.readLine()

        assertThat(readText).isEqualTo(expectedTest)
    }

    @AfterEach
    fun resetIO() {
        System.setOut(System.out)
        System.setIn(System.`in`)
    }
}