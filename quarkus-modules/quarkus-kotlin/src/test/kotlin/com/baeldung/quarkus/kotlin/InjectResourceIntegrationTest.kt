package com.baeldung.quarkus.kotlin

import io.quarkus.test.junit.QuarkusIntegrationTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled("KTLN-887")
@QuarkusIntegrationTest
class InjectResourceIntegrationTest {
    @Test
    fun `when getting the inject resource then we got the correct response`() {
        given()
            .`when`().get("/inject")
            .then()
            .statusCode(200)
            .body(`is`("Hello, World!"))
    }
}