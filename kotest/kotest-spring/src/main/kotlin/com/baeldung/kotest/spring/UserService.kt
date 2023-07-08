package com.baeldung.kotest.spring

import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    fun findUserById(id: Long) = userRepository.findUserById(id)
}