package com.baeldung.dataclass.deepcopy

data class Person(val firstName: String, var lastName: String) : Cloneable {
    public override fun clone(): Person = super.clone() as Person
}

data class Movie(val title: String, val year: Int, val director: Person, val actors: List<Person> = emptyList()) : Cloneable {
    public override fun clone() = Movie(title, year, director.clone(), actors.map { it.copy() })
}
