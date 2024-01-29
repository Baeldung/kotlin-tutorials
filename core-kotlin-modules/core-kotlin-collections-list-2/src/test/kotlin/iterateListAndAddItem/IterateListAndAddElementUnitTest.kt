package iterateListAndAddItem

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


fun byForEach(list: List<String>): List<String> {
    val result = mutableListOf<String>()
    list.forEach {
        result += it
        if (it.length > 1) {
            result += "<- a long one"
        }
    }
    return result.toList()
}

fun byBuildList(list: List<String>) =
    buildList {
        list.forEach {
            this += it
            if (it.length > 1) {
                this += "<- a long one"
            }
        }
    }

fun addBeforeByBuildList(list: List<String>) =
    buildList {
        list.forEach {
            if (it.length > 1) {
                this += "a long one ->"
            }
            this += it
        }
    }

fun byListIterator(list: MutableList<String>) {
    val it = list.listIterator()
    for (e in it) {
        if (e.length > 1) {
            it.add("<- a long one")
        }
    }
}

fun addBeforeByListIterator(list: MutableList<String>) {
    val it = list.listIterator()
    for (e in it) {
        if (e.length > 1) {
            it.previous()
            it.add("a long one ->")
            it.next()
        }
    }
}

class IterateListAndAddElementUnitTest {
    val myList = listOf("ab", "a", "cd", "c", "xyz")

    @Test
    fun `when using byLoop() then get expected result`() {
        assertEquals(
            listOf("ab", "<- a long one", "a", "cd", "<- a long one", "c", "xyz", "<- a long one"),
            byForEach(myList)
        )
    }

    @Test
    fun `when using buildList() then get expected result`() {
        assertEquals(
            listOf("ab", "<- a long one", "a", "cd", "<- a long one", "c", "xyz", "<- a long one"),
            byBuildList(myList)
        )
        //insert before the target
        assertEquals(
            listOf("a long one ->", "ab", "a", "a long one ->", "cd", "c", "a long one ->", "xyz"),
            addBeforeByBuildList(myList)
        )
    }

    @Test
    fun `given mutableList, when using listIterator to append elements then get expected result`() {
        val myMutableList = mutableListOf("ab", "a", "cd", "c", "xyz")
        byListIterator(myMutableList)
        assertEquals(
            listOf("ab", "<- a long one", "a", "cd", "<- a long one", "c", "xyz", "<- a long one"),
            myMutableList
        )
    }

    @Test
    fun `given mutableList, when using listIterator to prepend elements then get expected result`() {
        val myMutableList = mutableListOf("ab", "a", "cd", "c", "xyz")
        addBeforeByListIterator(myMutableList)
        assertEquals(
            listOf("a long one ->", "ab", "a", "a long one ->", "cd", "c", "a long one ->", "xyz"),
            myMutableList
        )
    }
}