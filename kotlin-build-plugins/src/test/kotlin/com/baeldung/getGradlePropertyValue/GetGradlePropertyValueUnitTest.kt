package com.baeldung.getGradlePropertyValue

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.io.File
import java.util.Properties
import java.io.FileInputStream

class GetGradlePropertyValueUnitTest {

    @Test
    fun `obtain environment variables using System getenv method`() {
        val customProperty = System.getenv("CUSTOM_PROPERTY")
        val apiUrl = System.getenv("API_URL")

        assertEquals("CustomValue", customProperty)
        assertEquals("https://api.example.com", apiUrl)
    }

    @Test
    fun `obtain custom property value by loading properties from generated file`() {
        val propertiesFileUrl = this::class.java.classLoader.getResource("custom.properties")
            ?: throw IllegalStateException("Properties file not found")

        val properties = Properties().apply {
            propertiesFileUrl.openStream().use { load(it) }
        }

        assertEquals("CustomValue", properties.getProperty("custom.property"))
        assertEquals("https://api.example.com", properties.getProperty("api.url"))
    }
}
