package com.baeldung.reflection

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

@Disabled
class JavaReflectionUnitTest {
    private val LOG = LoggerFactory.getLogger(JavaReflectionUnitTest::class.java)

    @Test
    fun listJavaClassMethods() {
        Exception::class.java.methods
                .forEach { method -> LOG.info("Method: {}", method) }
    }

    @Test
    fun listKotlinClassMethods() {
        JavaReflectionUnitTest::class.java.methods
                .forEach { method -> LOG.info("Method: {}", method) }
    }

    @Test
    fun listKotlinDataClassMethods() {
        data class ExampleDataClass(val name: String, var enabled: Boolean)

        ExampleDataClass::class.java.methods
                .forEach { method -> LOG.info("Method: {}", method) }
    }


}
