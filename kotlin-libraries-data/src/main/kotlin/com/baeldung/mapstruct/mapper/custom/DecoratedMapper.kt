package com.baeldung.mapstruct.mapper.custom

import com.baeldung.mapstruct.mapper.UserMapper
import org.mapstruct.DecoratedWith
import org.mapstruct.Mapper

@Mapper
@DecoratedWith(UserMapperDecorator::class)
abstract class DecoratedMapper: UserMapper {
}

