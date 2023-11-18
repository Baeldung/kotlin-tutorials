package com.baeldung.removeElementWithIterator

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class RemoveElementTest {

    @Test
    fun `when removing element with Iterator then it works`() {
        val numbers = mutableListOf(1, 2, 3, 4, 5)
        val iterator = numbers.iterator()

        while (iterator.hasNext()) {
            val element = iterator.next()
            if (element % 2 == 0) {
                iterator.remove()
            }
        }
        Assertions.assertThat(numbers).containsExactlyElementsOf(mutableListOf(1, 3, 5))
    }

    @Test
    fun `when removing element with removeAll function then it works`() {
        val numbers = mutableListOf(1, 2, 3, 4, 5)
        numbers.removeAll { it % 2 == 0 }
        Assertions.assertThat(numbers).containsExactlyElementsOf(mutableListOf(1, 3, 5))
    }

    @Test
    fun `when removing element iterating then it works`() {
        val numbers = mutableListOf(1, 2, 3, 4, 5)
        for (element in numbers.reversed()) {
            if (element % 2 == 0) {
                numbers.remove(element)
            }
        }
        Assertions.assertThat(numbers).containsExactlyElementsOf(mutableListOf(1, 3, 5))
    }
}