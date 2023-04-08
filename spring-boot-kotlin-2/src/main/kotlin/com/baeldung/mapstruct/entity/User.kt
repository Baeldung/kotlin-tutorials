package com.baeldung.mapstruct.entity

import java.util.*

class User(
    var name: String,
    val createdAt: Date,
    val id: Long,
    val address: Address,
    val status: String? = null
)