package com.baeldung.execjar

import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ApplicationLiveTest {

    @Test
    fun when_queried_on_root_then_joke_works() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                val content: String? = response.content
                assertNotNull(content)
                assertTrue(content.isNotEmpty())
            }
        }
    }
}