package com.baeldung.kotlin.koin.plain

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.assertEquals

class KoinSpecificTest : KoinTest {
    @AfterEach
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun when_test_implements_KoinTest_then_KoinComponent_powers_are_available() {
        startKoin {
            modules(koinModule)
        }
        val helloSayer: HelloSayer = get()
        assertEquals("Hello!", helloSayer.sayHello())
    }

    @Test
    fun when_definition_repeats_then_the_last_one_wins() {
        startKoin {
            modules(
                koinModule,
                module {
                    single<RumourTeller> { RumourSource("I know everything about everyone!") }
                }
            )
        }
        val rumourTeller = get<RumourTeller>()
        assertEquals("I've heard that I know everything about everyone!", rumourTeller.tellRumour())
    }

}