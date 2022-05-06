package com.baeldung.exceptionhandling

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters

@RunWith(SpringRunner::class)
@SpringBootTest(
    classes = [ExceptionHandlingSrpingApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class ExceptionHandlingApplicationTest {

    @Autowired
    private lateinit var client: WebTestClient

    @Test
    fun givenArticleTitle_whenCreateArticleWithSameTitle_thenBadRequestShouldBeReturned() {

        client.post()
            .uri { uriBuilder ->
                uriBuilder
                    .path("/articles")
                    .build()
            }.body(BodyInserters.fromObject("Exception Handling in Kotlin"))
            .exchange()
            .expectStatus()
            .isBadRequest
    }

    @Test
    fun givenArticleId_whenGetNonExistingArticle_thenNotFoundErrorShouldBeReturned() {

        client.get()
            .uri { uriBuilder ->
                uriBuilder
                    .path("/articles")
                    .queryParam("id", "5")
                    .build()
            }
            .exchange()
            .expectStatus()
            .isNotFound
    }

    @Test
    fun givenArticle_whenUpdateArticleWithLongTitle_thenBadRequestShouldBeReturned() {

        client.put()
            .uri { uriBuilder ->
                uriBuilder
                    .path("/articles")
                    .queryParam("id", "1")
                    .queryParam(
                        "title",
                        "Title to long. Title to long. Title to long. Title to long. Title to long. Title to long. Title to long. Title to long. Title to long. Title to long. Title to long. Title to long. Title to long. Title to long."
                    )
                    .build()
            }
            .exchange()
            .expectStatus()
            .isBadRequest
    }
}
