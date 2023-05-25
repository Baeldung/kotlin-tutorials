package com.baeldung.optional

import org.junit.Test
import org.junit.jupiter.api.assertThrows
import java.util.Optional
import kotlin.jvm.optionals.getOrDefault
import kotlin.jvm.optionals.getOrElse
import kotlin.jvm.optionals.getOrNull
import kotlin.test.assertEquals

class OptionalTest {

  @Test
  fun `Get or Null`() {
    val optionalValue: Optional<String> = Optional.of("Hello, Baeldung!")
    val value: String? = optionalValue.getOrNull()

    assertEquals("Hello, Baeldung!", value)
  }

  @Test
  fun `Get or Else`() {
    val optionalValue: Optional<String> = Optional.empty()

    assertThrows<IllegalStateException> { optionalValue.getOrElse { throw IllegalStateException("Value is missing") } }

    val value2: String = optionalValue.getOrElse { "Default Value" }
    assertEquals("Default Value", value2)
  }

  @Test
  fun `Get or Default`() {
    val optionalValue: Optional<String> = Optional.empty()
    val value1: String = optionalValue.getOrDefault("Default Value")

    assertEquals("Default Value", value1)
  }
}