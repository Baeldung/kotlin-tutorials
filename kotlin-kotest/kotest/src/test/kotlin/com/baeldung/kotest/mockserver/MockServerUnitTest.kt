package com.baeldung.kotest.mockserver

import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.mockserver.MockServerListener
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClients
import org.mockserver.client.MockServerClient
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse

class MockServerUnitTest : FunSpec({
    val mockServerListener = MockServerListener(1080)
    listener(mockServerListener)

    beforeTest {
        MockServerClient("localhost", 1080).`when`(
            HttpRequest.request()
                .withMethod("POST")
                .withPath("/login")
                .withHeader("Content-Type", "application/json")
                .withBody("""{"username": "foo", "password": "bar"}""")
        ).respond(HttpResponse.response().withStatusCode(202))
    }


    test("Should return 202 status code") {
        val httpPost = HttpPost("http://localhost:1080/login").apply {
            entity = StringEntity("""{"username": "foo", "password": "bar"}""")
            setHeader("Content-Type", "application/json")
        }

        val response = HttpClients.createDefault().use { it.execute(httpPost) }
        val statusCode = response.statusLine.statusCode
        statusCode shouldBe 202
    }

    afterTest {
        mockServerListener.close()
    }
})

class UnspecifiedHttpRequestUnitTest : FunSpec({
    val mockServerListener = MockServerListener(1080);
    listener(mockServerListener)

    val client = MockServerClient("localhost", 1080)

    beforeTest {
        client.`when`(
            HttpRequest.request()
        ).respond(
            HttpResponse.response().withStatusCode(202)
        )
    }

    test("Should make a post with correct content") {
        val httpPost = HttpPost("http://localhost:1080/login").apply {
            entity = StringEntity("""{"username": "foo", "password": "bar"}""")
            setHeader("Content-Type", "application/json")
        }

        HttpClients.createDefault().use { it.execute(httpPost) }

        val request = client.retrieveRecordedRequests(null).first()

        request.getHeader("Content-Type") shouldContain "application/json"
        request.bodyAsJsonOrXmlString.replace("\r\n", "\n") shouldBe """{
            |  "username" : "foo",
            |  "password" : "bar"
            |}""".trimMargin()
        request.path.value shouldBe "/login"
        request.method.value shouldBe "POST"
    }

    afterTest {
        mockServerListener.close()
    }
})