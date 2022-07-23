package com.baeldung.smartCastImpossible

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NiceOne {
    var myString: String? = null
    val notNullList: MutableList<String> = mutableListOf()

    fun addToList() {
//        if (myString != null)
//            notNullList += myString //<-- compilation error
    }
}

class NiceTwo {
    val notNullList: MutableList<String> = mutableListOf()
    private lateinit var myString: String

    private fun determineString() {
        myString = if (notNullList.size % 2 == 0) "Even" else "Odd"
    }

    fun addToList() {
        determineString()
        notNullList += myString
    }
}

class NiceThree {
    var myString: String? = null
    val notNullList: MutableList<String> = mutableListOf()

    fun addToList() {
        val myCopy: String? = myString
        if (myCopy != null)
            notNullList += myCopy
    }
}

class NiceFour {
    var myString: String? = null
    val notNullList: MutableList<String> = mutableListOf()

    fun addToList() {
        myString?.let { notNullList += it }
    }
}

class NiceFive {
    var myString: String? = null
    val notNullList: MutableList<String> = mutableListOf()

    fun addToList() {
        try {
            if (myString != null) {
                notNullList += myString!!
            }
        } catch (ex: java.lang.NullPointerException) {
            // exception handling omitted
        }
    }
}

class SmartCastIssueUnitTest {
    @Test
    fun `Given not-nullable type variable when using lateinit should get expected result`() {
        val two = NiceTwo()
        two.addToList()
        assertThat(two.notNullList).containsExactly("Even")
        two.addToList()
        assertThat(two.notNullList).containsExactly("Even", "Odd")
    }

    @Test
    fun `Given nullable type variable when using local copy should get expected result`() {
        val three = NiceThree()
        three.myString = "One"
        three.addToList()
        assertThat(three.notNullList).containsExactly("One")

        three.myString = "Two"
        three.addToList()
        assertThat(three.notNullList).containsExactly("One", "Two")

        three.myString = null
        three.addToList()
        assertThat(three.notNullList).containsExactly("One", "Two")
    }

    @Test
    fun `Given nullable type variable when using safe call and scope function should get expected result`() {
        val four = NiceFour()
        four.myString = "One"
        four.addToList()
        assertThat(four.notNullList).containsExactly("One")

        four.myString = "Two"
        four.addToList()
        assertThat(four.notNullList).containsExactly("One", "Two")

        four.myString = null
        four.addToList()
        assertThat(four.notNullList).containsExactly("One", "Two")
    }

    @Test
    fun `Given nullable type variable when using not-null assertion should get expected result`() {
        val five = NiceFive()
        five.myString = "One"
        five.addToList()
        assertThat(five.notNullList).containsExactly("One")

        five.myString = "Two"
        five.addToList()
        assertThat(five.notNullList).containsExactly("One", "Two")

        five.myString = null
        five.addToList()
        assertThat(five.notNullList).containsExactly("One", "Two")
    }
}