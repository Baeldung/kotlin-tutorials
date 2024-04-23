package com.baeldung.yaml.yamlkt

import com.baeldung.yaml.verifyUsers
import kotlinx.serialization.decodeFromString
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import net.mamoe.yamlkt.Yaml
import net.mamoe.yamlkt.YamlMap
import net.mamoe.yamlkt.YamlNull
import net.mamoe.yamlkt.getPrimitive

class YamlktUnitTest {

    private val yamlDefault = net.mamoe.yamlkt.Yaml.Default

    @Test
    fun `Return string when parsing scalar string element in a yaml string using decodeFromString`() {
        val scalarStringYamlStr = "Hello!"
        val parsedData: String = yamlDefault.decodeFromString<String>(scalarStringYamlStr)
        assertEquals("Hello!", parsedData)
    }

    @Test
    fun `Return number when parsing scalar number in a yaml string using decodeFromString`() {
        val scalarIntYamlStr = "123"
        val parsedData: Int = yamlDefault.decodeFromString<Int>(scalarIntYamlStr)
        assertEquals(123, parsedData)
    }

    @Test
    fun `Return float when parsing scalar float number in a yaml string using decodeFromString`() {
        val scalarFloatYamlStr = "12.3"
        val parsedData: Float = yamlDefault.decodeFromString<Float>(scalarFloatYamlStr)
        assertEquals(12.3f, parsedData)
    }

    @Test
    fun `Return float when parsing scalar scientific notation float number in a yaml string using decodeFromString`() {
        val scalarScientificNotationFloatYamlStr = "12.3e1"
        val parsedData: Float = yamlDefault.decodeFromString<Float>(scalarScientificNotationFloatYamlStr)
        assertEquals(123.0f, parsedData)
    }

    @Test
    fun `Return false when parsing boolean scalar with false value in a yaml string using decodeFromString`() {
        val falseBooleanYamlStr = "false"
        val parsedData: Boolean? = yamlDefault.decodeFromString(falseBooleanYamlStr)
        assertEquals(false, parsedData)
    }

    @Test
    fun `Return true when parsing boolean scalar with true value in a yaml string using decodeFromString`() {
        val trueBooleanYamlStr = "true"
        val parsedData: Boolean? = yamlDefault.decodeFromString(trueBooleanYamlStr)
        assertEquals(true, parsedData)
    }

    @Test
    fun `Return null when parsing tilde in a yaml string using decodeFromString`() {
        val tildeYamlStr = "~"
        val parsedData: Any? = yamlDefault.decodeFromString(tildeYamlStr)
        assertEquals(null, parsedData)
    }

    @Test
    fun `Return number list when parsing number list in a yaml string using decodeListFromString`() {
        val numberListYamlStr =
            """
              - 123
              - 456
            """
        val parsedData: List<Any?> = yamlDefault.decodeListFromString(numberListYamlStr)
        assertEquals(listOf(123, 456), parsedData)
    }

    @Test
    fun `Return number list when parsing flow-style list of numbers in a yaml string using decodeListFromString`() {
        val numberListYamlStr = "[123,456]"
        val parsedData: List<Any?> = yamlDefault.decodeListFromString(numberListYamlStr)
        assertEquals(listOf(123, 456), parsedData)
    }

    @Test
    fun `Return strings when parsing strings list in a yaml string using decodeListFromString`() {
        val stringListYamlStr =
            """
              - v1
              - v2
            """
        val parsedData: List<Any?> = yamlDefault.decodeListFromString(stringListYamlStr)
        assertEquals(listOf("v1", "v2"), parsedData)
    }

    @Test
    fun `Return strings when parsing flow-style list of strings in a yaml string using decodeListFromString`() {
        val stringListYamlStr = "[v1,v2]"
        val parsedData: List<Any?> = yamlDefault.decodeListFromString(stringListYamlStr)
        assertEquals(listOf("v1", "v2"), parsedData)
    }

    @Test
    fun `Return floats when parsing list of floats in a yaml string using decodeFromString`() {
        val stringListYamlStr =
            """
              - 12.3
              - 23.7e1
            """
        val parsedData: List<Float?> = yamlDefault.decodeFromString(stringListYamlStr)
        assertEquals(listOf(12.3f, 237.0f), parsedData)
    }

    @Test
    fun `Return floats when parsing flow-style list of floats in a yaml string using decodeFromString`() {
        val stringListYamlStr = "[12.3,23.7e1]"
        val parsedData: List<Float?> = yamlDefault.decodeFromString<List<Float>>(stringListYamlStr)
        assertEquals(listOf(12.3f, 237.0f), parsedData)
    }

