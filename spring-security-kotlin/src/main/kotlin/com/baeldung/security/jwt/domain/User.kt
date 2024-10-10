package com.baeldung.security.jwt.domain

import java.util.UUID

data class User(
    val id: UUID,
    val name: String,
    val password: String,
    val role: Role
)

enum class Role {
    USER, ADMIN
}