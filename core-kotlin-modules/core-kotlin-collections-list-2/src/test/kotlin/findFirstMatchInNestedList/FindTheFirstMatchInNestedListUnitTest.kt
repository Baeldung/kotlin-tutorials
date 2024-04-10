package findFirstMatchInNestedList

import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.test.assertEquals

data class Player(val name: String, val score: Int)

data class Team(val name: String, val players: List<Player>)

val teamA = Team(
    "Team A", listOf(
        Player("Sam", 7),
        Player("Mike", 35),
        Player("Vincent", 15),
        Player("Paul", 12)
    )
)
val teamB = Team(
    "Team B", listOf(
        Player("Tom", 50),
        Player("Jim", 35),
        Player("Kai", 150),
        Player("Tim", 200)
    )
)
val teamC = Team(
    "Team C", listOf(
        Player("Leo", 15),
        Player("Sean", 25),
        Player("Jack", 10),
        Player("Tim", 8)
    )
)
val teamD = Team(
    "Team D", listOf(
        Player("Eric", 54),
        Player("Liam", 104),
        Player("Kevin", 70),
        Player("Peter", 177),
    )
)
val teams = listOf(teamA, teamB, teamC, teamD)

class FindTheFirstMatchInNestedListUnitTest {
    private val log: Logger = LoggerFactory.getLogger(FindTheFirstMatchInNestedListUnitTest::class.java)

    @Test
    fun `when using flatmap and first then get the expected result`() {
        val result = teams.flatMap { it.players }.first { it.score > 100 }
        assertEquals(Player("Kai", 150), result)

        teams.flatMap {
            log.info("Transforming players of the team: ${it.name}")
            it.players
        }.first {
            log.info("Checking the player: ${it.name}")
            it.score > 100
        }
    }

    @Test
    fun `when finding the first match using foreach then get the expected result`() {
        fun findTheFirstPlayerHigherThan100(teams: List<Team>): Player {
            teams.forEach { team ->
                log.info("Checking players of the team: ${team.name}")
                team.players.forEach { player ->
                    log.info("|- Checking the player: ${player.name}")
                    if (player.score > 100) return player
                }
            }
            throw IllegalStateException("No matched player found")
        }

        val result = findTheFirstPlayerHigherThan100(teams)
        assertEquals(Player("Kai", 150), result)
    }

    @Test
    fun `when using sequence and then flatmap and first then get the expected result`() {
        val result = teams.asSequence().flatMap {
            log.info("Transforming players of the team: ${it.name}")
            it.players
        }.first {
            log.info("|- Checking the player: ${it.name}")
            it.score > 100
        }
        assertEquals(Player("Kai", 150), result)
    }
}