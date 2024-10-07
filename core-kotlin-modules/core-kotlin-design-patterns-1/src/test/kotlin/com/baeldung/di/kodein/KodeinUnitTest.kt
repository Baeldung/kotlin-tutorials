package com.baeldung.di.kodein

import com.baeldung.di.UserService
import com.baeldung.di.UserServiceImpl
import org.junit.jupiter.api.Test
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import kotlin.test.assertEquals

val kodein = DI {
    bind<UserService>() with singleton { UserServiceImpl() }
}

class KodeinUnitTest {
    private val userService: UserService by kodein.instance()

    @Test
    fun `obtain dependency using kodein`(){
        assertEquals("John Doe", userService.getUser())
    }
}