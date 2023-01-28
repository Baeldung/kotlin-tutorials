package com.baeldung.reflection

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.reflect.full.*

annotation class BaeldungExample(val url: String)
annotation class NotTested(val reason: String)

@NotTested("We trust developers ^_*")
@BaeldungExample("https://www.baeldung.com")
class BaeldungClass

class AnnotationReflectionUnitTest {

    @Test
    fun `when a kotlin class has annotation, then we can access the expected annotation using findAnnotation()`() {
        val theClass = BaeldungClass::class

        val notTestedAnnotation = theClass.findAnnotation<NotTested>()
        assertNotNull(notTestedAnnotation)
        assertEquals("We trust developers ^_*", notTestedAnnotation!!.reason)
    }

    @Test
    fun `when a kotlin class has annotation, then we can get all annotations`() {
        val theClass = BaeldungClass::class
        val annotations = theClass.annotations
        assertEquals(2, annotations.size)
    }

    @Test
    fun `when a kotlin class has annotation, then we can access the expected annotation from the annotations property`() {
        val theClass = BaeldungClass::class
        val baeldungAnnotation = theClass.annotations.filterIsInstance<BaeldungExample>().first()
        assertNotNull(baeldungAnnotation)
        assertEquals("https://www.baeldung.com", baeldungAnnotation.url)
    }
}