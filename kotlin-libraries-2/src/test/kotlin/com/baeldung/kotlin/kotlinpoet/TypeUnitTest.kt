package com.baeldung.kotlin.kotlinpoet

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TypeUnitTest {
    @Test
    fun emptyClass() {
        val code = TypeSpec.classBuilder("Test")
            .build()

        assertEquals("""|public class Test
            |""".trimMargin(), code.toString())
    }

    @Test
    fun methods() {
        val code = TypeSpec.classBuilder("Test")
            .addFunction(FunSpec.builder("doSomething")
                .returns(Int::class)
                .addParameter("input", String::class)
                .addModifiers(KModifier.PRIVATE)
                .build())
            .build()

        assertEquals("""|public class Test {
            |  private fun doSomething(input: kotlin.String): kotlin.Int {
            |  }
            |}
            |""".trimMargin(), code.toString())
    }

    @Test
    fun properties() {
        val code = TypeSpec.classBuilder("Test")
            .addProperty(PropertySpec.builder("test", String::class)
                .addModifiers(KModifier.PRIVATE)
                .mutable()
                .initializer("%S", "Hello")
                .build())
            .build()

        assertEquals("""|public class Test {
            |  private var test: kotlin.String = "Hello"
            |}
            |""".trimMargin(), code.toString())
    }
}