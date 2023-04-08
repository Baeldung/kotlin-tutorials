package com.baeldung.mapstruct.mapper.custom

import com.baeldung.mapstruct.entity.Address
import com.baeldung.mapstruct.entity.User
import com.baeldung.mapstruct.mapper.UserMapper
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import java.util.*
import kotlin.test.assertEquals

class DecoratedMapperUnitTest {
    private var userMapper: UserMapper = Mappers.getMapper(DecoratedMapper::class.java)

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
        assertEquals("Mr. John Doe", userDto.name)
        assertEquals("2023-03-25 00:00:00", userDto.createdOn)
        assertEquals( user.address.zipCode, userDto.address.zipCode)
        assertEquals("ACTIVE", userDto.status)
    }
}