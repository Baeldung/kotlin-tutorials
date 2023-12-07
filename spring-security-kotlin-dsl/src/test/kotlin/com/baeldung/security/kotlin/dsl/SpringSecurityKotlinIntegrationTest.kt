package com.baeldung.security.kotlin.dsl

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

@SpringBootTest
@AutoConfigureMockMvc
class SpringSecurityKotlinIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `ordinary user not permitted to access the endpoint`() {
        this.mockMvc
                .perform(get("/greetings")
                .with(httpBasic("user", "password")))
                .andExpect(unauthenticated())
    }
}
