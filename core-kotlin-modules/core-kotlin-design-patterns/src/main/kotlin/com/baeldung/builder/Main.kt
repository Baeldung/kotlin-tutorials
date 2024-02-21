package com.baeldung.builder

fun main() {
    FoodOrder.Builder()
      .bread("bread")
      .condiments("condiments")
      .meat("meat")
      .fish("bread").let { println(it) }
}