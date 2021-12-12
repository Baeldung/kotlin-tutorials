package com.baeldung.kotlinspringexecutable

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSpringExecutableApplication

fun main(args: Array<String>) {
	runApplication<KotlinSpringExecutableApplication>(*args)
}
