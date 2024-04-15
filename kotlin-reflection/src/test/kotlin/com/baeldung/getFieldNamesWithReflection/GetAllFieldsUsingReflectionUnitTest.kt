package com.baeldung.getFieldNamesWithReflection

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties

open class Human(val gender: String)

abstract class Identifiable(open val id: Int)
class TestClass(val name: String, val age: Int, override val id: Int): Identifiable(id)

class Person(val name: String, val age: Int): Human("male") {
}

class GetAllFieldsUsingReflectionUnitTest {

    @Test
    fun `obtain all field names using declaredMemberProperties method`() {
        val fieldNames =  getAllFieldNamesUsingDeclaredMemberPropertiesMethod(TestClass::class)
        assertEquals(listOf("age","id", "name"), fieldNames)
    }

    @Test
    fun `obtain all field names using member method`() {
        val fieldNames = getAllFieldNamesUsingMembersProperty(TestClass::class)
        assertEquals(listOf("age","id", "name"), fieldNames)
    }

    @Test
    fun `obtain all field names using java reflection API`() {
        val fieldNames = getAllFieldNamesUsingJavaReflection(TestClass::class)
        assertEquals(listOf("name", "age", "id"), fieldNames)
    }

    @Test
    fun `obtain all field names using Class members property `() {
        val fieldNames = getAllFieldNamesUsingClassMemberProperties(TestClass::class)
        assertEquals(listOf("age","id", "name"), fieldNames)
    }
    @Test
    fun `Show difference between declaredMemberProperties and memberProperties property`() {
        val declaredProperties = getAllFieldNamesUsingDeclaredMemberPropertiesMethod(Person::class)
        val allProperties = getAllFieldNamesUsingClassMemberProperties(Person::class)

        assertEquals(listOf("age", "name"), declaredProperties)
        assertEquals(listOf("age", "name", "gender"), allProperties)
    }
}

fun getAllFieldNamesUsingDeclaredMemberPropertiesMethod(clazz: KClass<*>): List<String> {
    return clazz.declaredMemberProperties
        .map { it.name }
}
fun getAllFieldNamesUsingJavaReflection(clazz: KClass<*>): List<String> {
    return clazz.java.declaredFields
        .map { it.name }
}

fun getAllFieldNamesUsingMembersProperty(clazz: KClass<*>): List<String> {
    return clazz.members
        .filter { it is KProperty<*> }
        .map { it.name }
}
fun getAllFieldNamesUsingClassMemberProperties(clazz: KClass<*>): List<String> {
    return clazz.memberProperties
        .map { it.name }
}