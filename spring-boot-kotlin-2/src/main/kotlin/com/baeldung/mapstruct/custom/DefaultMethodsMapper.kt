package com.baeldung.mapstruct.custom

import com.baeldung.mapstruct.simple.AddressDto
import com.baeldung.mapstruct.simple.User
import com.baeldung.mapstruct.simple.UserDto
import com.baeldung.mapstruct.simple.UserMapper
import java.text.SimpleDateFormat
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface DefaultMethodsMapper: UserMapper {

    @Mapping(source = "createdAt", target = "createdOn")
    override fun toDto(user: User): UserDto {
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
}