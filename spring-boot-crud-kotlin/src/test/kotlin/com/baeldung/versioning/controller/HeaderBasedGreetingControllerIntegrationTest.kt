package com.baeldung.versioning.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

@SpringBootTest(classes = [com.baeldung.crud.SpringBootCrudApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HeaderBasedGreetingControllerIntegrationTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @LocalServerPort
    var port: Int = 0

    @Test
    fun `given version 1 in header when greeting then return v1 message`() {
        val headers = HttpHeaders()
        headers.set("X-API-Version", "1")
        val entity = HttpEntity<String>(headers)
        val response = testRestTemplate.exchange("http://localhost:$port/greeting", HttpMethod.GET, entity, String::class.java)
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isEqualTo("Hello, welcome to the API v1!")
    }

    @Test
    fun `given version 2 in header when greeting then return v2 message`() {
        val headers = HttpHeaders()
        headers.set("X-API-Version", "2")
        val entity = HttpEntity<String>(headers)
        val response = testRestTemplate.exchange("http://localhost:$port/greeting", HttpMethod.GET, entity, String::class.java)
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isEqualTo("Hello, welcome to the API v2!")
    }
}