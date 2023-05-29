package com.baeldung.sortLinkedHashMapByValues

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.stream.Collectors
import kotlin.collections.LinkedHashMap


class SortMapByValuesUnitTest {

    @Test
    fun `when given hashLinkedMap then it is sorted by values using list and sortedWith method`() {
        val ageOfStudents = linkedMapOf(
            "Mark" to 22,
            "Peter" to 23,
            "John" to 20
        )

        val sortedList = ageOfStudents.toList().sortedWith(compareBy { it.second })
        val expectedStudentsSortedByValues = linkedMapOf(
            "John" to 20,
            "Mark" to 22,
            "Peter" to 23
        )
        assertThat(sortedList).containsExactlyElementsOf(expectedStudentsSortedByValues.toList())
    }

    @Test
    fun `when given hashLinkedMap then it is sorted by values using sortedBy method`() {
        val ageOfStudents = linkedMapOf(
            "Mark" to 22,
            "Peter" to 23,
            "John" to 20
        )

        val sortedList = ageOfStudents.toList().sortedBy { it.second }
        val expectedStudentsSortedByValues = linkedMapOf(
            "John" to 20,
            "Mark" to 22,
            "Peter" to 23
        )
        assertThat(sortedList).containsExactlyElementsOf(expectedStudentsSortedByValues.toList())
    }

    @Test
    fun `when given hashLinkedMap then it is sorted by values using stream`() {
        val ageOfStudents = linkedMapOf(
            "Mark" to 22,
            "Peter" to 23,
            "John" to 20
        )

        val sortedMap = ageOfStudents.entries.stream()
            .sorted(compareBy { it.value })
            .collect(Collectors.toMap({ it.key }, { it.value }, { u, v -> u }, { LinkedHashMap() }))

        val expectedStudentsSortedByValues = linkedMapOf(
            "John" to 20,
            "Mark" to 22,
            "Peter" to 23
        )
        assertThat(sortedMap.toList()).containsExactlyElementsOf(expectedStudentsSortedByValues.toList())
    }
}