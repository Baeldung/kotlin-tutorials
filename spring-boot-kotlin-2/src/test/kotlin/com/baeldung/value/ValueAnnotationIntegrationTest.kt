package com.baeldung.value

import com.baeldung.consoleapp.CommandLineFirstComponent
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals

@RunWith(SpringRunner::class)
@SpringBootTest
class ValueAnnotationIntegrationTest {

    @Autowired
    lateinit var myBean: MyBean

    @Autowired
    lateinit var myBean2: MyBean2

    @Autowired
    lateinit var myBean3: MyBean3

    @Autowired
    lateinit var myBean4: MyBean4


    @Test
    fun `Verify beans are getting the correct value`() {
        assertEquals("abc", myBean.propertyValue)
        assertEquals("abc", myBean2.propertyValue)
        assertEquals("spring-default", myBean3.propertyValue)
        assertEquals(null, myBean4.propertyValue)
    }

}