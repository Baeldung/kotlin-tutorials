package com.baeldung.koin.dagger.migration

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

class MyServiceUnitTest : KoinTest {

    private val myService: MyService by inject()
    private val testModule = module {
        single { MyRepository() }
        factory { MyService(get()) }
    }

    @BeforeEach
    fun setUp() {
        startKoin {
            modules(testModule)
        }
    }

    @AfterEach
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `test my service returns expected value`() {
        val result = myService.performAction()
        assertEquals("Service is using: Repository data", result)
    }
}