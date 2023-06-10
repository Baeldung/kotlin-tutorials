package dataclassvsobject

data class Person(val name: String, val age: String)

open class Employee(val name: String, val department: String) {

    open fun greeting() = "Hi! My name is $name. I work in $department department"
}

object PersonManager {
    private val personList = mutableListOf<Person>()

    fun addPerson(person: Person) {
        personList.add(person)
    }

    fun removePerson(person: Person) {
        personList.remove(person)
    }

    fun getAllPersons(): List<Person> {
        return personList.toList()
    }
}