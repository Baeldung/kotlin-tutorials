package com.baeldung.dataclass.deepcopy

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotSame
import kotlin.test.assertSame

class DeepCopyUnitTest {

    @Test
    fun givenSimpleObject_whenCopyCalled_thenStructurallyEqualAndDifferentReference() {
        val person = Person("First name", "Last name")

        val personCopy = person.copy()

        assertNotSame(person, personCopy)
        assertEquals(person, personCopy)
    }

    @Test
    fun givenComplexObject_whenCopyCalled_thenStructurallyEqualAndDifferentReferenceAndEqualInternalReferences() {
        val movie = Movie("Avatar 2", 2022, Person("James", "Cameron"))

        val movieCopy = movie.copy()

        assertNotSame(movieCopy, movie)
        assertEquals(movieCopy, movie)
        assertSame(movieCopy.director, movie.director)

        movie.director.lastName = "Brown"
        assertEquals(movieCopy.director.lastName, movie.director.lastName)
    }

    @Test
    fun givenComplexObject_whenCustomCopyCalled_thenStructurallyEqualAndDifferentReferenceAndDifferentInternalReferences() {
        val movie = Movie("Avatar 2", 2022, Person("James", "Cameron"))

        val movieCopy = movie.copy(director = movie.director.copy())

        assertNotSame(movieCopy, movie)
        assertEquals(movieCopy, movie)
        assertNotSame(movieCopy.director, movie.director)

        movie.director.lastName = "Brown"
        assertNotEquals(movieCopy.director.lastName, movie.director.lastName)
    }

    @Test
    fun givenComplexObject_whenCloneCalled_thenStructurallyEqualAndDifferentReferenceAndDifferentInternalReferences() {
        val movie = Movie("Avatar 2", 2022, Person("James", "Cameron"))

        val movieCopy = movie.clone()

        assertNotSame(movieCopy, movie)
        assertEquals(movieCopy, movie)
        assertNotSame(movieCopy.director, movie.director)

        movie.director.lastName = "Brown"
        assertNotEquals(movieCopy.director.lastName, movie.director.lastName)
    }
}