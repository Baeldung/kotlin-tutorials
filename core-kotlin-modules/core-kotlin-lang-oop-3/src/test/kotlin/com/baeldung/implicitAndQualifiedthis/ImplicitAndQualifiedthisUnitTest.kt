package com.baeldung.implicitAndQualifiedthis


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ImplicitAndQualifiedthisUnitTest {

    @Test
    fun `Qualified this and implicit this Test`() {

        fun printLine() = "Top-level function"
        val x = 5

        class Outer {
            val x = 0
            val b = this
            fun foo() : Int = 30

            fun printLine() = "Member function"

            fun checkThis() {
                val b = this // Implicit: refers to the current instance of Outer
                assertEquals(Outer::class.java.name, b::class.java.name)

                assertEquals(1, this.x + 1) // Implicit: refers to the x property of the current Outer instance
                assertEquals(6, x + 1) // Implicit: refers to the x property of the current Outer instance

                assertEquals("Member function", this.printLine())
                assertEquals("Top-level function", printLine())
            }

            inner class Inner {

                val x = 1

                fun checkInner(){
                    val funLit = fun String.() {
                        assertEquals("test.funLit()", this) // Implicit: receiver of the function literal (the String itself)
                    }

                    "test.funLit()".funLit()

                    val funInnerLambda = { _: String ->
                        val r = this
                        assertEquals(Inner::class.java.name, r::class.java.name)
                    }

                    funInnerLambda("test inner lambda")
                }

                fun Int.foo() {
                    val x = 2
                    assertEquals(2, x)
                    assertEquals(1, this@Inner.x) // Qualified: specifies x of the Inner class
                    assertEquals(42, this) // Implicit: receiver of the extension function (the Int value 42)
                    assertEquals(42, this@foo) // Qualified: specifies the receiver of the extension function (42)

                    assertEquals(this, this@foo)  // Both this keywords refer to the same receiver (42)

                    assertEquals(Outer::class.java.name, this@Outer::class.java.name) // Qualified: Outer class
                    assertEquals(Inner::class.java.name, this@Inner::class.java.name) // Qualified: Inner class

                    assertEquals(0, this@Outer.x) // Qualified: Outer's x property
                    assertEquals(30, this@Outer.foo()) // Qualified: Outer's foo() function

                    val funLit = fun String.() {
                        assertEquals("test.funLit()", this) // Implicit: receiver of the function literal (the String itself)
                    }

                    "test.funLit()".funLit()

                    val funExtLambda = { _: String ->
                        val r = this
                        assertEquals("Int", r::class.simpleName) // Implicit: this inside a lambda with no receiver refers to the enclosing context (Int.foo())
                    }

                    funExtLambda("test funExtLambda")
                }
            }
        }

        val outer = Outer()
        outer.checkThis()
        outer.foo()

        val inner = outer.Inner()
        inner.checkInner()
        inner.run {
            42.foo() // Calls the extension function defined inside Inner
        }
    }
}


