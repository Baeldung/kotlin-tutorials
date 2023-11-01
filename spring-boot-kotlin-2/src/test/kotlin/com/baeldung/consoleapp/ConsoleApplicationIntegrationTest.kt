package com.baeldung.consoleapp

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(properties = ["spring.main.web-application-type=NONE"])
class ConsoleApplicationIntegrationTest {

    @Autowired
    lateinit var commandLineFirstComponent: CommandLineFirstComponent

    @Autowired
    lateinit var commandLineSecondComponent: CommandLineSecondComponent


    @Test
    fun whenComponentsAreAvailable_thenShouldBindToProperties() {
        assertThat(commandLineFirstComponent).isNotNull
        assertThat(commandLineSecondComponent).isNotNull
    }

}