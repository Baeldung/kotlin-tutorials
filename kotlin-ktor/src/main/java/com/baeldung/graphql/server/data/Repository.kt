package com.baeldung.graphql.server.data

import com.baeldung.graphql.server.api.ConferencePublisher

interface ObjectWithId {
    var id: Int?
}

// In memory repository for simplicity
open class InMemoryRepository<T: ObjectWithId> {

    private val objects: MutableMap<Int?, T> = HashMap()

    open fun save(obj: T): T {
        if (obj.id == null) {
            obj.id = objects.size
        }
        objects[obj.id] = obj
        return obj
    }

    fun findById(id: Int): T? = objects[id]

    fun clear() = objects.clear()
}

object ConferenceRepository: InMemoryRepository<Conference>() {
    override fun save(obj: Conference): Conference {
        val conference = super.save(obj)
        ConferencePublisher.publishConference(conference)
        return conference
    }
}

object AttendeeRepository: InMemoryRepository<Attendee>()