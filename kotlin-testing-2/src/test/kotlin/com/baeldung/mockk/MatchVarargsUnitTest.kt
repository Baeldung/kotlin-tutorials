package com.baeldung.mockk

import io.mockk.MockKException
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MyClass {
    fun joinBySpace(vararg strings: String): String {
        return strings.joinToString(separator = " ")
    }
}

class MatchVarargsUnitTest {
    private val mockkObj = mockk<MyClass>()

    @Test
    fun `when calling concatStringsBySpace() on the real object then get expected result`() {
        val result = MyClass().joinBySpace("a", "b", "c", "d", "e")
        assertEquals("a b c d e", result)
    }

    @Test
    fun `when using anyVararg() on the mocked object then get expected result`() {
        every { mockkObj.joinBySpace("a", "b", "c", *anyVararg(), "x", "y", "z") } returns "Wow, Kotlin rocks!"

        val result = mockkObj.joinBySpace("a", "b", "c", "Baeldung", "is", "cool", "x", "y", "z")
        assertEquals("Wow, Kotlin rocks!", result)

        val result2 = mockkObj.joinBySpace("a", "b", "c", "x", "y", "z")
        assertEquals("Wow, Kotlin rocks!", result2)
    }


    @Test
    fun `when using varargAll() on the mocked object then get expected result`() {
        every { mockkObj.joinBySpace("a", "b", "c", *varargAll { it.startsWith("d") }, "z") } returns "Wow, Kotlin rocks!"

        val result = mockkObj.joinBySpace("a", "b", "c", "d1", "d2", "d-whatever", "z")
        assertEquals("Wow, Kotlin rocks!", result)

        assertThrows<MockKException> {
            mockkObj.joinBySpace("a", "b", "c", "d1", "Baeldung", "z")
        }.also { exception ->
            assertTrue(exception.message!!.startsWith("no answer found"))
        }
    }

    @Test
    fun `when using varargAny() on the mocked object then get expected result`() {
        every { mockkObj.joinBySpace("a", "b", "c", *varargAny { it.startsWith("d") }, "z") } returns "Wow, Kotlin rocks!"

        val result = mockkObj.joinBySpace("a", "b", "c", "d1", "d2", "d-whatever", "z")
        assertEquals("Wow, Kotlin rocks!", result)

        val result2 = mockkObj.joinBySpace("a", "b", "c", "d1", "Baeldung", "z")
        assertEquals("Wow, Kotlin rocks!", result2)
    }


    @Test
    fun `when using varargAll() with nArgs on the mocked object then get expected result`() {
        every { mockkObj.joinBySpace("a", "b", "c", *varargAll { nArgs > 6 }, "z") } returns "Wow, Kotlin rocks!"

        val result = mockkObj.joinBySpace("a", "b", "c", "Baeldung", "is", "cool", "z")
        assertEquals("Wow, Kotlin rocks!", result)

        assertThrows<MockKException> {
            mockkObj.joinBySpace("a", "b", "c", "Baeldung", "z")
        }.also { exception ->
            assertTrue(exception.message!!.startsWith("no answer found"))
        }
    }

    @Test
    fun `when using varargAny() with nArgs on the mocked object then get expected result`() {
        every { mockkObj.joinBySpace("a", "b", "c", *varargAny { nArgs > 6 }, "z") } returns "Wow, Kotlin rocks!"

        val result = mockkObj.joinBySpace("a", "b", "c", "Baeldung", "is", "cool", "z")
        assertEquals("Wow, Kotlin rocks!", result)

        assertThrows<MockKException> {
            mockkObj.joinBySpace("a", "b", "c", "Baeldung", "z")
        }.also { exception ->
            assertTrue(exception.message!!.startsWith("no answer found"))
        }
    }


    @Test
    fun `when using varargAll() with position on the mocked object then get expected result`() {
        every {
            mockkObj.joinBySpace(
                "a", "b", "c", *varargAll { if (position % 2 == 0) it == "E" else it == "O" }, "z"
            )
        } returns "Wow, Kotlin rocks!"


        val result = mockkObj.joinBySpace("a", "b", "c", "O", "E", "O", "E", "z")
        assertEquals("Wow, Kotlin rocks!", result)

        assertThrows<MockKException> {
            mockkObj.joinBySpace("a", "b", "c", "Baeldung", "z")
        }.also { exception ->
            assertTrue(exception.message!!.startsWith("no answer found"))
        }

        assertThrows<MockKException> {
            mockkObj.joinBySpace("a", "b", "c", "O", "Baeldung", "is", "cool", "z")
        }.also { exception ->
            assertTrue(exception.message!!.startsWith("no answer found"))
        }

    }

    @Test
    fun `when using varargAny() with position on the mocked object then get expected result`() {
        every {
            mockkObj.joinBySpace(
                "a", "b", "c", *varargAny { if (position % 2 == 0) it == "E" else it == "O" }, "z"
            )
        } returns "Wow, Kotlin rocks!"

        assertThrows<MockKException> {
            mockkObj.joinBySpace("a", "b", "c", "Baeldung", "z")
        }.also { exception ->
            assertTrue(exception.message!!.startsWith("no answer found"))
        }

        val result = mockkObj.joinBySpace("a", "b", "c", "O", "Baeldung", "is", "cool", "z")
        assertEquals("Wow, Kotlin rocks!", result)
    }

}