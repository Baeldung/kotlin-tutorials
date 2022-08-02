package com.baeldung.addToList

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class AddToListUnitTest {
    @Test
    fun `given an arraylist when add an element to it then get expected result`() {
        val myArrayList = ArrayList<String>()
        myArrayList.add("Tom Hanks")
        assertThat(myArrayList).containsExactly("Tom Hanks")
        myArrayList += "Brad Pitt"
        assertThat(myArrayList).containsExactly("Tom Hanks", "Brad Pitt")
    }

    @Test
    fun `given a mutable list when add an element to it then get expected result`() {
        val myMutable = mutableListOf("Tom Hanks", "Brad Pitt")
        myMutable.add("Tom Cruise")
        assertThat(myMutable).containsExactly("Tom Hanks", "Brad Pitt", "Tom Cruise")
        myMutable += "Will Smith"
        assertThat(myMutable).containsExactly("Tom Hanks", "Brad Pitt", "Tom Cruise", "Will Smith")
    }

    @Test
    fun `given an immutable list when add an element to it then get expected result`() {
        var myList = listOf("Tom Hanks", "Brad Pitt")
        val originalList = myList
        //myList.add("Tom Cruise") // compilation error

        myList += "Tom Cruise" // myList must be declared as 'var'
        assertThat(myList).containsExactly("Tom Hanks", "Brad Pitt", "Tom Cruise")
        assertThat(myList).isNotSameAs(originalList)
    }
}