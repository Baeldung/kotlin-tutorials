package com.baeldung.classParameterToFunction

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ClassParameterToFunctionUnitTest {

    @Test
    fun `passes class as function parameter using class reference`(){
        val result = functionAcceptingClassReference(ParameterClass::class.java)

        assertEquals("This is a method called on our class", result)
    }

    @Test
    fun `passes class as function parameter using reflection`(){
        val result = functionAcceptingClassNameUsingReflection<String>("com.baeldung.classParameterToFunction.ParameterClass")

        assertEquals("This is a method called on our class", result)
    }

    @Test
    fun `passes class as function parameter using reified parameters`(){
        val result = functionAcceptingClassNameUsingReifiedParameters<ParameterClass>()

        assertEquals("This is a method called on our class", result)
    }
}

fun <T> functionAcceptingClassReference(clazz: Class<T>): String {
    val instance = clazz.newInstance() as ParameterClass

    return instance.paramterClassMethod()
}

fun <T : Any> functionAcceptingClassNameUsingReflection(className: String): String {
    val clazz = Class.forName(className) as Class<T>
    val instance = clazz.newInstance() as ParameterClass

    return instance.paramterClassMethod()
}

inline fun <reified T> functionAcceptingClassNameUsingReifiedParameters(): String {
    val instance = T::class.java.newInstance() as ParameterClass

    return instance.paramterClassMethod()
}

class ParameterClass{

    fun paramterClassMethod(): String{
        return "This is a method called on our class"
    }
}