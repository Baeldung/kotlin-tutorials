package com.baeldung.map.theMaxEntry

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FindMaxEntryInMapUnitTest {
    val skillMap = mapOf(
      "Kai" to "Kotlin, typescript, Javascript, C, C++",
      "Saajan" to "Java, C, Python, Ruby, Shell, Go",
      "Eric" to "Kotlin, Java, C, Python, Ruby, Php, Shell, Rust",
      "Kevin" to "Java, Scala, Go, Ruby",
      "Venus" to "Beauty!"
    )

    @Test
    fun `when using maxBy then find the entry with most skills`() {
        val result = skillMap.maxBy { (_, skills) -> skills.split(", ").size }
        assertEquals("Eric" to "Kotlin, Java, C, Python, Ruby, Php, Shell, Rust", result.toPair())
    }

    @Test
    fun `when using maxWith then find the entry with most skills`() {
        val result = skillMap.maxWith { e1, e2 ->
            e1.value.split(", ").size.compareTo(e2.value.split(", ").size)
        }
        assertEquals("Eric" to "Kotlin, Java, C, Python, Ruby, Php, Shell, Rust", result.toPair())
    }

    @Test
    fun `when using maxBy and maxWith then find the entry with longest name`() {
        val result1 = skillMap.maxBy { (name, _) -> name.length }
        assertEquals("Saajan" to "Java, C, Python, Ruby, Shell, Go", result1.toPair())

        val result2 = skillMap.maxWith { e1, e2 -> e1.key.length.compareTo(e2.key.length) }
        assertEquals("Saajan" to "Java, C, Python, Ruby, Shell, Go", result2.toPair())
    }


    @Test
    fun `when using maxOf then find the entry with largest (Lexicographic order) name`() {
        val result = skillMap.maxOf { (name, _) -> name }
        assertEquals("Venus", result)
    }

    @Test
    fun `when using maxOf then find the entry with largest 2nd char in a name (Lexicographic order)`() {
        val result = skillMap.maxOf { (name, _) -> name[1] }
        assertEquals('r', result)
    }

    @Test
    fun `when using maxOf then find the entry with largest skill count`() {
        val result = skillMap.maxOf { it.value.split(", ").size }
        assertEquals(8, result)
    }
}