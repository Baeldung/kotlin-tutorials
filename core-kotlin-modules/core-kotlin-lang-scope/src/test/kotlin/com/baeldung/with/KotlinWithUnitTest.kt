package com.baeldung.with

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

data class Player(val firstname: String, val lastname: String, val totalPlayed: Int, val numOfWin: Int)

fun giveMeAPlayer(): Player? {
    return Player(firstname = "Tom", lastname = "Hanks", totalPlayed = 100, numOfWin = 77)
}

class Player2(val id: Int) {
    var firstname: String = ""
    var lastname: String = ""
    var totalPlayed: Int = 0
    var numOfWin: Int = 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Player2) return false

        if (id != other.id) return false
        if (firstname != other.firstname) return false
        if (lastname != other.lastname) return false
        if (totalPlayed != other.totalPlayed) return false
        if (numOfWin != other.numOfWin) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + firstname.hashCode()
        result = 31 * result + lastname.hashCode()
        result = 31 * result + totalPlayed
        result = 31 * result + numOfWin
        return result
    }
}

class KotlinWithUnitTest {

    @Test
    fun `given a Player object when use with should get the expected result`() {
        val tomHanks = Player(firstname = "Tom", lastname = "Hanks", totalPlayed = 100, numOfWin = 77)
        val expectedDescription = "Tom Hanks's win-rate is 77%"
        val result = with(tomHanks) {
            "$firstname $lastname's win-rate is ${numOfWin * 100 / totalPlayed}%"
        }
        assertEquals(expectedDescription, result)

    }

    @Test
    fun `given a Player object when refrence it using this in with should get the expected result`() {
        val tomHanks = Player(firstname = "Tom", lastname = "Hanks", totalPlayed = 100, numOfWin = 77)
        val result = with(tomHanks) {
            "$this"
        }
        assertEquals("$tomHanks", result)
    }

    @Test
    fun `when config Player2 object using apply should get the expected result`() {
        val tomHanks = Player2(7)
        tomHanks.firstname = "Tom"
        tomHanks.lastname = "Hanks"
        tomHanks.totalPlayed = 100
        tomHanks.numOfWin = 77

        val result = Player2(7).apply {
            firstname = "Tom"
            lastname = "Hanks"
            totalPlayed = 100
            numOfWin = 77
        }
        assertEquals(tomHanks, result)
    }

    @Test
    fun `when config Player2 object using with should get the expected result`() {
        val result = with(Player2(7)) {
            firstname = "Tom"
            lastname = "Hanks"
            totalPlayed = 100
            numOfWin = 77
        }
        @Suppress("USELESS_IS_CHECK")
        assertTrue { result is Unit }
    }

    @Test
    fun `when receiver object is nullable using with and run should get the expected result`() {
        val expectedDescription = "Tom Hanks's win-rate is 77%"
        val runResult = giveMeAPlayer()?.run { "$firstname $lastname's win-rate is ${numOfWin * 100 / totalPlayed}%" }
        assertEquals(expectedDescription, runResult)

        val withResult = with(giveMeAPlayer()) {
            if (this != null) "$firstname $lastname's win-rate is ${numOfWin * 100 / totalPlayed}%" else null
        }
        assertEquals(expectedDescription, withResult)
    }
}