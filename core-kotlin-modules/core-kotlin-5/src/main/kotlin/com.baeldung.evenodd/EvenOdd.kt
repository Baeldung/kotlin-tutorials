package com.baeldung.evenodd

fun evenOddChecks() {
  10 % 4 == 2
  25 % 5 == 0
  24 % 5 == 4
  100 % 20 == 0
}

fun evenOdd() {
  val a = 42
  val b = 25

  val aRem2 = a % 2
  val bRem2 = b % 2

  printEvenNumbers(listOf(a, b))
}

fun printEvenNumbers(numbers: List<Int>) {
  numbers.forEach { number ->
    if(number % 2 == 0) {
      println(number)
    }
  }
}