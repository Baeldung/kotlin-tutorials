package com.baeldung.pair.workingWithTriple

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

data class Player(
    val name: String,
    var score: Int
)



typealias Match = Triple<Player, Player, String>

fun Match.firstWin() = apply {
    first.score++
    second.score--
}

fun Match.secondWin() = apply {
    first.score--
    second.score++
}

class WorkingWithTripleUnitTest {

    @Test
    fun `when calling toString, then get the expected result`() {
        val t = Triple("A", "B", "C")
        assertEquals("(A, B, C)", t.toString())
        assertEquals("(A, B, C)", "$t")
    }

    @Test
    fun `when assignment with deconstruction, then get the expected result`() {
        val (a, b, c) = Triple(42, "Kotlin", Long.MAX_VALUE)
        assertTrue { a is Int }
        assertEquals(42, a)

        assertTrue { b is String }
        assertEquals("Kotlin", b)

        assertTrue { c is Long }
        assertEquals(Long.MAX_VALUE, c)
    }

    @Test
    fun `when calling triple-toList(), then get the expected result`() {
        val t = Triple("Java", "Kotlin", "Python")
        assertEquals(listOf("Java", "Kotlin", "Python"), t.toList())
    }

    fun <T> Triple<T, T, T>.reverse() = Triple(third, second, first)

    @Test
    fun `when reversing a triple, then get the expected result`() {
        val t = Triple("x", "y", "z")
        assertEquals(Triple("z", "y", "x"), t.reverse())
    }

    @Test
    fun `when using triple store matches, then get the expected result`() {
        val kent = Player("Kent", 42)
        val eric = Player("Eric", 42)
        val tom = Player("Tom", 20)
        val john = Player("John", 32)

        val matchesTomorrow = listOf(
            Triple(kent, tom, "9:00"),
            Triple(eric, john, "10:00"),
            Triple(kent, eric, "17:00"),
            Triple(tom, john, "18:00"),
        )

        val matchAt10 = matchesTomorrow.first { it.third == "10:00" }
        assertEquals(eric, matchAt10.first)
        assertEquals(john, matchAt10.second)

        //using type alias
        val matchesTomorrow2 = listOf(
            Match(kent, tom, "9:00"),
            Match(eric, john, "10:00"),
            Match(kent, eric, "17:00"),
            Match(tom, john, "18:00"),
        )

        val matchAt17 = matchesTomorrow2.first { it.third == "17:00" }
        assertEquals(kent, matchAt17.first)
        assertEquals(eric, matchAt17.second)

        matchAt17.secondWin()

        assertEquals(41, matchAt17.first.score)
        assertEquals(43, matchAt17.second.score)

    }
}