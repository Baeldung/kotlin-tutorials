package com.baeldung.graphql.server

import com.baeldung.graphql.OBJECT_BY_ID_TEST_QUERY
import com.baeldung.graphql.graphQlTestEnvironment
import com.baeldung.graphql.readResponseAsMap
import com.baeldung.graphql.serializeQuery
import com.baeldung.graphql.server.data.Conference
import com.baeldung.graphql.server.data.ConferenceRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.websocket.*
import junit.framework.TestCase.assertEquals
import kotlin.test.*

private const val CREATE_CONFERENCE_QUERY = """
    mutation {
        saveOrCreateConference(conference: { attendees: [ ], name: "My Conference" }) {
            id
            name
            attendees
      }
    }
    """

private const val UPDATE_CONFERENCE_QUERY = """
    mutation {
        saveOrCreateConference(conference: { id: 0, attendees: [ ], name: "My Conference" }) {
            id
            name
            attendees
      }
    }
    """

private const val FIND_BY_ID_QUERY = """
    {
        conferenceById(id: 0) {
            name
            id
        }
    }
    """

class ConferenceTest {

    @BeforeTest
    fun cleanUp() {
        ConferenceRepository.clear()
    }

    @Test
    fun `given no conference when fetching by id then return null`(): Unit = graphQlTestEnvironment { client ->
        val mockBody = serializeQuery(
            FIND_BY_ID_QUERY
        )
        val call = client.post("graphql") {
            header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(mockBody)
        }
        with(call) {
            assertEquals(HttpStatusCode.OK, status)
            val response = readResponseAsMap(this)
            assertNull((response["data"] as Map<*, *>)["conferenceById"])
        }
    }

    @Test
    fun `given one conference when fetching by id then return it`(): Unit = graphQlTestEnvironment { client ->
        ConferenceRepository.save(Conference(id = null, name = "Conference", attendees = listOf()))
        val mockBody = serializeQuery(
            FIND_BY_ID_QUERY
        )
        val call = client.post("graphql") {
            header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(mockBody)
        }
        with(call) {
            assertEquals(HttpStatusCode.OK, status)
            val response = jacksonObjectMapper().readValue(bodyAsText(), Map::class.java)
            assertNotNull((response["data"] as Map<*, *>)["conferenceById"])
        }
    }

    @Test
    fun `given one conference when fetching object by id then return it`(): Unit = graphQlTestEnvironment { client ->
        ConferenceRepository.save(Conference(id = null, name = "Conference", attendees = listOf()))
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

    @Test
    fun `when creating a conference via mutations then should succeed`(): Unit = graphQlTestEnvironment { client ->
        val mockBody = serializeQuery(
            CREATE_CONFERENCE_QUERY
        )
        val call = client.post("graphql") {
            header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(mockBody)
        }
        with(call) {
            assertEquals(HttpStatusCode.OK, status)
            val response = jacksonObjectMapper().readValue(bodyAsText(), Map::class.java)
            assertNotNull((response["data"] as Map<*, *>)["saveOrCreateConference"])
            assertEquals("My Conference", ConferenceRepository.findById(0)?.name)
        }
    }

    @Test
    fun `when updating a conference via mutations then should succeed`(): Unit = graphQlTestEnvironment { client ->
        ConferenceRepository.save(Conference(null, "Some Conference", listOf()))
        val mockBody = serializeQuery(
            UPDATE_CONFERENCE_QUERY
        )
        val call = client.post("graphql") {
            header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(mockBody)
        }
        with(call) {
            assertEquals(HttpStatusCode.OK, status)
            val response = jacksonObjectMapper().readValue(bodyAsText(), Map::class.java)
            assertNotNull((response["data"] as Map<*, *>)["saveOrCreateConference"])
            assertEquals("My Conference", ConferenceRepository.findById(0)?.name)
        }
    }

    @Test
    fun `when creating a conference then should trigger websocket subscription`(): Unit = graphQlTestEnvironment { client ->
        val mockBody = serializeQuery(
            CREATE_CONFERENCE_QUERY
        )
        client.post("graphql") {
            header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(mockBody)
        }
        client.webSocket({
            url("/subscriptions")
            headers[HttpHeaders.SecWebSocketProtocol] = "graphql-transport-ws"
        }) {
            outgoing.send(Frame.Text("""{"type": "connection_init"}"""))
            assertEquals("""{"type":"connection_ack"}""", (incoming.receive() as Frame.Text).readText())
            outgoing.send(Frame.Text("""{"type": "subscribe", "id": "unique-id", "payload": { "query": "subscription { conferenceId }" }}"""))
            assertEquals("""{"id":"unique-id","payload":{"data":{"conferenceId":0}},"type":"next"}""", (incoming.receive() as? Frame.Text)?.readText())
        }
    }

}