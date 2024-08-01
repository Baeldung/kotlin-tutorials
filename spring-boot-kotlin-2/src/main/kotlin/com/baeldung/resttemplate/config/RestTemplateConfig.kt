package com.baeldung.resttemplate.config

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import java.time.Duration

@Configuration
class RestTemplateConfig {

    @Bean("restTemplateCustom")
    fun restTemplate(): RestTemplate {
        val requestFactory = HttpComponentsClientHttpRequestFactory()
        val restTemplateBuilder = RestTemplateBuilder()
        restTemplateBuilder.setReadTimeout(Duration.ofMillis(5_000))
        restTemplateBuilder.setReadTimeout(Duration.ofMillis(5_000))
        val restTemplate = restTemplateBuilder.build()
        restTemplate.requestFactory = requestFactory

        return restTemplate;
    }
}