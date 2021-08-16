package com.baeldung.lists

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ListCopyUnitTest {

    private val cities = listOf("Berlin", "Munich", "Hamburg")

    @Test
    fun `should be able to shallow copy a list using toList extension functions`() {
        val copied = cities.toList()
        val mutableCopy = cities.toMutableList()

        assertThat(copied).containsAll(cities)
        assertThat(mutableCopy).containsAll(cities)

        assertThat(copied).isNotSameAs(cities)
        assertThat(copied[0]).isSameAs(cities[0])
    }
}
