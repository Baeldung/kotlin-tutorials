package com.baeldung.mockk

import io.mockk.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class BasicMockKUnitTest {

    @Test
    fun givenServiceMock_whenCallingMockedMethod_thenCorrectlyVerified() {
        // given
        val service = mockk<TestableService>()
        every { service.getDataFromDb("Expected Param") } returns "Expected Output"
        // when
        val result = service.getDataFromDb("Expected Param")
        // then
        verify { service.getDataFromDb("Expected Param") }
        assertEquals("Expected Output", result)
    }

    @Test
    fun givenServiceSpy_whenMockingOnlyOneMethod_thenOtherMethodsShouldBehaveAsOriginalObject() {
        // given
        val service = spyk<TestableService>()
        every { service.getDataFromDb(any()) } returns "Mocked Output"
        // when checking mocked method
        val firstResult = service.getDataFromDb("Any Param")
        // then
        assertEquals("Mocked Output", firstResult)
        // when checking not mocked method
        val secondResult = service.doSomethingElse("Any Param")
        // then
        assertEquals("I don't want to!", secondResult)
    }

    @Test
    fun givenRelaxedMock_whenCallingNotMockedMethod_thenReturnDefaultValue() {
        // given
        val service = mockk<TestableService>(relaxed = true)
        // when
        val result = service.getDataFromDb("Any Param")
        // then
        assertEquals("", result)
    }

    @Test
    fun givenObject_whenMockingIt_thenMockedMethodShouldReturnProperValue() {
        // given
        val service = TestableService()
        mockkObject(service)
        // when calling not mocked method
        val firstResult = service.getDataFromDb("Any Param")
        // then return real response
        assertEquals("Value from DB", firstResult)

        // when calling mocked method
        every { service.getDataFromDb(any()) } returns "Mocked Output"
        val secondResult = service.getDataFromDb("Any Param")
        // then return mocked response
        assertEquals("Mocked Output", secondResult)
    }

    @Test
    fun givenMock_whenCapturingParamValue_thenProperValueShouldBeCaptured() {
        // given
        val service = mockk<TestableService>()
        val slot = slot<String>()
        every { service.getDataFromDb(capture(slot)) } returns "Expected Output"
        // when
        service.getDataFromDb("Expected Param")
        // then
        assertEquals("Expected Param", slot.captured)
    }

    @Test
    fun givenMock_whenCapturingParamsValues_thenProperValuesShouldBeCaptured() {
        // given
        val service = mockk<TestableService>()
        val list = mutableListOf<String>()
        every { service.getDataFromDb(capture(list)) } returns "Expected Output"
        // when
        service.getDataFromDb("Expected Param 1")
        service.getDataFromDb("Expected Param 2")
        // then
        assertEquals(2, list.size)
        assertEquals("Expected Param 1", list[0])
        assertEquals("Expected Param 2", list[1])
    }


    @Test
    fun givenServiceMock_whenCallingMethodReturnsUnit_thenCorrectlyVerified() {
        // given
        val service = mockk<TestableService>()
        val myList = mutableListOf<String>()

        // when
        every { service.addHelloWorld(any()) } just Runs
        service.addHelloWorld(myList)

        // then
        assertTrue(myList.isEmpty())
    }

    @Test
    fun givenServiceMock_whenCallingOriginalMethod_thenCorrectlyVerified() {
        // given
        val service = mockk<TestableService>()
        val myList = mutableListOf<String>()

        // when
        every { service.addHelloWorld(any()) } answers { callOriginal() }
        service.addHelloWorld(myList)

        // then
        assertEquals(1, myList.size)
        assertEquals("Hello World!", myList.first())
    }

    @Test
    fun givenServiceMock_whenStubbingTwoScenarios_thenCorrectlyVerified() {
        // given
        val service = mockk<TestableService>()
        val kaiList = mutableListOf("Kai")
        val emptyList = mutableListOf<String>()

        // when
        every { service.addHelloWorld(any()) } just runs
        every { service.addHelloWorld(match { "Kai" in it }) } answers { callOriginal() }

        service.addHelloWorld(kaiList)
        service.addHelloWorld(emptyList)

        // then
        assertEquals(listOf("Kai", "Hello World!"), kaiList)
        assertTrue(emptyList.isEmpty())
    }
}