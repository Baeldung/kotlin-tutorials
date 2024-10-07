package com.baeldung.di

import com.baeldung.di.UserService

class UserServiceImpl : UserService {
    override fun getUser(): String = "John Doe"
}