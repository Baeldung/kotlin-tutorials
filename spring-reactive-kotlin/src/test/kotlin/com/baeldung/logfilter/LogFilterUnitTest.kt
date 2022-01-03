package com.baeldung.logfilter

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient


@WebFluxTest(controllers = [Controller::class])
open class LogFilterUnitTest {
    @MockBean
    lateinit var log: Logger

    @Autowired
    lateinit var client: WebTestClient

    @BeforeEach
    fun setup() {
        `when`(log.isDebugEnabled).thenReturn(true)
    }

    @Test
    fun whenGet_thenLogResponseBody() {
        client.get()
            .uri("/get")
            .exchange()
            .expectStatus().isOk
        verify(log).debug("{}: {}", "response", "[1,2,3]")
    }

    @Test
    fun whenPost_thenLogRequestAndResponseBody() {
        client.post()
            .uri("/post")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(Controller.IntRequest(1))
            .exchange()
            .expectStatus().isOk
        verify(log).debug("{}: {}", "request", "{\"input\":1}")
        verify(log).debug("{}: {}", "response", "[1,2,3]")
    }
}
