package com.baeldung.quarkus.kotlin

import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Test
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`

@QuarkusTest
class GreetingResourceUnitTest {
    @Test
    fun `when getting the hello resource then we got the correct response`() {
        given()
            .`when`().get("/hello")
            .then()
            .statusCode(200)
            .body("greeting", `is`("Hello, World!"))
    }
}