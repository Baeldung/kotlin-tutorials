package com.baeldung.prependList

import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals


infix fun <T> T.prependTo(theList: List<T>): List<T> {
    return buildList(theList.size + 1) {
        add(this@prependTo)
        addAll(theList)
    }
}

infix fun <T> List<T>.prepend(e: T): List<T> {
    return buildList(this.size + 1) {
        add(e)
        addAll(this@prepend)
    }
}

class PrependListUnitTest {
    private val beatlesSongs = listOf("Let it be", "Don't let me down", "I want to hold your hand")
    private val expected = listOf("Hey Jude", "Let it be", "Don't let me down", "I want to hold your hand")

    @Test
    fun `when using the + operator then get the expected result`() {
        val result = listOf("Hey Jude") + beatlesSongs
        assertEquals(expected, result)
    }

    @Test
    fun `when using Deque then get the expected result`() {
        val linkedList = LinkedList(beatlesSongs)
        linkedList.addFirst("Hey Jude")
        assertEquals(expected, linkedList)
    }


    @Test
    fun `when using prependTo() then get the expected result`() {
        val result = "Hey Jude" prependTo beatlesSongs
        assertEquals(expected, result)
    }

    @Test
    fun `when using prepend() then get the expected result`() {
        val result = beatlesSongs prepend "Hey Jude"
        assertEquals(expected, result)
    }

    @Test
    fun `when using add(0,e) on mutableList then get the expected result`() {
        val mutableSongs = mutableListOf("Let it be", "Don't let me down", "I want to hold your hand")
        mutableSongs.add(0, "Hey Jude")
        assertEquals(expected, mutableSongs)
    }

}