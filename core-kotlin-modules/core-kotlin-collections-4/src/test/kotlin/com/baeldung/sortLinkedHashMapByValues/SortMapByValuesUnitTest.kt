package com.baeldung.sortLinkedHashMapByValues

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SortMapByValuesUnitTest {

    @Test
    fun `when given hashLinkedMap then it is sorted by values using list and sortedWith method`() {
        val ageOfStudents = linkedMapOf(
          "Mark" to 22,
          "Peter" to 23,
          "John" to 20
        )

        val sortedPairList = ageOfStudents.toList().sortedWith(compareBy { it.second })
        val result = linkedMapOf(*sortedPairList.toTypedArray())
        val expectedStudentsSortedByValues = linkedMapOf(
          "John" to 20,
          "Mark" to 22,
          "Peter" to 23
        )
        assertThat(result).isEqualTo(expectedStudentsSortedByValues)
    }

    @Test
    fun `when given hashLinkedMap then it is sorted by values using sortedBy method`() {
        val ageOfStudents = linkedMapOf(
          "Mark" to 22,
          "Peter" to 23,
          "John" to 20
        )

        val sortedPairList = ageOfStudents.toList().sortedBy { it.second }
        val result = linkedMapOf(*sortedPairList.toTypedArray())
        val expectedStudentsSortedByValues = linkedMapOf(
          "John" to 20,
          "Mark" to 22,
          "Peter" to 23
        )
        assertThat(result).isEqualTo(expectedStudentsSortedByValues)
    }
}