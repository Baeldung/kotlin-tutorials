package com.baeldung.kotest.spring

import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.extensions.spring.testContextManager
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestContextManager

@SpringBootTest
@ContextConfiguration(classes = [MySpringBootApplication::class])
class TestContextTest : FunSpec({
    extension(SpringExtension)

    test("Get Test Context") {
        val contextManager: TestContextManager = testContextManager()
        val applicationContext: ApplicationContext = contextManager.testContext.applicationContext
        // Do something with applicationContext
    }
})

@SpringBootApplication
class MySpringBootApplication