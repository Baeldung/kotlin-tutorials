package com.baeldung.mapstruct.dto

data class UserDto(
    val id: Long,
    var name: String,
    val createdOn: String? = null,
    val address: AddressDto,
    val status: String? = null
)