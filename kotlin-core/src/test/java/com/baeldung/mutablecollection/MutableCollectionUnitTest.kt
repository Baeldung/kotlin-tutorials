package com.baeldung.mutablecollection

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MutableCollectionUnitTest {

    @Test
    fun `Given no elements, When initializing the collection, Then should be able to add more`() {
        val colorSet = mutableSetOf<String>()
        assertThat(colorSet).isEmpty()

        val colorSetTagged: MutableSet<String> = mutableSetOf()
        assertThat(colorSetTagged).isEmpty()

        val map = mutableMapOf<String, String>()
        val list = mutableListOf<String>()
        assertThat(map).isEmpty()
        assertThat(list).isEmpty()

        val mapTagged: MutableMap<String, String> = mutableMapOf()
        val listTagged: MutableList<String> = mutableListOf()
        assertThat(mapTagged).isEmpty()
        assertThat(listTagged).isEmpty()
    }
}
