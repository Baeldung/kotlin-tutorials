package com.baeldung.validation

import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class UserAddress(
    @field:NotBlank(message = "Name must not be blank")
    val name: String,
    @field:Min(value = 18, message = "Age must be at least 18")
    val age: Int,
    @field:Valid val address: Address,
)
