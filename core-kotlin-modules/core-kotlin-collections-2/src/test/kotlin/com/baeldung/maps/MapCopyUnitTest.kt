package com.baeldung.maps

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.assertSame

class MapCopyUnitTest {

    private val bookInfo = mapOf("name" to "1984", "author" to "Orwell")

    @Test
    fun `toMap function should copy a map content`() {
        val copied = bookInfo.toMap()
        assertThat(copied).isNotSameAs(bookInfo)
        assertThat(copied).containsAllEntriesOf(bookInfo)

        val mutableCopy = bookInfo.toMutableMap();
        assertThat(mutableCopy).containsAllEntriesOf(bookInfo)

        val destination = mutableMapOf<String, String>()
        bookInfo.toMap(destination)
        assertThat(destination).containsAllEntriesOf(bookInfo)
    }

    @Test
    fun `should be to use the underlying types directly for a copy`() {
        val copied = java.util.HashMap(bookInfo)
        assertThat(copied).containsAllEntriesOf(bookInfo)

        assertSame(copied["name"], bookInfo["name"])
    }
}
