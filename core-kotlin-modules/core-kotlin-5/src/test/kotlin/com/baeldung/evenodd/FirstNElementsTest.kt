package com.baeldung.evenodd

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class FirstNElementsTest {

  @Test
  fun `Given an array take first n elements`() {
    val originalArray = intArrayOf(1, 2, 3, 4, 5)

    val n = 3
    val tempArray = IntArray(n)

    for (i in 0 until n) {
      tempArray[i] = originalArray[i]
    }

    assertContentEquals(intArrayOf(1, 2, 3), tempArray)
  }

  @Test
  fun `Given an array take first n elements using kotlin function`() {
    val originalArray = intArrayOf(1, 2, 3, 4, 5)

    val n = 3

    val tempArray: List<Int> = originalArray.take(n)

    assertContentEquals(listOf(1, 2, 3), tempArray)
  }

  @Test
  fun `Given a list take first n elements using kotlin function`() {
    val originalList = listOf(1, 2, 3, 4, 5)

    val n = 3

    val tempList = originalList.take(n)

    assertContentEquals(listOf(1, 2, 3), tempList)
  }

  @Test
  fun `Convert a list to an array`() {
    val list: List<Int> = listOf(1, 2, 3, 4)
    val array: Array<Int> = list.toTypedArray()
  }

}