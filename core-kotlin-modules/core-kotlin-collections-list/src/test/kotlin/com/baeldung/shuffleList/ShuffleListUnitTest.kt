package com.baeldung.shuffleList

import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.test.assertEquals


class ShuffleListUnitTest {
    val readOnlyList = ('A'..'Z').toList()

    @Test
    fun `when using shuffled() then the read-only input list gets shuffled`() {
        val result = readOnlyList.shuffled()
        println(
          """
              |Before Shuffling:
              |$readOnlyList
              |Shuffled result:
              |$result""".trimMargin()
        )
    }

    @Test
    fun `when using shuffled() then always get different shuffle result`() {
        val shuffleResults = listOf(
          readOnlyList.shuffled(),
          readOnlyList.shuffled(),
          readOnlyList.shuffled(),
        )
        assertEquals(3, shuffleResults.distinct().size)
    }

    @Test
    fun `when shuffling with same seeded random then always get the same shuffle result`() {
        val myRandomSeed = 42
        val shuffleResults = listOf(
          readOnlyList.shuffled(Random(myRandomSeed)),
          readOnlyList.shuffled(Random(myRandomSeed)),
          readOnlyList.shuffled(Random(myRandomSeed)),
        )
        assertEquals(1, shuffleResults.distinct().size)
    }

    @Test
    fun `when using shuffled() then the mutable input list gets shuffled`() {

        val mutableList = ('A'..'Z').toMutableList()
        val result = mutableList.shuffled()
        println(
          """
              |Before Shuffling:
              |$readOnlyList
              |Shuffled result:
              |$result""".trimMargin()
        )
    }

    @Test
    fun `when using shuffle() then the mutable input list gets in-place shuffled `() {
        val mutableList = ('A'..'Z').toMutableList()
        mutableList.shuffle()
        println(
          """The original list:
          |$mutableList""".trimMargin()
        )
    }
}