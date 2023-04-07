package com.baeldung.mapstruct.simple

data class UserDto(
    val id: Long,
    var name: String,
    val createdOn: String? = null,
    val address: AddressDto,
    val status: String? = null
)