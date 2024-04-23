package com.baeldung.yaml.kaml

import com.baeldung.yaml.verifyUsers
import com.baeldung.yaml.yamlkt.YamlktUnitTest
import com.charleskorn.kaml.*
import kotlinx.serialization.decodeFromString
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class KamlUnitTest {

    private val yamlDefault = com.charleskorn.kaml.Yaml.default

    @Test
    fun `Return string when parsing scalar string element in a yaml string using decodeFromString`() {
        val scalarStringYamlStr = "Hello!"
        val parsedData: String = yamlDefault.decodeFromString(scalarStringYamlStr)
        assertEquals("Hello!", parsedData)
    }

    @Test
    fun `Return string when parsing scalar string element in a yaml string using parseToYamlNode`() {
        val scalarStringYamlStr = "Hello!"
        val parsedData: YamlScalar = yamlDefault.parseToYamlNode(scalarStringYamlStr).yamlScalar
        assertEquals("Hello!", parsedData.content)
    }

    @Test
    fun `Return number when parsing scalar number in a yaml string using decodeFromString`() {
        val scalarIntYamlStr = "123"
        val parsedData: Int = yamlDefault.decodeFromString(scalarIntYamlStr)
        assertEquals(123, parsedData)
    }

    @Test
    fun `Return number when parsing scalar number in a yaml string using parseToYamlNode`() {
        val scalarIntYamlStr = "123"
        val parsedData: YamlScalar = yamlDefault.parseToYamlNode(scalarIntYamlStr).yamlScalar
        assertEquals(123, parsedData.toInt())
    }


    @Test
    fun `Return float when parsing scalar float number in a yaml string using decodeFromString`() {
        val scalarFloatYamlStr = "12.3"
        val parsedData: Float = yamlDefault.decodeFromString(scalarFloatYamlStr)
        assertEquals(12.3f, parsedData)
    }

    @Test
    fun `Return float when parsing scalar float number in a yaml string using parseToYamlNode`() {
        val scalarFloatYamlStr = "12.3"
        val parsedData: YamlScalar = yamlDefault.parseToYamlNode(scalarFloatYamlStr).yamlScalar
        assertEquals(12.3f, parsedData.toFloat())
    }

    @Test
    fun `Return float when parsing scalar scientific notation float number in a yaml string using decodeFromString`() {
        val scalarScientificNotationFloatYamlStr = "12.3e1"
        val parsedData: Float = yamlDefault.decodeFromString(scalarScientificNotationFloatYamlStr)
        assertEquals(123.0f, parsedData)
    }

    @Test
    fun `Return float when parsing scalar scientific notation float number in a yaml string using parseToYamlNode`() {
        val scalarScientificNotationFloatYamlStr = "12.3e1"
        val parsedData: YamlScalar = yamlDefault.parseToYamlNode(scalarScientificNotationFloatYamlStr).yamlScalar
        assertEquals(123.0f, parsedData.toFloat())
    }

    @Test
    fun `Return false when parsing boolean scalar with false value in a yaml string using decodeFromString`() {
        val falseBooleanYamlStr = "false"
        val parsedData: Boolean = yamlDefault.decodeFromString<Boolean>(falseBooleanYamlStr)
        assertEquals(false, parsedData)
    }

    @Test
    fun `Return false when parsing boolean scalar with false value in a yaml string using parseToYamlNode`() {
        val falseBooleanYamlStr = "false"
        val parsedData: YamlScalar = yamlDefault.parseToYamlNode(falseBooleanYamlStr).yamlScalar
        assertEquals(false, parsedData.toBoolean())
    }

    @Test
    fun `Return true when parsing boolean scalar with true value in a yaml string using decodeFromString`() {
        val trueBooleanYamlStr = "true"
        val parsedData: Boolean = yamlDefault.decodeFromString<Boolean>(trueBooleanYamlStr)
        assertEquals(true, parsedData)
    }

    @Test
    fun `Return true when parsing boolean scalar with true value in a yaml string using parseToYamlNode`() {
        val trueBooleanYamlStr = "true"
        val parsedData: YamlScalar = yamlDefault.parseToYamlNode(trueBooleanYamlStr).yamlScalar
        assertTrue(parsedData.toBoolean())
    }

    @Test
    fun `Return null when parsing tilde in a yaml string using decodeFromString`() {
        val tildeYamlStr = "~"
        val parsedData: String? = yamlDefault.decodeFromString(tildeYamlStr)
        assertEquals(null, parsedData)
    }

    @Test
    fun `Return null when parsing tilde in a yaml string using parseToYamlNode`() {
        val tildeYamlStr = "~"
        val parsedData = yamlDefault.parseToYamlNode(tildeYamlStr)
        assertTrue(parsedData is YamlNull)
    }

    @Test
    fun `Return number list when parsing number list in a yaml string using decodeFromString`() {
        val numberListYamlStr =
            """
              - 123
              - 456
            """
        val parsedData: List<Int> = yamlDefault.decodeFromString(numberListYamlStr)
        assertEquals(listOf(123, 456), parsedData)
    }

    @Test
    fun `Return number list when parsing number list in a yaml string using parseToYamlNode`() {
        val numberListYamlStr =
            """
              - 123
              - 456
            """
        val parsedData: YamlList = yamlDefault.parseToYamlNode(numberListYamlStr).yamlList
        val item1 = parsedData[0].yamlScalar.content
        assertEquals(123, item1.toInt())
        val item2 = parsedData[1].yamlScalar.content
        assertEquals(456, item2.toInt())
    }

    @Test
    fun `Return number list when parsing flow-style list of numbers in a yaml string using decodeFromString`() {
        val numberListYamlStr = "[123,456]"
        val parsedData: List<Int> = yamlDefault.decodeFromString(numberListYamlStr)
        assertEquals(listOf(123, 456), parsedData)
    }

    @Test
    fun `Return number list when parsing flow-style list of numbers in a yaml string using parseToYamlNode`() {
        val numberListYamlStr = "[123,456]"
        val parsedData: YamlList = yamlDefault.parseToYamlNode(numberListYamlStr).yamlList
        val item1 = parsedData[0].yamlScalar.content
        assertEquals(123, item1.toInt())
        val item2 = parsedData[1].yamlScalar.content
        assertEquals(456, item2.toInt())
    }

    @Test
    fun `Return strings when parsing strings list in a yaml string using decodeFromString`() {
        val stringListYamlStr =
            """
              - v1
              - v2
            """
        val parsedData: List<String> = yamlDefault.decodeFromString(stringListYamlStr)
        assertEquals(listOf("v1", "v2"), parsedData)
    }

    @Test
    fun `Return strings when parsing strings list in a yaml string using parseToYamlNode`() {
        val stringListYamlStr =
            """
              - v1
              - v2
            """
        val parsedData: YamlList = yamlDefault.parseToYamlNode(stringListYamlStr).yamlList
        val item1 = parsedData[0].yamlScalar.content
        assertEquals("v1", item1)
        val item2 = parsedData[1].yamlScalar.content
        assertEquals("v2", item2)
    }

    @Test
    fun `Return strings when parsing flow-style list of strings in a yaml string using decodeFromString`() {
        val stringListYamlStr = "[v1,v2]"
        val parsedData: List<String> = yamlDefault.decodeFromString(stringListYamlStr)
        assertEquals(listOf("v1", "v2"), parsedData)
    }

    @Test
    fun `Return strings when parsing flow-style list of strings in a yaml string using parseToYamlNode`() {
        val stringListYamlStr = "[v1,v2]"
        val parsedData: YamlList = yamlDefault.parseToYamlNode(stringListYamlStr).yamlList
        val item1 = parsedData[0].yamlScalar.content
        assertEquals("v1", item1)
        val item2 = parsedData[1].yamlScalar.content
        assertEquals("v2", item2)
    }


    @Test
    fun `Return floats when parsing list of floats in a yaml string using decodeFromString`() {
        val stringListYamlStr =
            """
              - 12.3
              - 23.7e1
            """
        val parsedData: List<Float> = yamlDefault.decodeFromString(stringListYamlStr)
        assertEquals(listOf(12.3f, 237.0f), parsedData)
    }

    @Test
    fun `Return floats when parsing list of floats in a yaml string using parseToYamlNode`() {
        val stringListYamlStr =
            """
              - 12.3
              - 23.7e1
            """
        val parsedData: YamlList = yamlDefault.parseToYamlNode(stringListYamlStr).yamlList
        val item1 = parsedData[0].yamlScalar.content
        assertEquals(12.3f, item1.toFloat())
        val item2 = parsedData[1].yamlScalar.content
        assertEquals(237.0f, item2.toFloat())
    }


    @Test
    fun `Return floats when parsing flow-style list of floats in a yaml string using decodeFromString`() {
        val stringListYamlStr = "[12.3,23.7e1]"
        val parsedData: List<Float> = yamlDefault.decodeFromString(stringListYamlStr)
        assertEquals(listOf(12.3f, 237.0f), parsedData)
    }

    @Test
    fun `Return floats when parsing flow-style list of floats in a yaml string using parseToYamlNode`() {
        val stringListYamlStr = "[12.3,23.7e1]"
        val parsedData: YamlList = yamlDefault.parseToYamlNode(stringListYamlStr).yamlList
        val item1 = parsedData[0].yamlScalar.content
        assertEquals(12.3f, item1.toFloat())
        val item2 = parsedData[1].yamlScalar.content
        assertEquals(237.0f, item2.toFloat())
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
    fun `Return booleans when parsing list of boolean values in a yaml string using parseToYamlNode`() {
        val stringListYamlStr =
            """
              - true
              - false
              - true
              - true
            """
        val stringListYamlStr2 = "[true, false, true, true]"
        val parsedData: YamlList = yamlDefault.parseToYamlNode(stringListYamlStr).yamlList
        val parsedData2: YamlList = yamlDefault.parseToYamlNode(stringListYamlStr2).yamlList
        assertTrue(parsedData[0].yamlScalar.toBoolean())
        assertFalse(parsedData[1].yamlScalar.toBoolean())
        assertTrue(parsedData[2].yamlScalar.toBoolean())
        assertTrue(parsedData[3].yamlScalar.toBoolean())

        assertTrue(parsedData.equivalentContentTo(parsedData2))
    }


    @Test
    fun `Return map when parsing map object in a yaml string using parseToYamlNode`() {
        val mapWithScalarValuesYamlStr =
            """
            k1: v1
            k2: 123
            k3: false
            k4: 12.3
            k5: 1.2e1
            k6: ~
            """
        val yamlMapNode: YamlMap = yamlDefault.parseToYamlNode(mapWithScalarValuesYamlStr).yamlMap
        assertEquals("v1", yamlMapNode.getScalar("k1")!!.content)
        assertEquals(123, yamlMapNode.getScalar("k2")!!.toInt())
        assertEquals(false, yamlMapNode.getScalar("k3")!!.toBoolean())
        assertEquals(12.3f, yamlMapNode.getScalar("k4")!!.toFloat())
        assertEquals(12.0f, yamlMapNode.getScalar("k5")!!.toFloat())
        assertTrue(yamlMapNode.get<YamlNode>("k6")!! is YamlNull)
    }

    @Test
    fun `Return map when parsing map with complex objects in a yaml string using parseToYamlNode`() {
        val yamlMap =
            """
            k4:
              - l1
              - l2
              - l3
            k5:
              k51: v51
              k52: v52
            """
        val parsedData: YamlNode = yamlDefault.parseToYamlNode(yamlMap)
        val parsedYamlMap: YamlMap = parsedData.yamlMap

        val k4List: YamlList = parsedYamlMap["k4"]!!
        val k4Item1: String = k4List[0].yamlScalar.content
        val k4Item2: String = k4List[1].yamlScalar.content
        val k4Item3: String = k4List[2].yamlScalar.content
        assertEquals(listOf("l1", "l2", "l3"), listOf(k4Item1, k4Item2, k4Item3))

        val k5Map: YamlMap = parsedYamlMap["k5"]!!
        assertEquals("v51", k5Map.getScalar("k51")!!.content)
        assertEquals("v52", k5Map.getScalar("k52")!!.content)

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
