package com.baeldung.configurationproperties

import com.baeldung.configurationproperties.config.ApiConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class SpringBootV2ApplicationTest {

    @Autowired
    lateinit var apiConfiguration: ApiConfiguration

    @Test
    fun givenExternalConfigProps_whenUsedConstructorBinding_thenBindExternalProperties() {
        assertThat(apiConfiguration).isNotNull
        assertThat(apiConfiguration.url).isNotBlank
        assertThat(apiConfiguration.clientId).isNotBlank
        assertThat(apiConfiguration.key).isNotBlank
    }
}