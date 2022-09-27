package com.baeldung.springbootkotlinconfigurationpropertyvalue.defaultvalue

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class MyService(@Value("\${my.nullable.property:#{null}}") var nullableProperty: String?) {
    fun doServiceJob() {
        println("my.nullable.property=$nullableProperty")
    }
}