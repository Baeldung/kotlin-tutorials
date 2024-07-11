package com.baeldung.validation

import jakarta.validation.constraints.NotBlank

data class Address(
    @field:NotBlank(message = "Street must not be blank")
    val street: String,
    @field:NotBlank(message = "City must not be blank")
    val city: String,
    @field:NotBlank(message = "State must not be blank")
    val state: String,
)
