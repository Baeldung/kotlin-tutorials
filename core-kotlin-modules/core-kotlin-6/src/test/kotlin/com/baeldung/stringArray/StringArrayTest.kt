package com.baeldung.stringArray

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StringArrayTest {

    @Test
    fun `given int array when checked values are correct`() {
        val arrayOfPrimitives = IntArray(4)
        arrayOfPrimitives[0] = 1
        arrayOfPrimitives[1] = 2
        arrayOfPrimitives[2] = 4
        assertThat(arrayOfPrimitives)
          .hasSize(4)
          .containsExactly(1, 2, 4, 0)
    }

    @Test
    fun `given Integer array when checked values are correct`() {
        val arrayOfInteger = arrayOf(1, 2, 3)

        assertThat(arrayOfInteger)
          .hasOnlyElementsOfTypes(Integer::class.java)
          .hasSize(3)
          .containsExactly(1, 2, 3)
    }

    @Test
    fun `given empty String array when checked there are no values`() {
        val arrayOfString = arrayOf<String>()

        assertThat(arrayOfString)
          .hasOnlyElementsOfTypes(String::class.java)
          .isEmpty()
    }

    @Test
    fun `given String array when checked values are correct`() {
        val arrayOfString = arrayOf("black", "white", "orange")

        assertThat(arrayOfString)
          .hasOnlyElementsOfTypes(String::class.java)
          .hasSize(3)
          .containsExactly("black", "white", "orange")
    }

    @Test
    fun `given String array with nulls when checked values are correct`() {
        val arrayOfString = arrayOfNulls<String>(3)

        assertThat(arrayOfString)
          .hasSize(3)
          .containsOnlyNulls()
    }

    @Test
    fun `given String array with same values when checked values are correct`() {
        val arrayOfString = Array<String?>(3){"initial value"}

        assertThat(arrayOfString)
          .hasOnlyElementsOfTypes(String::class.java)
          .hasSize(3)
          .containsOnly("initial value")
    }

    @Test
    fun `given String array initialized with element no when checked values are correct`() {
        val arrayOfString = Array<String?>(3){"element no = $it" }

        assertThat(arrayOfString)
          .hasOnlyElementsOfTypes(String::class.java)
          .hasSize(3)
          .containsOnly("element no = 0", "element no = 1", "element no = 2")
    }
}