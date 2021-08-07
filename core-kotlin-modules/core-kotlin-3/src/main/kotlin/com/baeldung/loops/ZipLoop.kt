package com.baeldung.loops

fun printMultiplicationTable(factor: Int, start: Int, end: Int) {
    val multipliers = start..end
    val multiplicationResults = factor * start..factor * end step factor
    for ((multiplier, result) in multipliers zip multiplicationResults)
        println("$factor x $multiplier = $result")
}

fun showMatches(team1: List<String>, team2: List<String>) {
    for ((player1, player2) in team1 zip team2)
        println("$player1 vs $player2")
}

fun showMatchLabels(team1: List<String>, team2: List<String>) {
    for (match in team1.zip(team2) { player1, player2 -> "$player1 vs $player2" })
        println(match)
}

fun main() {
    // Team Matches
    val teamA = listOf("A1", "A2", "A3")
    val teamB = listOf("B1", "B2")
    showMatches(teamA, teamB)
    showMatchLabels(teamA, teamB)

    // Multiplication Table
    printMultiplicationTable(27, 3, 7)
}