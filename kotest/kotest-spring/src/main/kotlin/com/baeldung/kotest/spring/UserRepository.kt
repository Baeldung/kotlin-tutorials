package com.baeldung.kotest.spring

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.stereotype.Repository

@Repository
class UserRepository {
    fun findUserById(id: Long): User {
        return User(1, "John Doe")
    }
}


data class User(@JsonProperty("id") val userId: Long, @JsonProperty("name") val username: String)