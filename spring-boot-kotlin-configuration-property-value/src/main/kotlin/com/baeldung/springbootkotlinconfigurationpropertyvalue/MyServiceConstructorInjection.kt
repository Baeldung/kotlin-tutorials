package com.baeldung.springbootkotlinconfigurationpropertyvalue

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class MyServiceConstructorInjection(@Value("\${value.from.file}") var valueFromFile: String) {
    fun doServiceJob(){
        println("value.from.file=$valueFromFile")
    }
}