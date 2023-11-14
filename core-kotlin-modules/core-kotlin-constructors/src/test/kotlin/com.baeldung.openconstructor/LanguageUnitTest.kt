package com.baeldung.openconstructor

import org.junit.Ignore
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class LanguageUnitTest {

  @Test
  @Ignore
  fun `Non-Lazy Languages should return their alphabet sizes`() {
    val languageA: NonLazyLanguage = NonLazyEnglish()
    val languageB: NonLazyLanguage = NonLazySpanish()

    assertEquals(26, languageA.alphabetSize)
    assertEquals(27, languageB.alphabetSize)
  }

  @Test
  fun `Languages should return their alphabet name sizes`() {
    val languageA: Language = English()
    val languageB: Language = Spanish()
    assertEquals(7, languageA.alphabetNameSize)
    assertEquals(7, languageB.alphabetNameSize)
  }

  @Test
  fun `Languages should return their alphabet sizes`() {
    val languageA: Language = English()
    val languageB: Language = Spanish()
    assertEquals(26, languageA.alphabetSize)
    assertEquals(27, languageB.alphabetSize)
  }

}