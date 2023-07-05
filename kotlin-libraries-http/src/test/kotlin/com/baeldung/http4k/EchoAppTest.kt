package com.baeldung.http4k

import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EchoAppTest {

    private val testPayload = "hello"

    @Test
    fun whenEchoHandlerCalledWithHelloMessage_thenStatusOk() {
        val expectedResponse = Response(OK).body(testPayload)
        assertEquals(expectedResponse, echoHandler(Request(POST, "/").body(testPayload)))
    }

    @Test
    fun whenAppHandlerCalledOnTheRightEndpoint_thenCorrect() {
        val expectedResponse = Response(OK).body(testPayload)
        val appResponse = app(Request(POST, "/echo").body(testPayload))

        assertEquals(expectedResponse, appResponse)
        assertEquals(OK, appResponse.status)
    }

    @Test
    fun whenAppHandlerCalledWOnTheWrongEndpoint_thenNotFound() {
        val expectedResponse = Response(OK).body(testPayload)
        val appResponse = app(Request(POST, "/").body(testPayload))
        assertNotEquals(expectedResponse, appResponse)
        assertEquals(NOT_FOUND, appResponse.status)
    }

}