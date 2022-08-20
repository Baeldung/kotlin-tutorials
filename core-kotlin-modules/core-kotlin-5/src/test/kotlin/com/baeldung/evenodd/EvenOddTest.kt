package com.baeldung.evenodd

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EvenOddTest {

  @Test
  fun is_even_is_odd_demo() {
    val a = 42
    val b = 25

    val aRem2 = a % 2
    val bRem2 = b % 2
    val isAEven: Boolean = aRem2 == 0
    val isBEven: Boolean = bRem2 == 0

    assertTrue(isAEven)
    assertFalse(isBEven)
  }

  @Test
  fun using_modulo_operator() {
    assertTrue(10 % 4 == 2)
    assertTrue(25 % 5 == 0)
    assertTrue(24 % 5 == 4)
    assertTrue(100 % 20 == 0)
  }

  @Test
  fun is_even_function() {
    assertEquals(true, isEven(2))
    assertEquals(true, isEven(4))
    assertEquals(true, isEven(6))
    assertEquals(false, isEven(1))
    assertEquals(false, isEven(3))
  }

  @Test
  fun is_odd_function() {
    assertEquals(true, isOdd(1))
    assertEquals(true, isOdd(3))
    assertEquals(true, isOdd(5))
    assertEquals(false, isOdd(2))
    assertEquals(false, isOdd(4))
  }
}