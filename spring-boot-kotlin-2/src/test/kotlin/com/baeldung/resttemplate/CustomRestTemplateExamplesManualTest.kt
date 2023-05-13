package com.baeldung.resttemplate

import com.baeldung.resttemplate.config.RestTemplateConfig
import com.baeldung.resttemplate.dto.Foo
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.client.RestTemplate

@SpringBootTest(classes = [RestTemplateConfig::class])
class CustomRestTemplateExamplesManualTest {

    @Autowired
    @Qualifier("restTemplateCustom")
    lateinit var restTemplate: RestTemplate

    @Test
    fun `should update the name of Foo`() {
        var foo = Foo(1, "John")
        restTemplate.postForObject("http://localhost:8082/spring-rest/foos", foo, Foo::class.java)

        val update = mapOf("name" to "Jane")
        foo = restTemplate.patchForObject("http://localhost:8082/spring-rest/foos/{id}", update, Foo::class.java, 1)
        Assertions.assertThat(foo).isNotNull
        Assertions.assertThat(foo.name).isEqualTo("Jane")
    }
}