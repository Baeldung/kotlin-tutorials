package com.baeldung.mapToList

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class MapToListUnitTest {
    private val DEV_MAP: Map<String, String> = mapOf(
      "Kent" to "Linux",
      "Eric" to "MacOS",
      "Kevin" to "Windows",
      "Michal" to "MacOS",
      "Saajan" to "Linux",
    )

    @Test
    fun `when using entries then get the expected list`() {
        val result = DEV_MAP.entries.toList()
        assertEquals(DEV_MAP.size, result.size)
        result.forEach { entry ->
            assertEquals(DEV_MAP[entry.key], entry.value)
        }
    }

    @Test
    fun `when using toList() then get the expected list`() {
        val result = DEV_MAP.toList()
        assertEquals(DEV_MAP.size, result.size)
        result.forEach { pair ->
            assertEquals(DEV_MAP[pair.first], pair.second)
        }
    }

    @Test
    fun `when using keys and values then get the expected list`() {
        val keyList = DEV_MAP.keys.toList()
        val valueList = DEV_MAP.values.toList()

        assertThat(keyList).containsExactlyInAnyOrder("Kent", "Eric", "Kevin", "Michal", "Saajan")
        assertThat(valueList).containsExactlyInAnyOrder("Linux", "MacOS", "Windows", "MacOS", "Linux")
    }


    @Test
    fun `when using entries then get two correlated lists`() {
        val keyList = mutableListOf<String>()
        val valueList = mutableListOf<String>()
        DEV_MAP.entries.forEach {
            keyList += it.key
            valueList += it.value
        }

        assertThat(keyList).hasSize(DEV_MAP.size)
        assertThat(valueList).hasSize(DEV_MAP.size)

        repeat(DEV_MAP.size) { idx -> assertEquals(DEV_MAP[keyList[idx]], valueList[idx]) }
    }

    @Test
    fun `when using toList then get two correlated lists`() {
        val (keyList, valueList) = DEV_MAP.toList().let { kvpList -> kvpList.map { it.first } to kvpList.map { it.second } }

        assertThat(keyList).hasSize(DEV_MAP.size)
        assertThat(valueList).hasSize(DEV_MAP.size)

        repeat(DEV_MAP.size) { idx -> assertEquals(DEV_MAP[keyList[idx]], valueList[idx]) }
    }
}