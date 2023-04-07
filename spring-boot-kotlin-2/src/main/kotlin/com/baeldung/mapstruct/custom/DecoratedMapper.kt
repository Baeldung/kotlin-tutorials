package com.baeldung.mapstruct.custom

import com.baeldung.mapstruct.simple.UserMapper
import org.mapstruct.DecoratedWith
import org.mapstruct.Mapper

@Mapper
@DecoratedWith(UserMapperDecorator::class)
abstract class DecoratedMapper: UserMapper {
}

