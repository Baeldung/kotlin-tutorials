package com.baeldung.lexicographicalsort

import org.junit.Test
import kotlin.test.assertContentEquals

class LexicographicalSortUnitTest {

    @Test
    fun `sort using sortedWith() method`(){
        val words = arrayOf("banana", "apple", "cherry", "date", "A", "Result", "Union")
        val sortedWords = words.sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it })

        assertContentEquals(listOf("A", "apple", "banana", "cherry", "date", "Result", "Union"), sortedWords)
    }

    @Test
    fun `sort using sortedBy() method`(){
        val words = arrayOf("banana", "apple", "cherry", "date", "A", "Result", "Union")
        val sortedWords = words.sortedBy { it.lowercase() }

        assertContentEquals(listOf("A", "apple", "banana", "cherry", "date", "Result", "Union"), sortedWords)
    }

    @Test
    fun `sort using custom method`(){
        val words = arrayOf("banana", "apple", "cherry", "date", "A", "Result", "Union")

        sortWithCustomMethod(words)
        assertContentEquals(arrayOf("A", "apple", "banana", "cherry", "date", "Result", "Union"), words)
    }

    @Test
    fun `sort using forEachIndexed method`(){
        val words = listOf("banana", "apple", "cherry", "date", "A", "Result", "Union")

        val sortedWords = sortStringsIgnoreCaseForEach(words)
        assertContentEquals(listOf("A", "apple", "banana", "cherry", "date", "Result", "Union"), sortedWords)
    }

    fun sortWithCustomMethod(words: Array<String>){
        for (i in 0..words.size - 2) {
            for (j in i + 1 until words.size) {
                if (words[i].lowercase() > words[j].lowercase()) {

                    val temp = words[i]
                    words[i] = words[j]
                    words[j] = temp
                }
            }
        }
    }


    fun sortStringsIgnoreCaseForEach(words: List<String>): List<String> {
        val sortedWords = words.toMutableList()
        sortedWords.forEachIndexed { i, word ->
            (i until sortedWords.size).forEach { j ->
                if (word.lowercase().compareTo( sortedWords[j].lowercase()) > 0) {
                    sortedWords[i] = sortedWords[j].also { sortedWords[j] = sortedWords[i] }
                }
            }
        }
        return sortedWords
    }
}