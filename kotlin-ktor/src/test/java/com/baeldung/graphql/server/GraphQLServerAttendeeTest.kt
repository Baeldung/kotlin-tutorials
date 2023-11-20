package com.baeldung.graphql.server

import com.baeldung.graphql.OBJECT_BY_ID_TEST_QUERY
import com.baeldung.graphql.graphQlTestEnvironment
import com.baeldung.graphql.serializeQuery
import com.baeldung.graphql.server.data.Attendee
import com.baeldung.graphql.server.data.AttendeeRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import junit.framework.TestCase.assertEquals
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull

private const val CREATE_ATTENDEE_QUERY = """
    mutation {
        saveOrCreateAttendee(attendee: { name: "John Johnson" }) {
            id
            name
      }
    }
    """

private const val UPDATE_ATTENDEE_QUERY = """
    mutation {
        saveOrCreateAttendee(attendee: { id: 0, name: "Jake Jakeson" }) {
            id
            name
      }
    }
    """

class GraphQLServerAttendeeTest {

    @BeforeTest
    fun cleanUp() {
        AttendeeRepository.clear()
    }

    @Test
    fun `when creating an attendee via mutations then should succeed`(): Unit = graphQlTestEnvironment { client ->
        val mockBody = serializeQuery(
            CREATE_ATTENDEE_QUERY
        )
        val call = client.post("graphql") {
            header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(mockBody)
        }
        with(call) {
            assertEquals(HttpStatusCode.OK, status)
            val response = jacksonObjectMapper().readValue(bodyAsText(), Map::class.java)
            assertNotNull((response["data"] as Map<*, *>)["saveOrCreateAttendee"])
            assertEquals("John Johnson", AttendeeRepository.findById(0)?.name)
        }
    }

    @Test
    fun `when updating an attendee via mutations then should succeed`(): Unit = graphQlTestEnvironment { client ->
        AttendeeRepository.save(Attendee(null, "John Johnson"))
        val mockBody = serializeQuery(
            UPDATE_ATTENDEE_QUERY
        )
        val call = client.post("graphql") {
            header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(mockBody)
        }
        with(call) {
            assertEquals(HttpStatusCode.OK, status)
            val response = jacksonObjectMapper().readValue(bodyAsText(), Map::class.java)
            assertNotNull((response["data"] as Map<*, *>)["saveOrCreateAttendee"])
            assertEquals("Jake Jakeson", AttendeeRepository.findById(0)?.name)
        }
    }

    @Test
    fun `given one attendee when fetching object by id then return it`(): Unit = graphQlTestEnvironment { client ->
        AttendeeRepository.save(Attendee(null, "John Johnson"))
        val mockBody = serializeQuery(
            OBJECT_BY_ID_TEST_QUERY
        )
        val call = client.post("graphql") {
            header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(mockBody)
        }
        with(call) {
            assertEquals(HttpStatusCode.OK, status)
            val response = jacksonObjectMapper().readValue(bodyAsText(), Map::class.java)
            assertNotNull((response["data"] as Map<*, *>)["objectById"])
        }
    }

}