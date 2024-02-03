package com.baeldung.equalsandhashcode

import java.util.Objects
data class PersonWithUniqueName(val name: String, val age: Int, val address: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val otherPerson: PersonWithUniqueName = other as PersonWithUniqueName
        return otherPerson.name == name
    }

    override fun hashCode(): Int {
        return Objects.hash(name)
    }
}