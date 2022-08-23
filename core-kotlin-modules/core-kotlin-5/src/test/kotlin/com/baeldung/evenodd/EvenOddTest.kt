package com.baeldung.evenodd

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EvenOddTest {

  @Test
  fun `Given odd or even numbers should get the right response from rem`() {
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
  fun `Given the remainder function, assertions on the remainder should pass`() {
    assertTrue(10 % 4 == 2)
    assertTrue(25 % 5 == 0)
    assertTrue(24 % 5 == 4)
    assertTrue(100 % 20 == 0)
  }

  @Test
  fun `Given odd and even values the isEven function should answer correctly`() {
    assertTrue(isEven(2))
    assertTrue(isEven(4))
    assertTrue(isEven(6))

    assertFalse(isEven(1))
    assertFalse(isEven(3))
  }

  @Test
  fun `Given odd and even values the isOdd function should answer correctly`() {
    assertTrue(isOdd(1))
    assertTrue(isOdd(3))
    assertTrue(isOdd(5))

    assertFalse(isOdd(2))
    assertFalse(isOdd(4))
  }
}