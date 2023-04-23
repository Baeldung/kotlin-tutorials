package com.baeldung.mapstruct

import com.baeldung.configurationproperties.config.ApiConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootV2Application {

    fun main(args: Array<String>) {
        runApplication<SpringBootV2Application>(*args)
    }
}
