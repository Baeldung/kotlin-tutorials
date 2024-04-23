package com.baeldung.kotlin.kotlinpoet

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.TypeSpec
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FileUnitTest {
    @Test
    fun fullFile() {
        val code = FileSpec.builder("com.baeldung.kotlin.kotlinpoet", "Testing.kt")
            .addType(TypeSpec.classBuilder("Testing")
                .addFunction(FunSpec.builder("count")
                    .returns(Int::class)
                    .addParameter(ParameterSpec.builder("items", List::class).build())
                    .addStatement("return items.size()")
                    .build())
                .build())
            .build()

        assertEquals("""|package com.baeldung.kotlin.kotlinpoet
            |
            |import kotlin.Int
            |import kotlin.collections.List
            |
            |public class Testing {
            |  public fun count(items: List): Int = items.size()
            |}
            |""".trimMargin(), code.toString())
    }
}