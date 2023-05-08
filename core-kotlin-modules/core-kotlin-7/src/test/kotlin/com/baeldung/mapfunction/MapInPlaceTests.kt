package com.baeldung.mapfunction

import org.junit.Test
import kotlin.test.assertContentEquals

class MapInPlaceTests {

  @Test
  fun `Should map an array by doubling all numbers`() {
    val numbers = arrayOf(1, 2, 3, 4, 5)
    val doubledNumbers = numbers.map { it * 2 }

    assertContentEquals(listOf(2, 4, 6, 8, 10), doubledNumbers)
  }

  @Test
  fun `Should reassign the original variable to the new array`() {
    var numbers = arrayOf(1, 2, 3, 4, 5)
    numbers = numbers.map { it * 2 }.toTypedArray()

    assertContentEquals(arrayOf(2, 4, 6, 8, 10), numbers)
  }

  @Test
  fun `Should replace array values in the original array`() {
    val numbers = arrayOf(1, 2, 3, 4, 5)
    numbers.mapInPlace { it * 2 }

    assertContentEquals(arrayOf(2, 4, 6, 8, 10), numbers)
  }
}
