package com.baeldung.inAndNotIn

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class InAndNotInOperatorsUnitTest {

    //collection
    @Test
    fun `when using in and !in in collections then get expected result`() {
        val mySet = setOf("a", "b", "c", "d", "e")
        assertTrue { "a" in mySet }
        assertTrue { "b" in mySet }
        assertTrue { "x" !in mySet }
    }

    @Test
    fun `when using in and !in in arrays then get expected result`() {
        val myArray = arrayOf("a", "b", "c", "d", "e")
        assertTrue { "a" in myArray }
        assertTrue { "b" in myArray }
        assertTrue { "x" !in myArray }
    }

    @Test
    fun `when using in and !in in strings then get expected result`() {
        val myString = "a b c d e"
        assertTrue { "a b" in myString }
        assertTrue { 'c' in myString }
        assertTrue { "X" !in myString }
    }

    @Test
    fun `when using in and !in in ranges then get expected result`() {
        val myRange = 1..100
        assertTrue { 42 in myRange }
        assertTrue { 777 !in myRange }
    }


    // operator overload example 1

    data class Player(val name: String, val rank: Int)
    data class Team(val name: String, val players: Set<Player>)
    data class Match(val place: String, val teams: Pair<Team, Team>)

    val eric = Player("Eric", 8)
    val kai = Player("Kai", 7)
    val teamA = Team("Team A", setOf(eric, kai))

    val kevin = Player("Kevin", 6)
    val saajan = Player("Saajan", 11)
    val teamB = Team("Team B", setOf(kevin, saajan))

    val tom = Player("Tom", 1)
    val jerry = Player("Jerry", 9)
    val teamC = Team("Team C", setOf(tom, jerry))

    val match1 = Match("Frankfurt", teamA to teamC)
    val match2 = Match("Hamburg", teamB to teamC)
    val match3 = Match("Berlin", teamA to teamB)

    operator fun Match.contains(player: Player): Boolean {
        return teams.toList().any { player in it.players }
    }

    @Test
    fun `when using in and not in with player and match then get expected result`() {
        assertTrue { eric in match1 }
        assertTrue { saajan !in match1 }

        assertFalse { kai in match2 }
        assertTrue { tom !in match3 }
    }


    // operator overload example 2
    val pattern33 = Regex("^[a-z]{3} - [a-z]{3}$")
    val pattern34 = Regex("^[a-z]{3} - [a-z]{4}$")
    val pattern44 = Regex("^[a-z]{4} - [a-z]{4}$")

    operator fun Regex.contains(input: String): Boolean {
        return this.matches(input)
    }

    fun determinePattern(input: String): String {
        return when (input) {
            in pattern33 -> "3-3"
            in pattern34 -> "3-4"
            in pattern44 -> "4-4"
            else -> "none"
        }
    }

    @Test
    fun `when using findPattern then get expected result`() {
        assertEquals("3-3", determinePattern("abc - xyz"))
        assertEquals("3-4", determinePattern("top - down"))
        assertEquals("4-4", determinePattern("good - nice"))
        assertEquals("none", determinePattern("1234 - 4321"))
    }
}