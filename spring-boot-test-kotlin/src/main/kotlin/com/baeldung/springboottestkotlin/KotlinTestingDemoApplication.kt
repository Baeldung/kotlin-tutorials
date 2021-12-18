package com.baeldung.springboottestkotlin

import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class KotlinTestingDemoApplication

fun main(args: Array<String>) {
     runApplication<KotlinTestingDemoApplication>(*args)
}
