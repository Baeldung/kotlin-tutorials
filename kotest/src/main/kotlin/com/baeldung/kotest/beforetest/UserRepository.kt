package com.baeldung.kotest.beforetest


data class User(val id: Long, val username: String)

class UserRepository {
    private val users = mutableListOf<User>()

    fun addUser(user: User) {
        users.add(user)
    }

    fun all() = users.toList()
}
