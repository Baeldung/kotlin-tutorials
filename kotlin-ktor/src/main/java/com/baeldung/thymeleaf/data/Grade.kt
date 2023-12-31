package com.baeldung.thymeleaf.data

data class Grade (
    val id: String,
    val subject: String,
    var value: String = String()
)
