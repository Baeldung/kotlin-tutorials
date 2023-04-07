package com.baeldung.mapstruct.simple

import java.util.Date

class User(
    var name: String,
    val createdAt: Date,
    val id: Long,
    val address: Address,
    val status: String? = null
)