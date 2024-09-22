package com.baeldung.functionaldsl.beandeclration

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.BeanDefinitionDsl
import org.springframework.context.support.beans
import java.math.BigDecimal

@SpringBootApplication
open class Application

fun main(vararg args: String) {
    runApplication<Application>(*args) {
        addInitializers(
            beans {
                bean(
                    name = "functionallyDeclaredBean",
                    scope = BeanDefinitionDsl.Scope.SINGLETON,
                    isLazyInit = false,
                    isPrimary = false,
                    function = {
                        BigDecimal(1.0)
                    },
                )
            },
        )
    }
}
