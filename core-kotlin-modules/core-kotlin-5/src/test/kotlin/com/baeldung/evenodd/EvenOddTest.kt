package com.baeldung.evenodd

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EvenOddTest {


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