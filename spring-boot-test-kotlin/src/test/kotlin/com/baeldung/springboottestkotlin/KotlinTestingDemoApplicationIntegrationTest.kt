package com.baeldung.springboottestkotlin

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.junit.jupiter.api.Test

@SpringBootTest(
    classes = arrayOf(KotlinTestingDemoApplication::class),
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KotlinTestingDemoApplicationIntegrationTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun whePostCalled_thenShouldReturnBankObject() {
        val result = restTemplate.postForEntity("/api/bankAccount", BankAccount("ING", "123ING456", "JOHN SMITH"), BankAccount::class.java);

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result?.statusCode)
        assertEquals("ING", result.getBody()?.bankCode)
    }

    @Test
    fun whenGetCalled_thenShouldBadReqeust() {
        val result = restTemplate.getForEntity("/api/bankAccount?id=2", BankAccount::class.java);

        assertNotNull(result)
        assertEquals(HttpStatus.BAD_REQUEST, result?.statusCode)
    }

}
