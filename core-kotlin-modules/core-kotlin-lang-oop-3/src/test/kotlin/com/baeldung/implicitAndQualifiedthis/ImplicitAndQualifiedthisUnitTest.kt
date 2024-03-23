package com.baeldung.implicitAndQualifiedthis


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.awt.Point

class ImplicitAndQualifiedthisUnitTest {

    @Test
    fun testInvokePrintLineWithThis() {

        fun printLine() = "Top-level function"

        class Outer { // implicit label @Outer

            fun cekThis(){
                val b = this
                assertEquals(Outer::class.java.name, b::class.java.name)
            }

            inner class Inner { // implicit label @Inner

                // in Extension Function:
                fun Int.foo() { // implicit label @foo

                    assertEquals(42, this)
                    assertEquals(42, this@foo)
                    assertEquals(this, this@foo)
                    assertEquals(Outer::class.java.name, this@Outer::class.java.name)
                    assertEquals(Inner::class.java.name, this@Inner::class.java.name)

                    // in Anonymous Function
                    val funLit = lambda@ fun String.() {
                        assertEquals("test.funLit()", this) // funLit's receiver, a String
                        assertEquals("test.funLit()", this@lambda) // funLit's receiver, a String
                    }

                    "test.funLit()".funLit()

                    // in lambda expressions
                    val funLit2 = { s: String ->
                        // foo()'s receiver, since enclosing lambda expression
                        // doesn't have any receiver
                        assertEquals("test funLit2", s)
                        assertEquals(42, this)
                    }

                    funLit2("test funLit2")

                }
            }

            fun printLine() = "Member function"

            fun invokePrintLine(omitThis: Boolean = false): String {
                return if (omitThis) printLine() else this.printLine()
            }
        }

        val inner = Outer().Inner()
        inner.run { 42.foo() }

        Outer().cekThis()

        assertEquals("Member function", Outer().invokePrintLine()) // Member function
        assertEquals("Top-level function", Outer().invokePrintLine(omitThis = true)) // Top-level function

        class Person(val name: String) {
            fun printName() : String {
                assertEquals("Hangga Aji Sayekti", this.name)
                assertEquals(Person::class.java.name, this::class.java.name)
                return this.name
            }
        }

        val person = Person("Hangga Aji Sayekti")
        person.printName()
    }
}
