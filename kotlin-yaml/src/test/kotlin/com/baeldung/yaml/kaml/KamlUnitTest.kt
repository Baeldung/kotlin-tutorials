package com.baeldung.yaml.kaml

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class KamlUnitTest {

    @Test
    fun test1() {
        val deployment = getDeployment()
        println(deployment.apiVersion)
        assertEquals("apps/v1", deployment.apiVersion)
    }
}