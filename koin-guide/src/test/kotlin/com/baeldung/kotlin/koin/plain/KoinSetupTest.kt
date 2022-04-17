package com.baeldung.kotlin.koin.plain

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class KoinSetupTest {
    @AfterEach
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun when_singleton_is_used_then_the_first_parameter_wins() {
        startKoin {
            modules(singleWithParamModule)
        }
        val component = object : KoinComponent {
            val instance1 = get<RumourSource> { parametersOf("I've seen nothing") }
            val instance2 = get<RumourSource> { parametersOf("Jane is seeing Gill") }
        }

        assertEquals("I've heard that I've seen nothing", component.instance1.tellRumour())
        assertEquals("I've heard that I've seen nothing", component.instance2.tellRumour())
    }

    @Test
    fun when_factory_is_used_then_the_each_parameter_is_honoured() {
        startKoin {
            modules(factoryScopeModule)
        }
        val component = object : KoinComponent {
            val instance1 = get<RumourSource> { parametersOf("I've seen nothing") }
            val instance2 = get<RumourSource> { parametersOf("Jane is seeing Gill") }
        }

        assertEquals("I've heard that I've seen nothing", component.instance1.tellRumour())
        assertEquals("I've heard that Jane is seeing Gill", component.instance2.tellRumour())
    }

    @Test
    fun when_definitions_of_the_same_type_are_named_then_they_can_be_disntinguished() {
        startKoin {
            modules(namedSources)
        }
        val component = object : KoinComponent {
            val silentBob: RumourSource = get(qualifier = named("Silent Bob"))
            val jay: RumourSource = get(qualifier = named("Jay"))
        }
        assertNotEquals(component.jay.tellRumour(), component.silentBob.tellRumour())
    }
}