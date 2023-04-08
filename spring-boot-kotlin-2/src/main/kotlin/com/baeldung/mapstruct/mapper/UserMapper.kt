package com.baeldung.mapstruct.mapper

import com.baeldung.mapstruct.dto.AddressDto
import com.baeldung.mapstruct.dto.UserDto
import com.baeldung.mapstruct.entity.Address
import com.baeldung.mapstruct.entity.User
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface UserMapper {
    @Mapping(source = "createdAt", target = "createdOn", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "status", constant = "ACTIVE")
    fun toDto(user: User): UserDto

    @Mapping(source = "createdOn", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss", defaultExpression = "java(new java.util.Date())")
    @Mapping(source = "status", target = "status", defaultValue = "ACTIVE")
    fun toBean(userDto: UserDto): User

    fun toAddressDto(address: Address): AddressDto
    fun toAddress(addressDto: AddressDto): Address
}   