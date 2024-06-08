package com.baeldung.mockk

import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import io.mockk.unmockkStatic
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class MockkStatic {

    @AfterEach
    fun `Remove RandomNumberGenerator mockks`() {
        unmockkStatic(RandomNumberGenerator::class)
    }

    @AfterEach
    fun `Remove all mocks`() {
        unmockkAll()
    }

    @Test
    fun `Doing a coin flip should not throw an exception`() {
        repeat(1000) {
            coinFlip()
        }
    }

    @Test
    fun `Heads should be returned whenever random returns less than 0,5`() {
        // Simulate RandomNumberGenerator.random() returning a number < 0.5
        mockkStatic(RandomNumberGenerator::class)
        every { RandomNumberGenerator.random() } returns 0.1
        assertEquals(coinFlip(), "heads")
    }
}
