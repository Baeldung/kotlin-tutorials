package com.baeldung.testresources

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class OurClass(private val externalDependency: ExternalDependency) {
    fun doWork() = externalDependency.functionToBeMocked()
}

class ExternalDependency {
    fun functionToBeMocked(): String = "Real Result"
}

class MockkTestResourcesUnitTest : StringSpec({
    val externalDependency: ExternalDependency = mockk()
    val ourClass = OurClass(externalDependency)
    beforeTest {
        every { externalDependency.functionToBeMocked() } returns "mock response"
    }
    afterTest {
        clearMocks(externalDependency)
    }

    "test doWork function" {
        val result = ourClass.doWork()
        result shouldBe "mock response"
        verify { externalDependency.functionToBeMocked() }
    }
})