package com.baeldung.versioning.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus

@SpringBootTest(classes = [com.baeldung.crud.SpringBootCrudApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PathBasedGreetingControllerIntegrationTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @LocalServerPort
    var port: Int = 0

    @Test
    fun `given version 1 in path when greeting then return v1 message`() {
        val response = testRestTemplate.getForEntity("http://localhost:$port/v1/greeting", String::class.java)
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isEqualTo("Hello, welcome to the API v1!")
    }

    @Test
    fun `given version 2 in path when greeting then return v2 message`() {
        val response = testRestTemplate.getForEntity("http://localhost:$port/v2/greeting", String::class.java)
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isEqualTo("Hello, welcome to the API v2!")
    }
}