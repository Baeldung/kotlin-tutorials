package com.baeldung.obtainKclassFromPackageClassName

import io.github.classgraph.ClassGraph
import io.github.classgraph.ClassInfo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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

        val kClass = getClassUsingClassGraph(notClass)
        assertNull(kClass)
    }
}

fun getClassUsingForName(className: String): KClass<*>? {
    return Class.forName(className).kotlin
}

fun getClassFromLoader(className: String): KClass<*>? {
    return ClassLoader.getSystemClassLoader().loadClass(className).kotlin
}

fun getClassUsingClassGraph(className: String): KClass<*>? {
    val classInfo: ClassInfo? = ClassGraph()
        .addClassLoader(ClassLoader.getSystemClassLoader())
        .enableClassInfo()
        .scan()
        .getClassInfo(className)
    return classInfo?.loadClass()?.kotlin
}