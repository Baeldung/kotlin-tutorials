package com.baeldung.parseUrl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.net.URI
import java.net.URL

class ParseUrlUnitTest {

    companion object {
        private const val urlToParse = "https://www.baeldung.com/?reqNo=3&user=Bob&age=12"
        private val REGEX_PATTERN = """https://www.baeldung.com/?.*[?&]user=([^#&]+).*""".toRegex()
    }

    @Test
    fun `given url when parsed should return user parameter`() {
        val userNameFromUrl = REGEX_PATTERN.matchEntire(urlToParse)?.groups?.get(1)?.value
        assertThat(userNameFromUrl).isEqualTo("Bob")
    }

    @Test
    fun `given url when parsed with URL class returns user parameter`() {
        val url = URL(urlToParse)
        val userNameFromUrl = url.findParameterValue("user")
        assertThat(userNameFromUrl).isEqualTo("Bob")
        assertThat(url.protocol).isEqualTo("https")
        assertThat(url.host).isEqualTo("www.baeldung.com")
    }

    @Test
    fun `given url when parsed with URI class returns user parameter`() {
        val uri = URI(urlToParse)
        val userNameFromUrl = uri.findParameterValue("user")
        assertThat(userNameFromUrl).isEqualTo("Bob")
        assertThat(uri.scheme).isEqualTo("https")
        assertThat(uri.host).isEqualTo("www.baeldung.com")
    }

    private fun URL.findParameterValue(parameterName: String): String? {
        return query.split('&').map {
            val parts = it.split('=')
            val name = parts.firstOrNull() ?: ""
            val value = parts.drop(1).firstOrNull() ?: ""
            Pair(name, value)
        }.firstOrNull{it.first == parameterName}?.second
    }

    private fun URI.findParameterValue(parameterName: String): String? {
        return rawQuery.split('&').map {
            val parts = it.split('=')
            val name = parts.firstOrNull() ?: ""
            val value = parts.drop(1).firstOrNull() ?: ""
            Pair(name, value)
        }.firstOrNull { it.first == parameterName }?.second
    }
}