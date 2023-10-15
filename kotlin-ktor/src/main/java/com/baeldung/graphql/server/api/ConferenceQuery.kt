package com.baeldung.graphql.server.api

import com.baeldung.graphql.server.data.AttendeeRepository
import com.baeldung.graphql.server.data.Conference
import com.baeldung.graphql.server.data.ConferenceRepository
import com.baeldung.graphql.server.data.ObjectWithId
import com.expediagroup.graphql.server.operations.Query

class ConferenceQuery : Query {
    fun conferenceById(id: Int): Conference? {
        return ConferenceRepository.findById(id)
    }

    fun objectById(id: Int): ObjectWithId? {
        return ConferenceRepository.findById(id) ?: AttendeeRepository.findById(id)
    }
}