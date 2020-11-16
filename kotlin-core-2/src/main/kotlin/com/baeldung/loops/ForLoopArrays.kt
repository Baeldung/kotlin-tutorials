package com.baeldung.loops

fun main() {

    val vowels = arrayOf('a', 'e', 'i', 'o', 'u')

    // Iterate over an array using for loop
    for (vowel in vowels) {
        println(vowel)
    }
    for (index in vowels.indices) {
        println(vowels[index])
    }
    // withIndex - using the destructuring declaration
    for ((index, vowel) in vowels.withIndex()) {
        println("The vowel at index $index is: $vowel")
    }
}