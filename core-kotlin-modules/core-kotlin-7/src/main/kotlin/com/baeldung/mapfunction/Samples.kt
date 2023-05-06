package com.baeldung.mapfunction

fun doubleNumbers() {
  val numbers = arrayOf(1, 2, 3, 4, 5)
  val doubledNumbers = numbers.map { it * 2 }
  println(doubledNumbers) // [2, 4, 6, 8, 10]
  println(numbers === doubledNumbers) // false
}

fun reassignVariable() {
  var numbers = arrayOf(1, 2, 3, 4, 5)
  numbers = numbers.map { it * 2 }.toTypedArray()
  println(numbers) // [2, 4, 6, 8, 10]
}

fun <T> Array<T>.mapInPlace(transform: (T) -> T): Array<T> {
  for (i in this.indices) {
    this[i] = transform(this[i])
  }
  return this
}

fun useMapInPlace() {
  val numbers = arrayOf(1, 2, 3, 4, 5)
  numbers.mapInPlace { it * 2 }
  println(numbers) // [2, 4, 6, 8, 10]
}