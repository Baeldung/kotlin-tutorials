package com.baeldung.instantiation

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.primaryConstructor
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ReflectiveInstantiationUnitTest {

    @Test
    fun `createInstance should work for no-arg constructors`() {
        val noArgInstance = NoArg::class.createInstance()

        assertNotNull(noArgInstance)
        assertThat(noArgInstance).isInstanceOf(NoArg::class.java)
    }

    @Test
    fun `createInstance should work for optional args constructors`() {
        val instance = OptionalArgs::class.createInstance()

        assertNotNull(instance)
        assertThat(instance).isInstanceOf(OptionalArgs::class.java)
    }

    @Test
    fun `createInstance should throw for required arg constructors`() {
        val exception = assertThrows<IllegalArgumentException> { RequiredArgs::class.createInstance() }

        assertThat(exception).hasMessageStartingWith("Class should have a single no-arg constructor")
    }

    @Test
    fun `should be able to create an instance reflectively for constructors with multiple args`() {
        val primaryConstructor = RequiredArgs::class.primaryConstructor
        val instance = primaryConstructor!!.call("first arg", "second arg")

        assertNotNull(instance)
        assertThat(instance).isInstanceOf(RequiredArgs::class.java)
    }

    @Test
    fun `should to able to create an instance with secondary constructors using reflection`() {
        val constructors = RequiredArgs::class.constructors
        val instance = constructors.first { it.parameters.size == 1 }.call("arg1")

        assertEquals("arg1", instance.arg1)
        assertEquals("default", instance.arg2)
    }
}

class NoArg
class OptionalArgs(val arg: String = "default")
class RequiredArgs(val arg1: String, val arg2: String) {
    constructor(arg1: String): this(arg1, "default")
}
