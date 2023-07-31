package com.baeldung.list

import org.junit.jupiter.api.Test
import kotlin.test.assertContains
import kotlin.test.assertContentEquals
import kotlin.test.assertNotNull

class ListTests {
  data class Person(val name: String, val age: Int)
  data class Dog(val name: String, val age: Int)
  data class Car(val model: String, val price: Int)


  @Test
  fun `Should create a list`() {
    val people = listOf(Person("Alice", 25), Person("Bob", 30), Person("Charlie", 35))

    assertNotNull(people)
  }

  @Test
  fun `Should add to list`() {
    val dogs = mutableListOf(Dog("Buddy", 3), Dog("Max", 5), Dog("Lucy", 2))

    dogs.add(Dog("Daisy", 4))

    assertContains(dogs, Dog("Daisy", 4))
  }

  @Test
  fun `Should create list with constructor-like function`() {
    val cars = List(3) { index -> Car("Car ${index + 1}", (index + 1) * 100) }

    assertContentEquals(
      listOf(
        Car("Car 1", 100),
        Car("Car 2", 200),
        Car("Car 3", 300)
      ), cars
    )

  }
}
