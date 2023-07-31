package com.baeldung.spring.integration.dsl

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration

@SpringBootApplication(scanBasePackages = ["com.baeldung.spring.integration.dsl"], exclude = [SecurityAutoConfiguration::class])
class SpringIntegrationApplication

fun main(args: Array<String>) {
    SpringApplication.run(SpringIntegrationApplication::class.java, *args)
}
