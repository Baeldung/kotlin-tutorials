package com.baeldung.implicitAndQualifiedthis


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.awt.Point

class ImplicitAndQualifiedthisUnitTest {

    @Test
    fun testInvokePrintLineWithThis() {

        fun printLine() = "Top-level function"

        class Outer {

            fun cekThis(){
                val b = this
                assertEquals(Outer::class.java.name, b::class.java.name)
            }

            inner class Inner {

                fun Int.foo() {

                    assertEquals(42, this)
                    assertEquals(42, this@foo)
                    assertEquals(this, this@foo)
                    assertEquals(Outer::class.java.name, this@Outer::class.java.name)
                    assertEquals(Inner::class.java.name, this@Inner::class.java.name)

                    val funLit = fun String.() {
                        assertEquals("test.funLit()", this)
                    }

                    "test.funLit()".funLit()

                    val funLitNoReceiver = { s: String ->
                        assertEquals("test funLitNoReceiver", s)
                        assertEquals(42, this)
                    }

                    funLitNoReceiver("test funLitNoReceiver")
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

        assertEquals("Member function", Outer().invokePrintLine())
        assertEquals("Top-level function", Outer().invokePrintLine(omitThis = true))
    }
}
