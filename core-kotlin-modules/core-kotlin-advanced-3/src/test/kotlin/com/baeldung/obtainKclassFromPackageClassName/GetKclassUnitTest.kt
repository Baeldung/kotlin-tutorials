package com.baeldung.obtainKclassFromPackageClassName

import io.github.classgraph.ClassGraph
import org.junit.jupiter.api.Test
import java.io.IOException
import kotlin.reflect.KClass
import kotlin.test.assertEquals

class GetKclassUnitTest {

    @Test
    fun `obtain the Kclass from Package Class name using forName method`() {
        val className = "com.baeldung.obtainKclassFromPackageClassName.ClassExample"
        val kClass = getClassUsingForName(className)

        assertEquals(ClassExample::class, kClass)
    }

    @Test
    fun `obtain the Kclass from Package Class name using class loader`() {
        val className = "com.baeldung.obtainKclassFromPackageClassName.ClassExample"
        val kClass = getClassFromLoader(className)

        assertEquals(ClassExample::class, kClass)
    }

    @Test
    fun `obtain the Kclass from Package Class name using class path`() {
        val className = "com.baeldung.obtainKclassFromPackageClassName.ClassExample"
        val kClass = getClassUsingClassPath(className)

        assertEquals(ClassExample::class, kClass)
    }
}

fun getClassUsingForName(className: String): KClass<*>? {
    return try {
        Class.forName(className).kotlin
    } catch (e: ClassNotFoundException) {
        null
    }
}

fun getClassFromLoader(className: String): KClass<*>? {
    return try {
        ClassLoader.getSystemClassLoader().loadClass(className).kotlin
    } catch (e: ClassNotFoundException) {
        null
    }
}

fun getClassUsingClassPath(className: String): KClass<*>? {
    return try {
        ClassGraph()
            .addClassLoader(ClassLoader.getSystemClassLoader())
            .enableClassInfo()
            .scan()
            .getClassInfo(className)
            .loadClass()
            ?.kotlin
    } catch (e: Exception) {
        null
    }
}