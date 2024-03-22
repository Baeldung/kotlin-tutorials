package com.baeldung.implicitAndQualifiedthis


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ImplicitAndQualifiedthisUnitTest {

    @Test
    fun testInvokePrintLineWithThis() {

        fun printLine() = "Top-level function"
        class Outer { // implicit label @Outer
            inner class Inner { // implicit label @Inner
                fun Int.foo() { // implicit label @foo
                    val a = this@Outer // Outer's this
                    val b = this@Inner // Inner's this

                    assertEquals(Outer::class.java.name, a.javaClass.name)
                    assertEquals(Inner::class.java.name, b.javaClass.name)

                    val c = this // foo()'s receiver, an Int
                    val c1 = this@foo // foo()'s receiver, an Int

                    assertEquals(42, c)
                    assertEquals(42, c1)

                    val funLit = lambda@ fun String.() {
                        val d = this // funLit's receiver, a String

                        assertEquals("test.funLit()", d)
                    }

                    "test.funLit()".funLit()

                    val funLit2 = { s: String ->
                        // foo()'s receiver, since enclosing lambda expression
                        // doesn't have any receiver
                        val d1 = this

                        assertEquals("test funLit2", s)
                        assertEquals(42, d1)
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

        assertEquals("Member function", Outer().invokePrintLine()) // Member function
        assertEquals("Top-level function", Outer().invokePrintLine(omitThis = true)) // Top-level function
    }
}
