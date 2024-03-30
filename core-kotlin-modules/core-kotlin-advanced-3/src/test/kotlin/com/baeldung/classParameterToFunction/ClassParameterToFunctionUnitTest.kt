package com.baeldung.classParameterToFunction

import org.junit.jupiter.api.Test
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.test.assertEquals

class ClassParameterToFunctionUnitTest {

    @Test
    fun `passes class as function parameter using class reference`(){
        val result = functionAcceptingClassReference(ParameterClass::class.java)

        assertEquals("This is a method called on our class", result)
    }
    @Test
    fun `passes class as function parameter using kclass reference`(){
        val result = functionAcceptingKClassReference(ParameterClass::class)

        assertEquals("This is a method called on our class", result)
    }

    @Test
    fun `passes class as function parameter using reflection`(){
        val result = functionAcceptingClassNameUsingReflection<ParameterClass>("com.baeldung.classParameterToFunction.ParameterClass")

        assertEquals("This is a method called on our class", result)
    }

    @Test
    fun `passes class as function parameter using reified parameters`(){
        val result = functionAcceptingClassNameUsingReifiedParameters<ParameterClass>()

        assertEquals("This is a method called on our class", result)
    }
}

fun <T: ParameterClass> functionAcceptingClassReference(clazz: Class<T>): String {
    val instance = clazz.newInstance()
    return instance.parameterClassMethod()
}

fun <T: ParameterClass> functionAcceptingKClassReference(clazz: KClass<T>): String {
    val instance = clazz.createInstance()

    return instance.parameterClassMethod()
}
fun <T : ParameterClass> functionAcceptingClassNameUsingReflection(className: String): String {
    val clazz = Class.forName(className) as Class<T>
    val instance = clazz.newInstance()

    return instance.parameterClassMethod()
}

inline fun <reified T: ParameterClass> functionAcceptingClassNameUsingReifiedParameters(): String {
    val instance = T::class.java.newInstance()

    return instance.parameterClassMethod()
}

class ParameterClass{

    fun parameterClassMethod(): String{
        return "This is a method called on our class"
    }
}