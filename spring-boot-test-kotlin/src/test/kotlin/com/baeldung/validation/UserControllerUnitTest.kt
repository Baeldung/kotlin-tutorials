package com.baeldung.validation

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
class UserControllerUnitTest(@Autowired val mockMvc: MockMvc) {

    @Test
    fun whenPostRequestWithValidUserJson_thenReturnsStatus200() {
        mockMvc.perform(
            post("/users")
                .content(
                    jacksonObjectMapper().writeValueAsString(
                        User("John Doe", 18),
                    ),
                )
                .contentType(MediaType.APPLICATION_JSON),
        ).andExpect(status().isOk)
    }

    @Test
    fun whenPostRequestWithInvalidUserJson_thenReturnsStatus400() {
        mockMvc.perform(
            post("/users")
                .content(
                    jacksonObjectMapper().writeValueAsString(
                        User("", 16),
                    ),
                )
                .contentType(MediaType.APPLICATION_JSON),
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun whenPostRequestWithValidUserAddressJson_thenReturnsStatus200() {
        mockMvc.perform(
            post("/users/address")
                .content(
                    jacksonObjectMapper().writeValueAsString(
                        UserAddress(
                            "John Doe",
                            18,
                            Address(
                                "Street",
                                "City",
                                "Address",
                            ),
                        ),
                    ),
                )
                .contentType(MediaType.APPLICATION_JSON),
        ).andExpect(status().isOk)
    }

    @Test
    fun whenPostRequestWithInvalidUserAddressJson_thenReturnsStatus400() {
        mockMvc.perform(
            post("/users/address")
                .content(
                    jacksonObjectMapper().writeValueAsString(
                        UserAddress(
                            "John Doe",
                            18,
                            Address(
                                "Street",
                                "City",
                                "",
                            ),
                        ),
                    ),
                )
                .contentType(MediaType.APPLICATION_JSON),
        ).andExpect(status().isBadRequest)
    }
}
