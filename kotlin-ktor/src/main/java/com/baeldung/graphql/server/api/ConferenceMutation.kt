package com.baeldung.graphql.server.api

import com.baeldung.graphql.server.data.Conference
import com.baeldung.graphql.server.data.ConferenceRepository
import com.expediagroup.graphql.server.operations.Mutation

class ConferenceMutation : Mutation {
    fun saveOrCreateConference(conference: Conference): Conference {
        return ConferenceRepository.save(conference)
    }
}