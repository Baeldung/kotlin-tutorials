package com.baeldung.getGradlePropertyValue

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.util.Properties
import java.io.FileInputStream

class GetGradlePropertyValueUnitTest {

    companion object {
        @JvmStatic
        @BeforeAll
        fun setUp() {
            val properties = Properties()
            val gradlePropertiesFile = "gradle.properties"
            properties.load(FileInputStream(gradlePropertiesFile))

            for (key in properties.stringPropertyNames()) {
                System.setProperty(key, properties.getProperty(key))
            }
        }
    }

    @Test
    fun `obtain properties value using Properties class`() {
        val properties = Properties()
        properties.load(FileInputStream("gradle.properties"))
        val actualValue = properties.getProperty("customProperty")

        val expectedValue = "CustomValue"
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `obtain environment variables using System getenv method`() {
        val customProperty = System.getenv("CUSTOM_PROPERTY")
        val apiUrl = System.getenv("API_URL")

        println("CUSTOM_PROPERTY: $customProperty")
        println("API_URL: $apiUrl")

        assertEquals("CustomValue", customProperty)
        assertEquals("https://api.example.com", apiUrl)
    }

    @Test
    fun `obtain properties value using System property method`() {
        val propertyName1 = "customProperty"
        val expectedValue1 = "CustomValue"
        val actualValue1 = System.getProperty(propertyName1)

        val propertyName2 = "apiUrl"
        val expectedValue2 = "https://api.example.com"
        val actualValue2 = System.getProperty(propertyName2)

        assertEquals(expectedValue1, actualValue1)
        assertEquals(expectedValue2, actualValue2)
    }
}
