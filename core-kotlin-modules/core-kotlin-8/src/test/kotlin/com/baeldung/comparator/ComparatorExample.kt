package com.baeldung.comparator

import org.junit.jupiter.api.Test

class ComparatorExample {

    @Test
    fun lengthComparatorTest() {
        val words = listOf("apple", "blueberry", "cherry", "date")
        val lengthComparator = object : Comparator<String> {
            override fun compare(string1: String, string2: String): Int {
                // return 0 if strings are of equal length, a negative number if the first string is shorter than the second one, or a positive number otherwise
                return string1.length - string2.length
            }
        }
        assert(words.sortedWith(lengthComparator) == listOf("date", "apple", "cherry", "blueberry"))
    }

    @Test
    fun lengthComparatorSimplifiedTest() {
        val words = listOf("apple", "blueberry", "cherry", "date")
        assert(words.sortedWith(compareBy(String::length)) == listOf("date", "apple", "cherry", "blueberry"))
    }

    @Test
    fun userDefinedObjectComparatorTest() {
        data class Person(val name: String, val age: Int)
        val people = listOf(Person("Alice", 29), Person("Bob", 31), Person("Bob", 29))
        assert(people.sortedWith(compareBy({ it.age }, { it.name })) == listOf(Person("Alice", 29), Person("Bob", 29), Person("Bob", 31)))
    }

    @Test
    fun reversedComparatorTest() {
        val words = listOf("apple", "blueberry", "cherry", "date")
        assert(words.sortedWith(compareBy<String> { it.length }.reversed()) == listOf("blueberry","cherry","apple","date"))
    }

    @Test
    fun thenTest() {
        data class Person(val name: String, val age: Int)
        val people = listOf(Person("Alice", 29), Person("Bob", 31), Person("Cleo", 29))
        val sortedPeople = people.sortedWith(compareBy(Person::age).then(compareBy(Person::name)))
        assert(sortedPeople == listOf(Person("Alice", 29), Person("Cleo", 29), Person("Bob", 31)))
    }

    @Test
    fun thenByTest() {
        data class Person(val name: String, val age: Int)
        val people = listOf(Person("Alice", 29), Person("Bob", 31), Person("Cleo", 29))
        val sortedPeople = people.sortedWith(compareBy(Person::age).thenBy(Person::name))
        assert(sortedPeople == listOf(Person("Alice", 29), Person("Cleo", 29), Person("Bob", 31)))
    }

    @Test
    fun thenDescendingAndThenByDescendingTest() {
        data class Person(val name: String, val age: Int)
        val people = listOf(Person("Alice", 29), Person("Bob", 31), Person("Cleo", 29))
        val sortedPeople = people.sortedWith(compareBy(Person::age).thenDescending(compareBy(Person::name)))
        assert(sortedPeople == listOf(Person("Cleo", 29), Person("Alice", 29), Person("Bob", 31)))
        val sortedPeople2 = people.sortedWith(compareByDescending(Person::age).thenByDescending(Person::name))
        assert(sortedPeople2 == listOf(Person("Bob", 31), Person("Cleo", 29), Person("Alice", 29)))
    }

    @Test
    fun thenComparatorTest() {
        data class Person(val name: String, val age: Int)
        val people = listOf(Person("Alice", 29), Person("Bob", 31), Person("Cleo", 29))
        val combinedComparator = compareBy(Person::age).thenComparator { person1, person2 -> person1.name.length - person2.name.length }
        val sortedPeople = people.sortedWith(combinedComparator)
        assert(sortedPeople == listOf(Person("Cleo", 29), Person("Alice", 29), Person("Bob", 31)))
    }
}