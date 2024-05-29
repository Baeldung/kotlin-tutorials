package com.baeldung.iterate.components

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReflectionInnerObjectsTests {
    @Test
    fun `Get inner data class for Person`() {
        val person = Person("Daniel", 37)

        assertThat(person::class.nestedClasses)
            .extracting("simpleName")
            .contains("Job", "Address")
    }

    @Test
    fun `Use iterator operator from Person`() {
        val person = Person("Daniel", 37)
        val properties = person.components().asSequence().toList()

        assertThat(properties)
            .extracting("first")
            .containsOnly("name", "age", "isAdult", "isTeenager", "isRetired")
    }
}