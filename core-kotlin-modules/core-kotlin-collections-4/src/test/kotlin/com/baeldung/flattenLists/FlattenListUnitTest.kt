package com.baeldung.flattenLists

import org.junit.Test
import kotlin.test.assertEquals

data class Team(val name: String, val members: List<String>)

class FlattenListUnitTest {
    private val listOfList = listOf(listOf("Kotlin", "Java"), listOf("Python", "Ruby"), listOf("C++", "Rust", "Go"))
    private val expectedFlatList = listOf("Kotlin", "Java", "Python", "Ruby", "C++", "Rust", "Go")

    private val teamA = Team("Team A", listOf("Kai", "Eric"))
    private val teamB = Team("Team B", listOf("Kevin", "Saajan"))
    private val teamC = Team("Team C", listOf("Milos", "Tom", "Jerry"))
    private val teamList = listOf(teamA, teamB, teamC)
    private val expectedFlatMembers = listOf("Kai", "Eric", "Kevin", "Saajan", "Milos", "Tom", "Jerry")

    @Test
    fun `given a list of lists, when using flatten, then get flat list`() {
        val result = listOfList.flatten()
        assertEquals(expectedFlatList, result)
    }

    @Test
    fun `given a list of lists, when using flatMap, then get flat list`() {
        val flatList = listOfList.flatMap { it }
        assertEquals(expectedFlatList, flatList)

        val flatMembers = teamList.flatMap { it.members }
        assertEquals(expectedFlatMembers, flatMembers)
    }


    @Test
    fun `given a list of teams, when using fold, then get flat list of members`() {
        val flatList = listOfList.fold(listOf<String>()) { acc, str -> acc + str }
        assertEquals(expectedFlatList, flatList)

        val flatMembers = teamList.fold(listOf<String>()) { list, team -> list + team.members }
        assertEquals(expectedFlatMembers, flatMembers)
    }
}