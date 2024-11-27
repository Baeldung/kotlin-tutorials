package com.baeldung.di.guice

import com.baeldung.di.UserService
import com.google.inject.Guice
import com.google.inject.Inject
import com.google.inject.Injector
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GuiceUnitTest {
    @Inject
    private lateinit var userService: UserService

    @Test
    fun `obtain dependency using guice`() {
        val injector: Injector = Guice.createInjector(UserModule())
        injector.injectMembers(this)

        assertEquals("John Doe", userService.getUser())
    }
}