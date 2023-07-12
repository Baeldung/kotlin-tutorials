package com.baeldung.kotest.spring

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.stereotype.Repository

@Repository
class UserRepository {

    private val users = mutableListOf<User>()

    fun save(user: User) {
        users += user
    }

    fun findUserById(id: Long): User? {
        return users.find { it.userId == id }
    }
}


data class User(@JsonProperty("id") val userId: Long, @JsonProperty("name") val username: String)