package com.baeldung.graphql.server.data

import java.io.Serializable

data class Conference(override var id: Int?, var name: String, var attendees: List<Int>): ObjectWithId, Serializable {

    fun attendeeObjects(limit: Int?): List<Attendee> {
        val attendeeObjects = attendees.mapNotNull { AttendeeRepository.findById(it) }
        if (limit == null) {
            return attendeeObjects
        }
        return attendeeObjects.take(limit)
    }

}