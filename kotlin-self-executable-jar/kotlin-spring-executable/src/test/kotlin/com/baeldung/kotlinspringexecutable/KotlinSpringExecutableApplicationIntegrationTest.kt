package com.baeldung.kotlinspringexecutable

import com.baeldung.kotlinspringexecutable.web.RouterConfiguration
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient


@WebFluxTest
@ContextConfiguration(classes = [RouterConfiguration::class])
class KotlinSpringExecutableApplicationIntegrationTest {
    @Autowired
    lateinit var context: ApplicationContext
    lateinit var webTestClient: WebTestClient

    @BeforeEach
    fun setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(context).build()
    }

    @Test
    fun when_queried_on_root_then_joke_method_works() {
        webTestClient.get()
            .uri("/")
            .exchange()
            .expectStatus().isOk
            .expectBody(String::class.java)
            .value {
                assertNotNull(it)
                assertTrue(it.isNotEmpty())
            }
    }

}
