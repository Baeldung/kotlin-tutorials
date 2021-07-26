package com.baeldung.repeating

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RepeatingElementsUnitTest {

    @Test
    fun `should be able to create list off repeating elements`() {
        // val repeated = List(3) { index -> "Hello" }
        // val repeated = List(3) { _ -> "Hello" }
        val repeated = List(3) { "Hello" }
        assertThat(repeated).hasSize(3)
        assertThat(repeated.toSet()).containsExactly("Hello")
    }

    @Test
    fun `should be able to create other collections off repeating elements`()  {
        val mutable = MutableList(3) { "Hello" }
        assertThat(mutable.toSet()).containsExactly("Hello")

        val charArray = CharArray(3) { 'H' }
        assertThat(charArray.toSet()).containsExactly('H')

        val array = Array(3) { "Hello" }
        assertThat(array.toSet()).containsExactly("Hello")

        val intArray = IntArray(3) { 42 }
        assertThat(intArray.toSet()).containsExactly(42)
    }

    @Test
    fun `should be to able to create collection off repeating elements with sequences`() {
        val repeated = generateSequence { "Hello" }.take(3).toList()
        assertThat(repeated).hasSize(3)
        assertThat(repeated.toSet()).containsExactly("Hello")
    }
}
