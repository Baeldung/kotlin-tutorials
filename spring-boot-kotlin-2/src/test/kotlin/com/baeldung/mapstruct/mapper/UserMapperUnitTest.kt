package com.baeldung.mapstruct.mapper

import com.baeldung.mapstruct.dto.AddressDto
import com.baeldung.mapstruct.dto.UserDto
import com.baeldung.mapstruct.entity.Address
import com.baeldung.mapstruct.entity.User
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class UserMapperUnitTest {
    private var userMapper: UserMapper = Mappers.getMapper(UserMapper::class.java)

    @Test
    fun `should map entity to dto`() {
        val user = User(
            id = 1,
            name = "John Doe",
            createdAt = Date().apply {
                time = 1679682600000 // 2023-03-25 00:00:00
            },
            address = Address (
                streetAddress = "123 Main St",
                zipCode = "12345"
            ),
            status = "UNKNOWN"
        )
        val userDto = userMapper.toDto(user)
        assertEquals( user.id, userDto.id)
        assertEquals(user.name, userDto.name)
        assertEquals("2023-03-25 00:00:00", userDto.createdOn)
        assertEquals( user.address.zipCode, userDto.address.zipCode)
        assertEquals("ACTIVE", userDto.status)
    }

    @Test
    fun `should map dto to entity`() {
        val userDto = UserDto(
            id = 1,
            name = "John Doe",
            address = AddressDto (
                streetAddress = "123 Main St",
                zipCode = "12345"
            )
        )
        val user = userMapper.toBean(userDto)
        assertEquals(userDto.id, user.id)
        assertEquals(userDto.name, user.name)
        assertNotNull(user.createdAt)
        assertEquals(userDto.address.zipCode, user.address.zipCode)
        assertEquals("ACTIVE", user.status)
    }
}