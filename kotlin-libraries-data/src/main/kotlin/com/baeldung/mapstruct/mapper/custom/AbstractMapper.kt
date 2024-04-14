package com.baeldung.mapstruct.mapper.custom

import com.baeldung.mapstruct.dto.AddressDto
import com.baeldung.mapstruct.dto.UserDto
import com.baeldung.mapstruct.entity.Address
import com.baeldung.mapstruct.entity.User
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import java.text.SimpleDateFormat

@Mapper
abstract class AbstractMapper {

    fun toDto(user: User): UserDto {
        return UserDto(
            id = user.id,
            name = "Mr. ${user.name}",
            createdOn = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.createdAt),
            address = AddressDto(
                streetAddress = user.address.streetAddress,
                zipCode = user.address.zipCode
            ),
            status = "ACTIVE"
        )
    }

    @Mapping(source = "createdOn", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss", defaultExpression = "java(new java.util.Date())")
    @Mapping(source = "status", target = "status", defaultValue = "ACTIVE")
    abstract fun toBean(userDto: UserDto): User

    abstract fun toAddressDto(address: Address): AddressDto
    abstract fun toAddress(addressDto: AddressDto): Address
}