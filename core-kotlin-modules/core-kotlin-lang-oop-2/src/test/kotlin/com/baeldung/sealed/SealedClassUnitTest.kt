package com.baeldung.sealed

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.reflect.KClass

class SealedClassUnitTest {

    @Test
    fun `sealedSubclasses should return all subclasses`() {
        val subclasses: List<KClass<*>> = Expr::class.sealedSubclasses

        assertThat(subclasses).hasSize(4)
        assertThat(subclasses).containsExactlyInAnyOrder(
          ForExpr::class, IfExpr::class, WhenExpr::class, DeclarationExpr::class
        )
    }
}
