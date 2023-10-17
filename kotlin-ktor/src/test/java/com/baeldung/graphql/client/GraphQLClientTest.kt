package com.baeldung.graphql.client

import com.baeldung.graphql.graphQlTestEnvironment
import com.baeldung.graphql.server.data.Attendee
import com.baeldung.graphql.server.data.AttendeeRepository
import com.baeldung.graphql.server.data.Conference
import com.baeldung.graphql.server.data.ConferenceRepository
import kotlin.test.*

class GraphQLClientTest {

    @BeforeTest
    fun cleanUp() {
        AttendeeRepository.clear()
    }

    private fun createDummyConference() {
        ConferenceRepository.save(Conference(null, "Some Conference", listOf()))
    }

    private suspend fun createDummyAttendee() {
        GraphQLService.createOrSaveAttendee(null, "Some Attendee")
    }

    @Test
    fun `given conference by id when fetching existing conference then should fetch it`() = graphQlTestEnvironment { _ ->
        createDummyConference()
        val fetchedConference = GraphQLService.findConferenceById(0)
        assertEquals(0, fetchedConference?.id)
        assertEquals("Some Conference", fetchedConference?.name)
    }

    @Test
    fun `given conference by id when fetching not existing conference then should return null`() = graphQlTestEnvironment { _ ->
        val fetchedConference = GraphQLService.findConferenceById(0)
        assertNull(fetchedConference)
    }

    @Test
    fun `given create or save conference when saving not existing conference then should create it`() = graphQlTestEnvironment { _ ->
        GraphQLService.createOrSaveConference(null, "Some Conference")
        val createdConference = ConferenceRepository.findById(0)
        assertEquals("Some Conference", createdConference?.name)
    }

    @Test
    fun `given create or save conference when saving existing conference then should update it`() = graphQlTestEnvironment { _ ->
        createDummyConference()
        GraphQLService.createOrSaveConference(0, "Same Conference")
        val updatedConference = ConferenceRepository.findById(0)
        assertEquals("Same Conference", updatedConference?.name)
    }

    @Test
    fun `given create or save attendee when saving not existing attendee then should create it`() = graphQlTestEnvironment { _ ->
        createDummyAttendee()
        val createdAttendee = AttendeeRepository.findById(0)
        assertEquals("Some Attendee", createdAttendee?.name)
    }

    @Test
    fun `given create or save attendee when saving existing attendee then should update it`() = graphQlTestEnvironment { _ ->
        AttendeeRepository.save(Attendee(null, "Some Attendee"))
        GraphQLService.createOrSaveAttendee(0, "Same Attendee")
        val updatedAttendee = AttendeeRepository.findById(0)
        assertEquals("Same Attendee", updatedAttendee?.name)
    }

    @Test
    fun `given get object by id when fetching conference then should return it`() = graphQlTestEnvironment { client ->
        createDummyConference()
        val objectById = GraphQLService.getObjectById(0)
        assertIs<com.baeldung.graphql.client.generated.objectbyidquery.Conference>(objectById)
    }

    @Test
    fun `given get object by id when fetching attendee then should return it`() = graphQlTestEnvironment { _ ->
        createDummyAttendee()
        val objectById = GraphQLService.getObjectById(0)
        assertIs<com.baeldung.graphql.client.generated.objectbyidquery.Attendee2>(objectById)
    }

    @Test
    fun `given get object by id when fetching not existing object then should return null`() = graphQlTestEnvironment { _ ->
        assertNull(GraphQLService.getObjectById(0))
    }
}