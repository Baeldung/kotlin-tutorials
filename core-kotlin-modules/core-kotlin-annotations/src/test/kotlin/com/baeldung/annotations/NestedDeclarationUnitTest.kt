package com.baeldung.annotations

import org.junit.jupiter.api.Test
import kotlin.reflect.full.findAnnotation
import kotlin.test.assertEquals

class NestedDeclarationUnitTest {


    @Test
    fun `given nested declarations in annotation, when check for parent-level member, should get expected result`() {
        val parentAnnotation = ClassUsingNestedAnnotation::class.findAnnotation<Parent>()
        assertEquals(Parent.Type.TYPE1, parentAnnotation?.type)
    }

    @Test
    fun `given nested declarations in annotation, when check for child1 annotation, should get expected result`() {
        val child1Annotation = ClassUsingNestedAnnotation::class.findAnnotation<Parent.Child1>()
        assertEquals("sample prop", child1Annotation?.prop1)
    }

    @Test
    fun `given nested declarations in annotation, when check for child2 annotation, should get expected result`() {
        val child2Annotation = ClassUsingNestedAnnotation::class.findAnnotation<Parent.Child2>()
        assertEquals(1, child2Annotation?.prop2)
    }
}