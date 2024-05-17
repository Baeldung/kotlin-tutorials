package com.baeldung.iterate.components

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.reflect.full.*

class ReflectionIterationTests {
    @Test
    fun `Get data class members for Person`() {
        val person = Person("Daniel", 37)

        person::class.members.forEach {
            println(it.name)
        }

        assertThat(person::class.members)
            .extracting("name")
            .contains("age", "name", "isAdult", "currentSalary", "employeeId", "salary")

        person::class.declaredMembers.forEach {
            println(it.name)
        }

        assertThat(person::class.declaredMembers)
            .extracting("name")
            .contains("age", "isAdult", "name")
    }

    @Test
    fun `Get data class member properties for Person`() {
        val person = Person("Daniel", 25)

        assertThat(person::class.memberProperties)
            .extracting("name")
            .contains("age", "isAdult", "name", "employeeId", "salary")

        assertThat(person::class.memberExtensionProperties)
            .extracting("name")
            .containsOnly("isTeenager", "isSenior")
    }

    @Test
    fun `Get data class declared member properties for Person`() {
        val person = Person("Daniel", 37)

        assertThat(person::class.declaredMemberProperties)
            .extracting("name")
            .containsOnly("age", "isAdult", "name")

        assertThat(person::class.declaredMemberExtensionProperties)
            .extracting("name")
            .containsOnly("isTeenager")
    }

    @Test
    fun `Get Java class static properties`() {
        val circle = Circle()

        assertThat(Circle::class.staticProperties)
            .extracting("name")
            .containsOnly("PI")
    }

    @Test
    fun `Get data class member functions for Person`() {
        val person = Person("Daniel", 37)

        assertThat(person::class.memberFunctions)
            .extracting("name")
            .contains("component1", "component2", "copy", "equals", "hashCode", "toString")

        assertThat(person::class.memberExtensionFunctions)
            .extracting("name")
            .containsOnly("isRetired", "isPromoted")
    }

    @Test
    fun `Get data class declared member functions for Person`() {
        val person = Person("Daniel", 25)

        assertThat(person::class.declaredMemberFunctions)
            .extracting("name")
            .contains("component1", "component2", "copy", "equals", "hashCode", "toString")

        assertThat(person::class.declaredMemberExtensionFunctions)
            .extracting("name")
            .containsOnly("isRetired")
    }

    @Test
    fun `Get data class static functions for Person`() {
        val circle = Circle()

        assertThat(circle::class.staticFunctions)
            .extracting("name")
            .contains("calculateArea")
    }

    @Test
    fun `Get data class companion object for Person`() {
        val person = Person("Daniel", 37)

        assertThat(person::class.companionObject)
            .isNotNull
            .extracting("simpleName")
            .isEqualTo("Create")
    }
}