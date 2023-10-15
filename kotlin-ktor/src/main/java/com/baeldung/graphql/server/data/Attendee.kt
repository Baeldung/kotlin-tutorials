package com.baeldung.graphql.server.data

data class Attendee(override var id: Int?, var name: String): ObjectWithId
