package com.baeldung.itParameter

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import kotlin.test.assertEquals

class TheItParameterUnitTest {

    private val log = LoggerFactory.getLogger(TheItParameterUnitTest::class.java)

    @Test
    fun `when using it then easy to read`() {
        val result = listOf("Kai", "Liam", "Eric", "Kevin")
            .filter { it.length == 4 }
            .map { it.uppercase() }
            .sortedBy { it }
        assertEquals(listOf("ERIC", "LIAM"), result)
    }

    @Test
    fun `when using it in nested function blocks then it is not easy to understand`() {
        val result = listOf("Kai", "Liam", "Eric", "Kevin").map {
            it.map {
                (it + 1).also { log.debug("Char After: $it") }
            }.joinToString(separator = "")
        }
        assertEquals(listOf("Lbj", "Mjbn", "Fsjd", "Lfwjo"), result)
    }

    @Test
    fun `when custom functions have only one parameter, then it can be used`() {
        fun Long.calc(desc: String, operation: (Long) -> Long) =
            "$desc($this) = ${operation(this)}"

        val negateOutput = 2L.calc("negate") { -it }
        assertEquals("negate(2) = -2", negateOutput)

        val squareOutput = 2L.calc("square") { it * it }
        assertEquals("square(2) = 4", squareOutput)
    }

    @Test
    fun `when using explict parameters in nested function blocks then it is easy to understand`() {
        val result = listOf("Kai", "Liam", "Eric", "Kevin").map { name ->
            name.map { originalChar ->
                (originalChar + 1).also { resultChar -> log.debug("Char After: $resultChar") }
            }.joinToString(separator = "")
        }
        assertEquals(listOf("Lbj", "Mjbn", "Fsjd", "Lfwjo"), result)
    }

    @Test
    fun `when using deconstruct in lambda function blocks then it is easy to understand`() {
        val players = listOf("Kai" to 42, "Liam" to 50, "Eric" to 27, "Kevin" to 49)
        val expectedOutput = listOf(
            "Kai's score: 42",
            "Liam's score: 50",
            "Eric's score: 27",
            "Kevin's score: 49"
        )

        val output1 = players.map { "${it.first}'s score: ${it.second}" }
        assertEquals(expectedOutput, output1)

        // easier to understand:
        val output2 = players.map { (player, score) -> "$player's score: $score" }
        assertEquals(expectedOutput, output2)
    }

}