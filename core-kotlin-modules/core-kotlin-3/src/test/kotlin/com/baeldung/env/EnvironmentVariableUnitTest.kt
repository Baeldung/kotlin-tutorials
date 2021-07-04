package com.baeldung.env

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class EnvironmentVariableUnitTest {

    @Test
    fun `getenv reads the env var`() {
        val env = System.getenv("HOME")
        assertNotNull(env)
        assertNull(System.getenv("INVALID_ENV_NAME"))
    }

    @Test
    fun `getenv without params will return`() {
        val allEnvs = System.getenv()
        allEnvs.forEach { (k, v) -> println("$k => $v") }

        assertThat(allEnvs).isNotEmpty
    }
}