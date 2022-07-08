package com.baeldung.nullable

class A {
    fun toB(): B = TODO()
}

class B {
    fun toC(): C = TODO()
}

class C

fun fluent(a: A) = a
  .toB()
  .toC()
  .toString()

fun patterMatching(a: Any): C = when(a) {
    is A -> TODO("Do something")
    is B -> TODO("Do something else")
    is C -> TODO("Do more things still")
    else -> TODO("Do default")
}

fun elvisStacking(flag: String?) =
  flag
    ?.let { transform(it) }
    ?.let { transformAgain(it) }
    ?: "erised"

internal fun transform(s: String) = s.reversed()

internal fun transformAgain(s: String) = s.toCharArray().apply { shuffle() }.let { String(it) }

fun elvisStackingWithUnitDefault(flag: String?): Unit =
  flag
    ?.let { transform(it); Unit }
    ?: println("erised")

fun ifExpression(flag: String?) =
  if (flag.isNullOrBlank()) "erised" else transform(flag)

data class Snitch(val content: Any)

fun whenExpression(flag: Any?) =
  when (flag) {
      null -> "erised"
      is Exception -> "expelliarmus"
      "socks" -> "Silente"
      "doe" -> "Piton"
      "family" -> "the boy"
      Snitch("the stone") -> "a hallow"
      else -> "babbano"
  }

data class Nested(
  val value: Inner?
)

data class Inner(
  val subvalue: StillInner?
)

data class StillInner(
  val subsubvalue: String?
)

fun callIntoDeep(nested: Nested?): String? =
  nested?.value?.subvalue?.subsubvalue