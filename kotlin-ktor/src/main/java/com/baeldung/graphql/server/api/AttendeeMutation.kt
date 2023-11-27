package com.baeldung.graphql.server.api

import com.baeldung.graphql.server.data.Attendee
import com.baeldung.graphql.server.data.AttendeeRepository
import com.expediagroup.graphql.server.operations.Mutation

class AttendeeMutation : Mutation {
    fun saveOrCreateAttendee(attendee: Attendee): Attendee {
        return AttendeeRepository.save(attendee)
    }
}