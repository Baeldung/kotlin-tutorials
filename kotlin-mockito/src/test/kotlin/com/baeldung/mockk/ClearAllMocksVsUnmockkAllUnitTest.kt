package com.baeldung.mockk

import io.mockk.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class GreetingService {
    fun greeting(name: String) = "$name, how are you?"

    companion object {
        fun sayGoodDay(name: String) = "$name, good day!"
    }
}

object GoodMorning {
    fun sayGoodMorning(name: String) = "$name, good morning!"
}

fun sayGoodNight(name: String) = "$name, good night!"

class ClearAllMocksVsUnmockkAllUnitTest {

    @Test
    fun `when using clearAllMocks() then Mock objects are cleared`() {
        val greetingServiceMock = mockk<GreetingService> {
            every { greeting(any()) } returns "mocked instance"
        }
        assertEquals("mocked instance", greetingServiceMock.greeting("Kai"))

        clearAllMocks()

        assertThrows<MockKException> {
            greetingServiceMock.greeting("Kai")
        }.also { it.message?.startsWith("no answer found for: com.baeldung.mockk.GreetingService") }
    }

    @Test
    fun `when using unmockkAll() then Mock objects are still available`() {
        val greetingServiceMock = mockk<GreetingService> {
            every { greeting(any()) } returns "mocked instance"
        }
        assertEquals("mocked instance", greetingServiceMock.greeting("Kai"))

        unmockkAll()

        assertEquals("mocked instance", greetingServiceMock.greeting("Kai"))
    }


    @Test
    fun `when using clearAllMocks() then mocks are cleared`() {
        mockkObject(GreetingService)
        every { GreetingService.sayGoodDay(any()) } returns "mocked static fun"
        assertEquals("mocked static fun", GreetingService.sayGoodDay("Kai"))

        mockkObject(GoodMorning)
        every { GoodMorning.sayGoodMorning(any()) } returns "mocked object"
        assertEquals("mocked object", GoodMorning.sayGoodMorning("Kai"))

        mockkStatic(::sayGoodNight)
        every { sayGoodNight(any()) } returns "mocked top-level fun"
        assertEquals("mocked top-level fun", sayGoodNight("Kai"))

        clearAllMocks()

        assertEquals("Kai, good day!", GreetingService.sayGoodDay("Kai"))
        assertEquals("Kai, good morning!", GoodMorning.sayGoodMorning("Kai"))
        assertEquals("Kai, good night!", sayGoodNight("Kai"))
    }

    @Test
    fun `when using unmockkAll() then mocks are unmocked`() {
        mockkObject(GreetingService)
        every { GreetingService.sayGoodDay(any()) } returns "mocked static fun"
        assertEquals("mocked static fun", GreetingService.sayGoodDay("Kai"))

        mockkObject(GoodMorning)
        every { GoodMorning.sayGoodMorning(any()) } returns "mocked object"
        assertEquals("mocked object", GoodMorning.sayGoodMorning("Kai"))

        mockkStatic(::sayGoodNight)
        every { sayGoodNight(any()) } returns "mocked top-level fun"
        assertEquals("mocked top-level fun", sayGoodNight("Kai"))

        unmockkAll()

        assertEquals("Kai, good day!", GreetingService.sayGoodDay("Kai"))
        assertEquals("Kai, good morning!", GoodMorning.sayGoodMorning("Kai"))
        assertEquals("Kai, good night!", sayGoodNight("Kai"))
    }

    @Test
    fun `after calling clearAllMocks() then the mock can be reused`() {
        mockkStatic(::sayGoodNight)
        every { sayGoodNight(any()) } returns "mocked top-level fun"
        assertEquals("mocked top-level fun", sayGoodNight("Kai"))

        clearAllMocks()

        assertEquals("Kai, good night!", sayGoodNight("Kai"))

        every { sayGoodNight(any()) } returns "get mocked again"
        assertEquals("get mocked again", sayGoodNight("Kai"))
    }

    @Test
    fun `after calling unmockkAll() then the mock cannot be reused`() {
        mockkStatic(::sayGoodNight)
        every { sayGoodNight(any()) } returns "mocked top-level fun"
        assertEquals("mocked top-level fun", sayGoodNight("Kai"))

        unmockkAll()

        assertEquals("Kai, good night!", sayGoodNight("Kai"))
        assertThrows<MockKException> {
            every { sayGoodNight(any()) } returns "mocked again"
        }.also { it.message?.startsWith("Failed matching mocking signature") }

        mockkStatic(::sayGoodNight)
        every { sayGoodNight(any()) } returns "mocked again"
        assertEquals("mocked again", sayGoodNight("Kai"))

    }

}