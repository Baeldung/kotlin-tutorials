package com.baeldung.resttemplate

import com.baeldung.resttemplate.dto.Foo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.*
import org.springframework.web.client.RestTemplate

class RestTemplateExamplesUnitTest {
    private val restTemplate = RestTemplate()

    @Test
    fun givenFoo_whenPostForObject_thenCreated() {
        val foo = Foo(1, "John")
        val response = restTemplate.postForObject("http://localhost:8082/spring-rest/foos", foo, Foo::class.java)
        assertThat(response).isNotNull
        assertThat(response.id).isEqualTo(foo.id)
        assertThat(response.name).isEqualTo(foo.name)
    }


    @Test
    fun givenFoo_whenPostForEntity_thenCreated() {
        val foo = Foo(1, "John")
        val response = restTemplate.postForEntity("http://localhost:8082/spring-rest/foos", foo, Foo::class.java)
        assertThat(response).isNotNull
        assertThat(response.statusCode).isEqualTo(HttpStatus.CREATED)
        assertThat(response.body).isNotNull
        assertThat(response.body!!.id).isEqualTo(foo.id)
        assertThat(response.body!!.name).isEqualTo(foo.name)
    }

    @Test
    fun givenFoo_whenPostForLocation_thenCreated() {
        val foo = Foo(1, "John")
        val location = restTemplate.postForLocation("http://localhost:8082/spring-rest/foos", foo)
        assertThat(location).isNotNull
        assertThat(location!!.path).isEqualTo("/spring-rest/foos/1")
    }

    @Test
    fun givenFooService_whenCallGetForObject_thenOK() {
        var foo = Foo(1, "John")
        restTemplate.postForObject("http://localhost:8082/spring-rest/foos", foo, Foo::class.java)

        foo = restTemplate.getForObject("http://localhost:8082/spring-rest/foos/1", Foo::class.java)
        assertThat(foo).isNotNull
        assertThat(foo.id).isEqualTo(1)
        assertThat(foo.name).isEqualTo("John")
    }

    @Test
    fun givenFooService_whenCallGetWithPathVariable_thenOK() {
        val foo = restTemplate.getForObject("http://localhost:8082/spring-rest/foos/{id}", Foo::class.java, 1)
        assertThat(foo).isNotNull
        assertThat(foo!!.id).isEqualTo(1)
        assertThat(foo.name).isEqualTo("John")
    }

    @Test
    fun givenFooService_whenCallGetWithQueryParameter_thenOK() {
        val uriVariables = mapOf("sample" to "value")
        val foo = restTemplate.getForObject("http://localhost:8082/spring-rest/foos/{id}", Foo::class.java, 1, uriVariables)
        assertThat(foo).isNotNull
        assertThat(foo!!.id).isEqualTo(1)
        assertThat(foo.name).isEqualTo("John")
    }

    @Test
    fun givenFooService_whenCallPut_thenOK() {
        val foo = Foo(1, "John")
        restTemplate.put("http://localhost:8082/spring-rest/foos/{id}", foo, 1)
    }

    @Test
    fun givenFooService_whenCallDelete_thenOK() {
        restTemplate.delete("http://localhost:8082/spring-rest/foos/{id}", 1)
    }

    @Test
    fun givenFooService_whenCallHeadForHeaders_thenOK() {
        val headers = restTemplate.headForHeaders("http://localhost:8082/spring-rest/foos")
        assertThat(headers).isNotNull
        assertThat(headers.contentType).isEqualTo(MediaType.APPLICATION_JSON)
    }

    @Test
    fun givenFooService_whenCallOptionsForAllow_thenOK() {
        val options = restTemplate.optionsForAllow("http://localhost:8082/spring-rest/foos")
        assertThat(options).isNotNull
        assertThat(options).contains(HttpMethod.GET, HttpMethod.POST, HttpMethod.HEAD, HttpMethod.OPTIONS)
    }

    @Test
    fun givenFooService_whenCallExchange_thenOK() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val foo = Foo(1, "John")
        val request = HttpEntity(foo, headers)
        val response = restTemplate.exchange("http://localhost:8082/spring-rest/foos", HttpMethod.POST, request, Foo::class.java)
        assertThat(response).isNotNull
        assertThat(response.statusCode).isEqualTo(HttpStatus.CREATED)
    }
}