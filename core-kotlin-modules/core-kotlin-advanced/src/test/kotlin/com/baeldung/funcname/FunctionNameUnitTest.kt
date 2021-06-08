package com.baeldung.funcname

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FunctionNameUnitTest {

    @Test
    fun `should be able to get the function name using anonymous inner classes`() {
        val name = functionNameWithAnonymousInnerClass()

        assertEquals("functionNameWithAnonymousInnerClass", name)
    }

    @Test
    fun `should be able to get the function name using stack traces`() {
        val name = functionNameWithStackTraces()

        assertEquals("functionNameWithStackTraces", name)
    }
}

// Java 9+ only
/*
fun functionNameWithStackWalker(): String? {
    return StackWalker.getInstance().walk { frames ->
        frames.findFirst().map { it.methodName }.orElse(null)
    }
}
*/

fun functionNameWithAnonymousInnerClass(): String {
    return object {}.javaClass.enclosingMethod.name
}

fun functionNameWithStackTraces(): String {
    return Thread.currentThread().stackTrace[1].methodName
}
