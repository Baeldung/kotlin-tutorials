package com.baeldung.springbootkotlinconfigurationpropertyvalue

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class MyServiceFieldInjection {
    @Value("\${value.from.file}")
    lateinit var valueFromFile: String

    fun doServiceJob() {
        println("value.from.file=$valueFromFile")
    }
}