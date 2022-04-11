package com.baeldung.kotlin.koin.plain

import io.mockk.every
import io.mockk.mockkClass
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.koin.core.component.inject
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.junit5.KoinTestExtension
import org.koin.test.junit5.mock.MockProviderExtension
import org.koin.test.mock.declareMock

class KoinWithExtensionsTest: KoinTest {
    @JvmField
    @RegisterExtension
    val koinTestExtension = KoinTestExtension.create {
        modules(
            module {
                single<RumourTeller> { RumourSource("I know everything about everyone!") }
            })
    }

    @JvmField
    @RegisterExtension
    val mockProvider = MockProviderExtension.create { clazz ->
        mockkClass(clazz)
    }

    @Test
    fun when_extensions_are_used_then_mocking_is_easier() {
        declareMock<RumourTeller> {
            every { tellRumour() } returns "I don't even know."
        }
        val mockedTeller: RumourTeller by inject()
        assertEquals("I don't even know.", mockedTeller.tellRumour())
    }
}