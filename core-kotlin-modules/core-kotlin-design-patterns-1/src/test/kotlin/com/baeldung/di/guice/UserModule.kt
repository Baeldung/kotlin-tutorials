package com.baeldung.di.guice

import com.baeldung.di.UserService
import com.baeldung.di.UserServiceImpl
import com.google.inject.AbstractModule

class UserModule : AbstractModule() {
    override fun configure() {
        bind(UserService::class.java).to(UserServiceImpl::class.java)
    }
}