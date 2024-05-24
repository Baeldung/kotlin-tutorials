package com.baeldung.kotlin.kotlinpoet

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeSpec
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class KotlinPoetUnitTest {
    @Test
    fun generateEmptyClass() {
        val code = TypeSpec.classBuilder("Test")
            .addModifiers(KModifier.PROTECTED)
            .build()

        assertEquals("protected class Test\n", code.toString())
    }
}
