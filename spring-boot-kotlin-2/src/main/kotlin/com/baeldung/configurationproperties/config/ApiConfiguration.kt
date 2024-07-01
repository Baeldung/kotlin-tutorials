package com.baeldung.configurationproperties.config
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "api")
data class ApiConfiguration /*@ConstructorBinding constructor*/(
    val clientId: String,
    val url: String,
    val key: String
)