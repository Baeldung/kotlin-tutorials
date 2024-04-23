package com.baeldung.kotlin.kotlinpoet

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FunctionUnitTest {
    @Test
    fun emptyFunction() {
        val code = FunSpec.builder("test")
            .build()

        assertEquals(
            """|public fun test() {
               |}
               |""".trimMargin(),
            code.toString())
    }

    @Test
    fun simpleBody() {
        val code = FunSpec.builder("test")
            .addStatement("println(\"Testing\")")
            .build()

        assertEquals(
            """|public fun test() {
               |  println("Testing")
               |}
               |""".trimMargin(),
            code.toString())
    }

    @Test
    fun simpleBodyFormatSpecifier() {
        val code = FunSpec.builder("test")
            .addStatement("println(%S)", "Testing")
            .build()

        assertEquals(
            """|public fun test() {
               |  println("Testing")
               |}
               |""".trimMargin(),
            code.toString())
    }

    @Test
    fun controlFlow() {
        val code = FunSpec.builder("test")
            .beginControlFlow("if (showOutput)")
            .addStatement("println(%S)", "Testing")
            .nextControlFlow("else")
            .addStatement("println()")
            .endControlFlow()
            .build()

        assertEquals(
            """|public fun test() {
                |  if (showOutput) {
                |    println("Testing")
                |  } else {
                |    println()
                |  }
                |}
                |""".trimMargin(),
            code.toString())
    }

    @Test
    fun parameters() {
        val code = FunSpec.builder("test")
            .addParameter(ParameterSpec.builder("first", String::class).build())
            .addParameter(ParameterSpec.builder("second", Int::class).defaultValue("%L", 42).build())
            .build()

        assertEquals(
            """|public fun test(first: kotlin.String, second: kotlin.Int = 42) {
               |}
               |""".trimMargin(),
            code.toString())
    }

    @Test
    fun returns() {
        val code = FunSpec.builder("test")
            .returns(String::class)
            .build()

        assertEquals(
            """|public fun test(): kotlin.String {
               |}
               |""".trimMargin(),
            code.toString())
    }

    @Test
    fun singleExpression() {
        val code = FunSpec.builder("test")
            .returns(Int::class)
            .addStatement("return 5")
            .build()

        assertEquals(
            """|public fun test(): kotlin.Int = 5
               |""".trimMargin(),
            code.toString())
    }
}
