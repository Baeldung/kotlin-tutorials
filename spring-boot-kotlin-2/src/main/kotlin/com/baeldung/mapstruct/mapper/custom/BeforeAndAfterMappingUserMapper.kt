package com.baeldung.mapstruct.mapper.custom

import com.baeldung.mapstruct.dto.UserDto
import com.baeldung.mapstruct.mapper.UserMapper
import org.mapstruct.AfterMapping
import org.mapstruct.BeforeMapping
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget

@Mapper
abstract class BeforeAndAfterMappingUserMapper: UserMapper {
    @BeforeMapping
    fun beforeMapping(userDto: UserDto) {
        userDto.name = userDto.name.removePrefix("Mr. ");
    }

    @AfterMapping
    fun afterMapping(@MappingTarget userDto: UserDto) {
        userDto.name = "Mr. " + userDto.name;
    }
}   