package com.baeldung.kotlin.koin.plain

import io.mockk.mockkClass
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.junit5.mock.MockProviderExtension

class KoinSelfTest : KoinTest {

    @Test
    fun when_module_is_correct_then_the_check_passes() {
koinApplication {
    modules(koinModule, staticRumourModule)
    checkModules()
}
    }

    @JvmField
    @RegisterExtension
    val mockProvider = MockProviderExtension.create { clazz ->
        mockkClass(clazz)
    }

    @Test
    fun when_module_has_heavy_objects_then_it_can_be_completed_with_mocks() {
        koinApplication {
            modules(koinModule, module { single { RumourSource() } })
            checkModules {
                withInstance<RumourSource>()
                withParameter<RumourTeller> { "Some param" }
            }
        }
    }
}