package com.baeldung.resttemplate

import com.baeldung.resttemplate.dto.Foo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.*
import org.springframework.web.client.RestTemplate

class RestTemplateExamplesManualTest {
    private val restTemplate = RestTemplate()

    @Test
    fun `should create a new Foo using postForObject`() {
        val foo = Foo(1, "John")
        val response = restTemplate.postForObject("http://localhost:8082/spring-rest/foos", foo, Foo::class.java)
        assertThat(response).isNotNull
        assertThat(response!!.id).isEqualTo(foo.id)
        assertThat(response.name).isEqualTo(foo.name)
    }


    @Test
    fun `should create a new Foo using postForEntity`() {
        val foo = Foo(1, "John")
        val response = restTemplate.postForEntity("http://localhost:8082/spring-rest/foos", foo, Foo::class.java)
        assertThat(response).isNotNull
        assertThat(response.statusCode).isEqualTo(HttpStatus.CREATED)
        assertThat(response.body).isNotNull
        assertThat(response.body!!.id).isEqualTo(foo.id)
        assertThat(response.body!!.name).isEqualTo(foo.name)
    }

    @Test
    fun `should create a new Foo and get its location`() {
        val foo = Foo(1, "John")
        val location = restTemplate.postForLocation("http://localhost:8082/spring-rest/foos", foo)
        assertThat(location).isNotNull
        assertThat(location!!.path).isEqualTo("/spring-rest/foos/1")
    }

    @Test
    fun `should get a Foo by id`() {
        var foo = Foo(1, "John")
        restTemplate.postForObject("http://localhost:8082/spring-rest/foos", foo, Foo::class.java)

        foo = restTemplate.getForObject("http://localhost:8082/spring-rest/foos/1", Foo::class.java)!!
        assertThat(foo).isNotNull
        assertThat(foo.id).isEqualTo(1)
        assertThat(foo.name).isEqualTo("John")
    }

    @Test
    fun `should get a Foo by id using path param`() {
        val foo = restTemplate.getForObject("http://localhost:8082/spring-rest/foos/{id}", Foo::class.java, 1)
        assertThat(foo).isNotNull
        assertThat(foo!!.id).isEqualTo(1)
        assertThat(foo.name).isEqualTo("John")
    }

    @Test
    fun `should get a Foo by id using uri variables`() {
        val uriVariables = mapOf("sample" to "value")
        val foo = restTemplate.getForObject("http://localhost:8082/spring-rest/foos/{id}", Foo::class.java, 1, uriVariables)
        assertThat(foo).isNotNull
        assertThat(foo!!.id).isEqualTo(1)
        assertThat(foo.name).isEqualTo("John")
    }

    @Test
    fun `should update Foo`() {
        val foo = Foo(1, "John")
        restTemplate.put("http://localhost:8082/spring-rest/foos/{id}", foo, 1)
    }

    @Test
    fun `should delete Foo`() {
        restTemplate.delete("http://localhost:8082/spring-rest/foos/{id}", 1)
    }

    @Test
    fun `should get headers`() {
        val headers = restTemplate.headForHeaders("http://localhost:8082/spring-rest/foos")
        assertThat(headers).isNotNull
        assertThat(headers.contentType).isEqualTo(MediaType.APPLICATION_JSON)
    }

    @Test
    fun `should get options`() {
        val options = restTemplate.optionsForAllow("http://localhost:8082/spring-rest/foos")
        assertThat(options).isNotNull
        assertThat(options).contains(HttpMethod.GET, HttpMethod.POST, HttpMethod.HEAD, HttpMethod.OPTIONS)
    }

    @Test
    fun `should create entity using exchange`() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val foo = Foo(1, "John")
        val request = HttpEntity(foo, headers)
        val response = restTemplate.exchange("http://localhost:8082/spring-rest/foos", HttpMethod.POST, request, Foo::class.java)
        assertThat(response).isNotNull
        assertThat(response.statusCode).isEqualTo(HttpStatus.CREATED)
    }
}