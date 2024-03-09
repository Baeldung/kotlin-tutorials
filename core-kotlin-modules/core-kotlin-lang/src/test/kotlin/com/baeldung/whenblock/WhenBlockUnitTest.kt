package com.baeldung.whenblock

import com.baeldung.whenblock.WhenBlockMultipleStatements.isPositiveInt
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertFailsWith

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WhenBlockUnitTest {

    private val outputStream = ByteArrayOutputStream()

    @BeforeAll
    fun setUp() {
        System.setOut(PrintStream(outputStream))
    }

    @AfterEach
    fun tearDown() {
        outputStream.reset()
    }

    @Test
    fun testWhenExpression() {
        val directoryType = UnixFileType.D

        val objectType = when (directoryType) {
            UnixFileType.D -> "d"
            UnixFileType.HYPHEN_MINUS -> "-"
            UnixFileType.L -> "l"
        }

        assertEquals("d", objectType)
    }

    @Test
    fun testWhenExpressionWithDefaultCase() {
        val fileType = UnixFileType.L

        val result = when (fileType) {
            UnixFileType.L -> "linking to another file"
            else -> "not a link"
        }

        assertEquals("linking to another file", result)
    }

    @Test
    fun testWhenExpressionWithThrowException() {
        assertFailsWith(IllegalArgumentException::class) {
            val fileType = UnixFileType.L

            when (fileType) {
                UnixFileType.HYPHEN_MINUS -> @Suppress("UNUSED_EXPRESSION")true
                else -> throw IllegalArgumentException("Wrong type of file")
            }
        }
    }

    @Test
    fun testWhenStatement() {
        val fileType = UnixFileType.HYPHEN_MINUS

        when (fileType) {
            UnixFileType.HYPHEN_MINUS -> println("Regular file type")
            UnixFileType.D -> println("Directory file type")
            else -> println("Other file type")
        }
    }

    @Test
    fun testCaseCombination() {
        val fileType = UnixFileType.D

        val frequentFileType: Boolean = when (fileType) {
            UnixFileType.HYPHEN_MINUS, UnixFileType.D -> true
            else -> false
        }

        assertTrue(frequentFileType)
    }

    @Test
    fun testWhenWithoutArgument() {
        val fileType = UnixFileType.L

        val objectType = when {
            fileType === UnixFileType.L -> "l"
            fileType === UnixFileType.HYPHEN_MINUS -> "-"
            fileType === UnixFileType.D -> "d"
            else -> "unknown file type"
        }

        assertEquals("l", objectType)
    }

    @Test
    fun testDynamicCaseExpression() {
        val unixFile = UnixFile.SymbolicLink(UnixFile.RegularFile("Content"))

        when {
            unixFile.getFileType() == UnixFileType.D -> println("It's a directory!")
            unixFile.getFileType() == UnixFileType.HYPHEN_MINUS -> println("It's a regular file!")
            unixFile.getFileType() == UnixFileType.L -> println("It's a soft link!")
        }
    }

    @Test
    fun testCollectionCaseExpressions() {
        val regularFile = UnixFile.RegularFile("Test Content")
        val symbolicLink = UnixFile.SymbolicLink(regularFile)
        val directory = UnixFile.Directory(listOf(regularFile, symbolicLink))

        val isRegularFileInDirectory = when (regularFile) {
            in directory.children -> true
            else -> false
        }

        val isSymbolicLinkInDirectory = when {
            symbolicLink in directory.children -> true
            else -> false
        }

        assertTrue(isRegularFileInDirectory)
        assertTrue(isSymbolicLinkInDirectory)
    }

    @Test
    fun testRangeCaseExpressions() {
        val fileType = UnixFileType.HYPHEN_MINUS

        val isCorrectType = when (fileType) {
            in UnixFileType.D..UnixFileType.L -> true
            else -> false
        }

        assertTrue(isCorrectType)
    }

    @Test
    fun testWhenWithIsOperatorWithSmartCase() {
        val unixFile: UnixFile = UnixFile.RegularFile("Test Content")

        val result = when (unixFile) {
            is UnixFile.RegularFile -> unixFile.content
            is UnixFile.Directory -> unixFile.children.map { it.getFileType() }.joinToString(", ")
            is UnixFile.SymbolicLink -> unixFile.originalFile.getFileType()
        }

        assertEquals("Test Content", result)
    }

    @Test
    fun testPositiveIntWithZero() {
        val givenNumber = 0
        val expectedOutput = "number is zero.\nIt's neither positive nor negative."
        val isPositive = isPositiveInt(givenNumber)
        assertFalse(isPositive)
        assertEquals(expectedOutput, outputStream.toString())
    }

    @Test
    fun testPositiveIntWithNegativeValue() {
        val givenNumber = -5
        val expectedOutput = "number is negative"
        val isPositive = isPositiveInt(givenNumber)
        assertFalse(isPositive)
        assertEquals(expectedOutput, outputStream.toString())
    }

    @Test
    fun testPositiveIntWithPositiveValue() {
        val givenNumber = 10
        val expectedOutput = "number is positive"
        val isPositive = isPositiveInt(givenNumber)
        assertTrue(isPositive)
        assertEquals(expectedOutput, outputStream.toString())
    }

}