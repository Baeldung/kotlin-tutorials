package com.baeldung.resttemplate.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfig {

    @Bean("restTemplateCustom")
    fun restTemplate(): RestTemplate {
        val restTemplate = RestTemplate()
        val requestFactory = HttpComponentsClientHttpRequestFactory()
        requestFactory.setConnectTimeout(5)
        requestFactory.setReadTimeout(5)
        restTemplate.requestFactory = requestFactory;

        return restTemplate;
    }
}