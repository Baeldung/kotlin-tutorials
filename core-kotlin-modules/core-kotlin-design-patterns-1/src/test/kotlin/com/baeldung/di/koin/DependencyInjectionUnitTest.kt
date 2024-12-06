package com.baeldung.di.koin

import com.baeldung.di.UserService
import com.baeldung.di.UserServiceImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.module.Module


class DependencyInjectionUnitTest: KoinComponent {

    val appModule: Module = module { single<UserService> { UserServiceImpl() } }
    private val service: UserService by inject()

    @Test
    fun `DI demonstration with Koin`() {
        startKoin {
            modules(appModule)
        }
        assertEquals("John Doe", service.getUser())
    }
}