package com.baeldung.springbootkotlinconfigurationpropertyvalue.nonspringboot

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:config.properties")
class ConfigFile