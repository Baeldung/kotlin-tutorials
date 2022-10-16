package com.baeldung.openconstructor

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class BaseTest {

  @Test
  fun `Base should return Foo depending on implementation`() {
    val implementationA = BaseImplementationA(1)
    val implementationB = BaseImplementationB(2)

    assertEquals(implementationA.fooResult, 1)
    assertEquals(implementationB.fooResult, 2)
  }

}