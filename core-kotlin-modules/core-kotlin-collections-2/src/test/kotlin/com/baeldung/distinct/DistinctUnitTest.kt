package com.baeldung.distinct

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DistinctUnitTest {

    @Test
    fun `distinct should remove duplicates based on the equals`() {
        val protocols = listOf("tcp", "http", "tcp", "udp", "udp")
        val distinct = protocols.distinct()

        assertThat(distinct).hasSize(3)
        assertThat(distinct).containsExactlyInAnyOrder("tcp", "http", "udp")
    }

    @Test
    fun `should be able to customize the equality process by distinctBy`() {
        val urls = listOf(
          Url("https", "baeldung", 443, "/authors"),
          Url("https", "baeldung", 443, "/authors"),
          Url("http", "baeldung", 80, "/authors"),
          Url("https", "baeldung", 443, "/kotlin/distinct"),
          Url("https", "google", 443, "/"),
          Url("http", "google", 80, "/search"),
          Url("tcp", "docker", 2376, "/"),
        )

        val uniqueHosts = urls.distinctBy { it.host }
        assertThat(uniqueHosts).hasSize(3)

        val uniqueUrls = urls.distinctBy { "${it.protocol}://${it.host}:${it.port}/" }
        assertThat(uniqueUrls).hasSize(5)
    }
}

data class Url(val protocol: String, val host: String, val port: Int, val path: String)