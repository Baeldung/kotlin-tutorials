package com.baeldung.openconstructor

import org.junit.Ignore
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class LanguageTest {

  @Test
  @Ignore
  fun `Non-Lazy Languages should return their alphabet sizes`() {
    val languageA: NonLazyLanguage = NonLazyEnglish()
    val languageB: NonLazyLanguage = NonLazySpanish()

    assertEquals(languageA.alphabetSize, 26)
    assertEquals(languageB.alphabetSize, 27)
  }

  @Test
  fun `Languages should return their alphabet sizes`() {
    val languageA: Language = English()
    val languageB: Language = Spanish()
    assertEquals(languageA.alphabetSize, 26)
    assertEquals(languageB.alphabetSize, 27)
  }

}