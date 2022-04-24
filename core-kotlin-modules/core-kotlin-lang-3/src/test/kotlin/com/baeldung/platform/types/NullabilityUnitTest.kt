package com.baeldung.platform.types

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith


class NullabilityUnitTest {

    @Test
    fun givenNullable_whenCall_thenFail() {
        val client = Client()
        assertFailsWith(NullPointerException::class) {
            client.name.length
        }
    }

    @Test
    fun givenNullable_whenCall_thenNotFail() {
        val client = Client()
        val name: String? = client.name
        assertThat(name?.length).isNull()
    }
}
