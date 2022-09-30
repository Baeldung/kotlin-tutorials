package com.baeldung.configurationproperties.config
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "api")
@ConstructorBinding
data class ApiConfiguration(
    val clientId: String,
    val url: String,
    val key: String
)