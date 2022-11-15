package com.baeldung.openconstructor

abstract class Language {
  val alphabetSize by lazy { calculateAlphabetSize() }
  abstract fun calculateAlphabetSize(): Int
}

class English(val alphabet: String = "abcdefghijklmnopqrstuvwxyz") : Language() {
  override fun calculateAlphabetSize() = alphabet.length
}

class Spanish(val alphabet: String = "abcdefghijklmnñopqrstuvwxyz") : Language() {
  override fun calculateAlphabetSize() = alphabet.length
}

abstract class NonLazyLanguage {
  val alphabetSize = calculateAlphabetSize()
  abstract fun calculateAlphabetSize(): Int
}

class NonLazyEnglish(val alphabet: String = "abcdefghijklmnopqrstuvwxyz") : NonLazyLanguage() {
  override fun calculateAlphabetSize() = alphabet.length
}

class NonLazySpanish(val alphabet: String = "abcdefghijklmnñopqrstuvwxyz") : NonLazyLanguage() {
  override fun calculateAlphabetSize() = alphabet.length
}
