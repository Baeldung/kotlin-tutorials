package com.baeldung.atrium

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.infix.en_GB.because
import ch.tutteli.atrium.api.infix.en_GB.toBeAnInstanceOf
import ch.tutteli.atrium.api.infix.en_GB.toBeTheInstance
import ch.tutteli.atrium.api.infix.en_GB.toMatch
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.api.verbs.expectGrouped
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.reporting.AtriumError
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class FluentAssertionUnitTest {
    @Test
    fun equalsSuccess() {
        val value = "Hello"

        expect(value).toEqual("Hello")
    }

    @Test
    @Disabled
    fun equalsFailure() {
        val value = "Hello"

        expect(value).toEqual("Hello, World!")
    }

    @Test
    @Disabled
    fun chainedFailure() {
        val value = -3
        expect(value).toBeGreaterThan(0).toBeLessThan(5)
    }

    @Test
    @Disabled
    fun multipleFailures() {
        val value = "Wrong"
        expect(value) {
            toEqual("Wrong")
            toStartWith("Hello")
            toEndWith("World")
        }
    }

    @Test
    @Disabled
    fun equalsFailureWithReason() {
        val value = "Hello"

        expect(value).because("that's the value we expected" ) {
            toEqual("Hello, World!")
        }
    }

    @Test
    @Disabled
    fun chainedPropertyExtraction() {
        data class User(val username: String, val displayName: String) {
            fun isEnabled() = true
        }

        val user = User("baeldung", "Baeldung")

        expect(user)
            .its(User::username) { toEqual("Username") }
            .its(User::displayName) { toEqual("Display Name") }
            .its(User::isEnabled) { toEqual(true) }
    }

    @Test
    @Disabled
    fun multiplePropertyExtraction() {
        data class User(val username: String, val displayName: String) {
            fun isEnabled() = true

        }

        val user = User("baeldung", "Baeldung")

        expect(user) {
            its(User::username) { toEqual("Username") }
            its(User::displayName) { toEqual("Display Name") }
            its(User::isEnabled) { toEqual(true) }
        }
    }

    @Test
    @Disabled
    fun multipleFeatureExtraction() {
        data class User(val username: String, val displayName: String) {
            fun isEnabled() = true
        }



        val user = User("baeldung", "Baeldung")

        expect(user) {
            feature({f(it::username)}) { toEqual("Username") }
            feature({f(it::displayName)}) { toEqual("Display Name") }
            feature({f(it::isEnabled)}) { toEqual(true) }
        }
    }

    @Test
    @Disabled
    fun typeAssertions() {
        open class Animal(val name: String)
        class Dog(name: String, val breed: String) : Animal(name)
        class Cat(name: String, val color: String) : Animal(name)

        val animal = Dog("Lassie", "Rough collie")

        expect(animal).toBeAnInstanceOf<Dog> {
            its(Animal::name).toEqual("Lassie")
            its(Dog::breed).toEqual("Pomeranian")
        }
    }

    @Test
    @Disabled
    fun groupedAssertions() {
        expectGrouped {
            group("First group") {
                expect("Hello").toEqual("World")
                expect("Hello").toEqual("Again")
            }
            group("Second group") {
                expect("Hello").toEqual("World")
            }
        }
    }

    @Test
    @Disabled
    fun dataDrivenTest() {
        expectGrouped {
            for (i in 1..3) {
                group("Group ${i}") {
                    expect(i).toBeLessThan(2)
                }
            }
        }
    }

    @Test
    @Disabled
    fun customAssertion() {
        expectGrouped {
            group("toBeMultipleOf") {
                expect(3).toBeAMultipleOf(2)
            }

            group("toBeBetween") {
                expect(3).toBeBetween(5, 10)
                expect(3).toBeBetween(0, 2)
            }

            group("User") {
                data class User(val username: String, val displayName: String) {
                    fun isEnabled() = true
                }

                fun Expect<User>.toHaveUsername(username: String) = its(User::username) { toEqual(username) }

                val user = User("baeldung", "Baeldung")
                expect(user).toHaveUsername("Other")
            }
        }
    }
}

fun Expect<Int>.toBeAMultipleOf(base: Int) =
    _logic.createAndAppend("to be a multiple of", base) { it % base == 0 }

fun Expect<Int>.toBeBetween(low: Int, high: Int) = and {
    toBeGreaterThan(low)
    toBeLessThan(high)
}