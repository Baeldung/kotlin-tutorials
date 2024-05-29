package com.baeldung.yaml.model

import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val city: String,
    val country: String,
)

@Serializable
data class User(
    val name: String,
    val age: Int,
    val address: Address
)

@Serializable
data class Users(
    val users: List<User>
)
