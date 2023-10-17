package com.baeldung.graphql.client

import com.baeldung.graphql.client.generated.ConferenceByIdQuery
import com.baeldung.graphql.client.generated.ObjectByIdQuery
import com.baeldung.graphql.client.generated.SaveOrCreateAttendeeMutation
import com.baeldung.graphql.client.generated.SaveOrCreateConferenceMutation
import com.baeldung.graphql.client.generated.conferencebyidquery.Conference
import com.baeldung.graphql.client.generated.objectbyidquery.ObjectWithId
import com.baeldung.graphql.client.generated.saveorcreateattendeemutation.Attendee
import com.expediagroup.graphql.client.jackson.GraphQLClientJacksonSerializer
import com.expediagroup.graphql.client.ktor.GraphQLKtorClient
import java.net.URI

private val GRAPHQL_URL = URI.create("http://localhost:80/graphql").toURL()

object GraphQLService {

    private val client = GraphQLKtorClient(url = GRAPHQL_URL, serializer = GraphQLClientJacksonSerializer())

    suspend fun findConferenceById(id: Int): Conference? {
        val resp = client.execute(
            ConferenceByIdQuery(ConferenceByIdQuery.Variables(id))
        )
        return resp.data?.conferenceById
    }

    suspend fun createOrSaveConference(id: Int?, name: String): com.baeldung.graphql.client.generated.saveorcreateconferencemutation.Conference? {
        val resp = client.execute(
            SaveOrCreateConferenceMutation(SaveOrCreateConferenceMutation.Variables(id, name, listOf()))
        )
        return resp.data?.saveOrCreateConference
    }

    suspend fun createOrSaveAttendee(id: Int?, name: String): Attendee? {
        val resp = client.execute(
            SaveOrCreateAttendeeMutation(SaveOrCreateAttendeeMutation.Variables(id, name))
        )
        return resp.data?.saveOrCreateAttendee
    }

    suspend fun getObjectById(id: Int): ObjectWithId? {
        val resp = client.execute(
            ObjectByIdQuery(ObjectByIdQuery.Variables(id))
        )
        return resp.data?.objectById
    }

}