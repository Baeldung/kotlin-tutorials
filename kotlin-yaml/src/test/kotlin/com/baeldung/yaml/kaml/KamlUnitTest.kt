package com.baeldung.yaml.kaml

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class KamlUnitTest {

    @Test
    fun test1() {
        val deployment = getDeployment()
        // println(deployment.age)
        assertEquals(30, deployment.users.get(0).age)
    }
}