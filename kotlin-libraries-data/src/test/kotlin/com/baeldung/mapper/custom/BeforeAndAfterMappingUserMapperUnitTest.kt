package com.baeldung.mapstruct.mapper.custom

import com.baeldung.mapstruct.dto.AddressDto
import com.baeldung.mapstruct.dto.UserDto
import com.baeldung.mapstruct.entity.Address
import com.baeldung.mapstruct.entity.User
import com.baeldung.mapstruct.mapper.UserMapper
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import java.util.*
import kotlin.test.assertEquals


class BeforeAndAfterMappingUserMapperUnitTest {
    private var userMapper: UserMapper = Mappers.getMapper(BeforeAndAfterMappingUserMapper::class.java)

    @Test
    fun `should map entity to dto`() {
        val user = User(
            id = 1,
            name = "John Doe",
            createdAt = Date().apply {
                time = 1679682600000 // 2023-03-24 18:30:00
            },
            address = Address (
                streetAddress = "123 Main St",
                zipCode = "12345"
            ),
        )
        val userDto = userMapper.toDto(user)
        assertEquals("Mr. John Doe", userDto.name)
    }

    @Test
    fun `should map dto to entity`() {
        val userDto = UserDto(
            id = 1,
            name = "Mr. John Doe",
            createdOn = "2023-03-24 18:30:00",
            address = AddressDto(
                streetAddress = "123 Main St",
                zipCode = "12345"
            ),
            status = "ACTIVE"
        )
        val user = userMapper.toBean(userDto)
        assertEquals("John Doe", user.name)
    }
}