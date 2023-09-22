package com.baeldung.extfunction.mock

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class ReverseTest {

    interface StringReversalService {
        fun reverse(input: String): String
    }

    class StringReversalServiceImpl : StringReversalService {
        override fun reverse(input: String): String {
            return input.reversed()
        }
    }

    // StringExtensions.kt

    fun String.reverseUsingService(service: StringReversalService): String {
        return service.reverse(this)
    }

    @Test
    fun testReverseUsingServiceWithMockito() {
        val input = "Hello, World!"
        val mockitoService = mock(StringReversalService::class.java)
        `when`(mockitoService.reverse(input)).thenReturn("!dlroW ,olleH")
        val reversed = input.reverseUsingService(mockitoService)
        assertEquals("!dlroW ,olleH", reversed)
    }

    @Test
    fun testReverseUsingServiceWithMockk() {
        val input = "Hello, World!"
        val mockService = mockk<StringReversalService>()
        every { mockService.reverse(input) } returns "!dlroW ,olleH"
        val reversed = input.reverseUsingService(mockService)
        assertEquals("!dlroW ,olleH", reversed)
    }
}