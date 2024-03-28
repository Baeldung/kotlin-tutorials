package com.baeldung.yaml.yamlkt

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class YamlktUnitTest {
    
    @Test
    fun test1() {
        val fileContent = YamlktUnitTest::class.java.getResourceAsStream("/deployment.yaml").bufferedReader().use { it.readText() }.trimIndent()
         val deployment = getDeployment2(fileContent)
        println(deployment.users.get(0).age)
        assertEquals(30, deployment.users.get(0).age)
    }

    @Test
    fun test2() {
        val fileContent = YamlktUnitTest::class.java.getResourceAsStream("/deployment.yaml").bufferedReader().use { it.readText() }.trimIndent()
        val deployment = getDeployment(fileContent)
        println(deployment.users.get(0).age)
        assertEquals(30, deployment.users.get(0).age)
    }
}