package com.baeldung.graphql.client

import com.baeldung.graphql.server.data.AttendeeRepository
import com.baeldung.graphql.server.data.ConferenceRepository
import com.baeldung.graphql.server.graphQLModule
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.BeforeClass
import kotlin.test.*

class GraphQLClientIntegrationTest {

    private val graphQLClient = GraphQLClient("http://0.0.0.0:8080/graphql")

    // https://stackoverflow.com/questions/61245981/how-can-i-start-ktor-server-programmatically-for-integration-tests
    // Since regular Ktor tests work with a dummy application with no server, we can't actually test the client properly without starting it
    // So we start it up and tear it down for every test.
    companion object {

        private lateinit var server: NettyApplicationEngine

        @BeforeClass
        @JvmStatic
        fun setup() {
            val env = applicationEngineEnvironment {
                module {
                    graphQLModule()
                }
                connector {
                    host = "0.0.0.0"
                    port = 8080
                }
            }
            server = embeddedServer(Netty, env).start(false)
        }

        @AfterClass
        @JvmStatic
        fun teardown() {
            // clean up after this class, leave nothing dirty behind
            server.stop(1000, 10000)
        }
    }

    @BeforeTest
    fun cleanUp() {
        ConferenceRepository.clear()
        AttendeeRepository.clear()
    }

    private suspend fun createDummyConference(attendees: List<Int> = listOf()) =
        graphQLClient.createOrSaveConference(null, "Some Conference", attendees)

    private suspend fun createDummyAttendee() =
        graphQLClient.createOrSaveAttendee(null, "Some Attendee")

    @Test
    fun `given conference by id when fetching existing conference then should fetch it`(): Unit = runBlocking {
        createDummyConference()
        val fetchedConference = graphQLClient.findConferenceById(0)
        assertEquals(0, fetchedConference?.id)
        assertEquals("Some Conference", fetchedConference?.name)
    }

    @Test
    fun `given conference by id when fetching not existing conference then should return null`(): Unit = runBlocking {
        val fetchedConference = graphQLClient.findConferenceById(0)
        assertNull(fetchedConference)
    }

    @Test
    fun `given create or save conference when saving not existing conference then should create it`(): Unit = runBlocking {
        val returnedConference = graphQLClient.createOrSaveConference(null, "Some Conference")
        assertEquals(0, returnedConference?.id)
        val createdConference = ConferenceRepository.findById(0)
        assertEquals("Some Conference", createdConference?.name)
    }

    @Test
    fun `given create or save conference when saving existing conference then should update it`(): Unit = runBlocking {
        createDummyConference()
        graphQLClient.createOrSaveConference(0, "Same Conference")
        val updatedConference = ConferenceRepository.findById(0)
        assertEquals("Same Conference", updatedConference?.name)
    }

    @Test
    fun `given create or save attendee when saving not existing attendee then should create it`(): Unit = runBlocking {
        val returnedAttendee = graphQLClient.createOrSaveAttendee(null, "Some Attendee")
        assertEquals(0, returnedAttendee?.id)
        val createdAttendee = AttendeeRepository.findById(0)
        assertEquals("Some Attendee", createdAttendee?.name)
    }

    @Test
    fun `given create or save attendee when saving existing attendee then should update it`(): Unit = runBlocking {
        createDummyAttendee()
        graphQLClient.createOrSaveAttendee(0, "Same Attendee")
        val updatedAttendee = AttendeeRepository.findById(0)
        assertEquals("Same Attendee", updatedAttendee?.name)
    }

    @Test
    fun `given get object by id when fetching conference then should return it`(): Unit = runBlocking {
        createDummyConference()
        val objectById = graphQLClient.getObjectById(0)
        assertIs<com.baeldung.graphql.client.generated.objectbyidquery.Conference>(objectById)
    }

    @Test
    fun `given get object by id when fetching attendee then should return it`(): Unit = runBlocking {
        createDummyAttendee()
        val objectById = graphQLClient.getObjectById(0)
        assertIs<com.baeldung.graphql.client.generated.objectbyidquery.Attendee2>(objectById)
    }

    @Test
    fun `given get object by id when fetching not existing object then should return null`(): Unit = runBlocking {
        assertNull(graphQLClient.getObjectById(0))
    }

    @Test
    fun `given conference with attendees when fetching conference with limit one then should return conference with one attendee object`(): Unit = runBlocking {
        createDummyAttendee()
        createDummyAttendee()
        createDummyConference(listOf(0, 1))
        val fetchedConference = graphQLClient.findConferenceById(id = 0, attendeeLimit = 1)
        assertEquals(1, fetchedConference?.attendeeObjects?.size)
    }

    @Test
    fun `given conference batch when fetching two conferences then should return both`() = runBlocking {
        createDummyConference()
        createDummyConference()
        val conferences = graphQLClient.getConferenceBatch(firstId = 0, secondId = 1)
        assertEquals(2, conferences.size)
        assertTrue { conferences.any { it.id == 0 } }
        assertTrue { conferences.any { it.id == 1 } }
    }
}