    @Test
    fun `Return booleans when parsing list of boolean values in a yaml string using decodeFromString`() {
        val stringListYamlStr =
            """
              - true
              - false
              - true
              - true
            """
        val parsedData: List<Boolean?> = yamlDefault.decodeFromString<List<Boolean>>(stringListYamlStr)
        assertEquals(listOf(true, false, true, true), parsedData)
    }

    @Test
    fun `Return map when parsing map with complex objects in a yaml string using decodeMapFromString`() {
        val yamlMap =
            """
            k1: v1
            k2: v2
            k3: v3
            k4:
              - l1
              - l2
              - l3
            k5:
              k11: v11
              k22: v22
            """
        val parsedData: Map<String?, Any?> = yamlDefault.decodeMapFromString(yamlMap)
        assertEquals("v1", parsedData["k1"])
        assertEquals("v2", parsedData["k2"])
        assertEquals("v3", parsedData["k3"])
        assertEquals(listOf("l1", "l2", "l3"), parsedData["k4"])
        assertEquals(mapOf("k11" to "v11", "k22" to "v22"), parsedData["k5"])
    }

    @Test
    fun `Return map when parsing map object in a yaml string using decodeMapFromString`() {
        val mapWithScalarValuesYamlStr =
            """
            k1: v1
            k2: 123
            k3: false
            k4: 12.3
            k5: 1.2e1
            k6: ~
            """
        val parsedData: Map<String?, Any?> = yamlDefault.decodeMapFromString(mapWithScalarValuesYamlStr)
        assertEquals("v1", parsedData["k1"])
        assertEquals(123, parsedData["k2"])
        assertEquals(false, parsedData["k3"])
        assertEquals(12.3, parsedData["k4"])
        assertEquals(12.0, parsedData["k5"])
        assertEquals(null, parsedData["k6"])
    }

    @Test
    fun `Return map when parsing map object in a yaml string using decodeFromString`() {
        val mapWithScalarValuesYamlStr =
            """
            k1: v1
            k2: 123
            k3: false
            k4: 12.3
            k5: 1.2e1
            k6: ~
            """
        val parsedData: YamlMap = yamlDefault.decodeFromString(mapWithScalarValuesYamlStr)
        assertEquals("v1", parsedData.getString("k1"))
        assertEquals(123, parsedData.getInt("k2"))
        assertEquals(false, parsedData.getPrimitive("k3"))
        assertEquals(12.3f, parsedData.getFloat("k4"))
        assertEquals(12.0f, parsedData.getFloat("k5"))
        assertTrue(parsedData["k6"] is YamlNull)
    }


    @Test
    fun `Return map when parsing map object with complex value objects in a yaml string using decodeFromString`() {
        val yamlMap =
            """
            k1:
              - v1
              - v2
            k2:
              - 123
              - 456
            k3:
              - false
              - true
              - false
              - true
            k4:
              - 12.3
              - 1.2e1
              - 43.3
            k5:
              - ~
              - null
            """
        val parsedData: Map<String?, Any?> = yamlDefault.decodeMapFromString(yamlMap)
        assertEquals(listOf("v1", "v2"), parsedData["k1"])
        assertEquals(listOf(123, 456), parsedData["k2"])
        assertEquals(listOf(false, true, false, true), parsedData["k3"])
        assertEquals(listOf(12.3, 12.0, 43.3), parsedData["k4"])
        assertEquals(listOf(null, null), parsedData["k5"])
    }

    @Test
    fun `Return map when parsing map object with flow-style list objects in a yaml string using decodeFromString`() {
        val yamlMap =
            """
            k1: [v1, v2]
            k2: [123, 456]
            k3: [false, true, false, true]
            k4: [12.3, 1.2e1, 43.3]
            """

        val parsedData: Map<String?, Any?> = yamlDefault.decodeMapFromString(yamlMap)
        assertEquals(listOf("v1", "v2"), parsedData["k1"])
        assertEquals(listOf(123, 456), parsedData["k2"])
        assertEquals(listOf(false, true, false, true), parsedData["k3"])
        assertEquals(listOf(12.3, 12.0, 43.3), parsedData["k4"])
    }

    @Test
    fun `Return users when parsing yaml string using Users serializer`() {
        val fileContent = readYaml("/users.yaml")
        val users = getUsersUsingUsersSerializer(fileContent)
        verifyUsers(users)
    }

    @Test
    fun `Return users when parsing yaml string using YamlMap serializer`() {
        val fileContent = readYaml("/users.yaml")
        val users = getUsersUsingYamlMapSerializer(fileContent)
        verifyUsers(users)
    }

    private fun readYaml(path: String): String {
        return YamlktUnitTest::class.java.getResourceAsStream(path)!!.bufferedReader().readText().trimIndent()
    }
}
