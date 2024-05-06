package com.baeldung.value

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MyBean {
    @Value("\${some.property}")
    lateinit var propertyValue: String
}

@Component
class MyBean2(@Value("\${some.property}") val propertyValue: String)

@Component
class MyBean3(@Value("\${non-existent-property:spring-default}") val propertyValue: String)

@Component
class MyBean4(@Value("\${non-existent-property:#{null}}") val propertyValue: String?)