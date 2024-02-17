package com.baeldung.theValueAnnotation

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.PropertySource


@PropertySource(value = ["classpath:application-inject-value.yml"], factory = YamlPropertySourceFactory::class)
@SpringBootApplication(scanBasePackages = ["com.baeldung.theValueAnnotation"])
class KotlinValueInjectionApplication

fun main(args: Array<String>) {
    SpringApplication.run(KotlinValueInjectionApplication::class.java, *args)
}