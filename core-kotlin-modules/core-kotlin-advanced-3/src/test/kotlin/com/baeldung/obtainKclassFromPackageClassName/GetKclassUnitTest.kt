package com.baeldung.obtainKclassFromPackageClassName

import io.github.classgraph.ClassGraph
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.IOException
import kotlin.reflect.KClass
import kotlin.test.assertEquals
import kotlin.test.assertNull

class GetKclassUnitTest {

    @Test
    fun `obtain the Kclass from Package Class name using forName method`() {
        val className = "com.baeldung.obtainKclassFromPackageClassName.ClassExample"

        val kClass = getClassUsingForName(className)

        assertEquals(ClassExample::class, kClass)
    }

    @Test
    fun `obtain the Kclass from Package Class name using forName method and non-existing class`() {
        val notClass = "com.baeldung.obtainKclassFromPackageClassName.NotAClass"

        val exception = assertThrows<ClassNotFoundException> {
            getClassUsingForName(notClass)
        }

        assertEquals("com.baeldung.obtainKclassFromPackageClassName.NotAClass", exception.message)

    }

    @Test
    fun `obtain the Kclass from Package Class name using class loader`() {
        val className = "com.baeldung.obtainKclassFromPackageClassName.ClassExample"

        val kClass = getClassFromLoader(className)

        assertEquals(ClassExample::class, kClass)
    }

    @Test
    fun `obtain the Kclass from Package Class name using class loader and non-existing class`() {
        val notClass = "com.baeldung.obtainKclassFromPackageClassName.NotAClass"

        val exception = assertThrows<ClassNotFoundException> {
            getClassFromLoader(notClass)
        }

        assertEquals("com.baeldung.obtainKclassFromPackageClassName.NotAClass", exception.message)
    }

    @Test
    fun `obtain the Kclass from Package Class name using class path`() {
        val className = "com.baeldung.obtainKclassFromPackageClassName.ClassExample"
        val kClass = getClassUsingClassGraph(className)

        assertEquals(ClassExample::class, kClass)
    }

    @Test
    fun `obtain the Kclass from Package Class name using class path and non-existing class`() {
        val notClass = "com.baeldung.obtainKclassFromPackageClassName.NotAClass"

        val exception = assertThrows<NullPointerException> {
            getClassUsingClassGraph(notClass)
        }

        assertEquals("Cannot invoke \"io.github.classgraph.ClassInfo.loadClass()\" because the return value of \"io.github.classgraph.ScanResult.getClassInfo(String)\" is null", exception.message)
    }
}

fun getClassUsingForName(className: String): KClass<*>? {
    return Class.forName(className).kotlin
}

fun getClassFromLoader(className: String): KClass<*>? {
    return ClassLoader.getSystemClassLoader().loadClass(className).kotlin
}

fun getClassUsingClassGraph(className: String): KClass<*>? {
        return ClassGraph()
            .addClassLoader(ClassLoader.getSystemClassLoader())
            .enableClassInfo()
            .scan()
            .getClassInfo(className)
            .loadClass()
            ?.kotlin
}