package com.baeldung.dataclass.deepcopy

import kotlinx.serialization.Serializable

@Serializable
data class Person(val firstName: String, var lastName: String) : Cloneable {
    public override fun clone() = super.clone()
}

@Serializable
data class Movie(val title: String, val year: Int, val director: Person, val actors: List<Person> = emptyList()) : Cloneable {
    public override fun clone() = Movie(title, year, director.clone() as Person, actors.map { it.copy() })
}
