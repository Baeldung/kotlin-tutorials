package com.baeldung.dataclasses

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DataClassUnitTest {
    @Test
    fun when_copying_with_params_then_a_different_object_is_created() {
        val person = Person(29, "Jane", "Doe")
        val olderPerson = person.copy(age = 30)
        assertEquals(person.firstName, olderPerson.firstName)
        assertEquals(person.lastName, olderPerson.lastName)
        assertEquals(30, olderPerson.age)
    }

    @Test
    fun when_field_is_an_object_then_copying_has_to_supply_a_new_one() {
        val car = Car(registrationPlate = "ISZ-151", owner = Person(29, "Jane", "Doe"))
        val soldCar = car.copy(owner = Person(29, "Mary", "Sue"))
    }

    @Test
    fun when_no_arg_constructor_is_called_then_default_values_are_used() {
        val person = Person()
        assertEquals(0, person.age)
        assertEquals("Jonny", person.firstName)
    }

    @Test
    fun when_component_functions_are_called_directly_then_properties_in_order_of_declaration_are_returned() {
        val person = Person(18, "Mary", "Sue")
        assertEquals(18, person.component1())
        assertEquals("Sue", person.component3())
    }

    @Test
    fun when_working_with_collection_then_destructuring_is_handy() {
        val students = listOf(
          Person(14, "Harry", "Panther"),
          Person(14, "Hermione", "Grinder"),
          Person(15, "Rowan", "Wisel"),
          Person(14, "Dean", "Sean"),
          Person(15, "Neville", "Longhorn")
        )
        val nameList = students.joinToString("\n") { (_, name, surname) -> "$name $surname" }
        assertEquals(
          "Harry Panther\n" +
            "Hermione Grinder\n" +
            "Rowan Wisel\n" +
            "Dean Sean\n" +
            "Neville Longhorn", nameList
        )
    }
}