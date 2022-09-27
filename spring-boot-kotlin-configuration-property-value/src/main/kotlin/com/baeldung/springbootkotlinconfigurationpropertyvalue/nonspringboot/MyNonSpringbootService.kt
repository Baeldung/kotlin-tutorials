package com.baeldung.springbootkotlinconfigurationpropertyvalue.nonspringboot

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class MyNonSpringbootService(@Value("\${value.from.config.file}") var valueFromFile: String) {
    fun doServiceJob() {
        println("value.from.config.file=$valueFromFile")
    }
}