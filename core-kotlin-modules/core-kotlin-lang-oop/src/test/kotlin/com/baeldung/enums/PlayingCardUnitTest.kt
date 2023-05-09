package com.baeldung.enums

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.Comparator

class PlayingCardUnitTest {

    @Test
    fun givenPlayingCard_whenGetOrdinalValue_thenReturnZeroBasedIndex() {
        Assertions.assertEquals(0, PlayingCard.KING_OF_SPADES.ordinal)
        Assertions.assertEquals(1, PlayingCard.QUEEN_OF_DIAMONDS.ordinal)
    }

    @Test
    fun givenPlayingCards_whenComparedUsingCompareTo_thenReturnWrongResult() {
        Assertions.assertTrue(PlayingCard.KING_OF_SPADES.compareTo(PlayingCard.QUEEN_OF_DIAMONDS) < 0)
    }

    @Test
    fun givenPlayingCards_whenSortedWithRankComparator_thenReturnCorrectOrder() {
        val rankComparator = Comparator<Rank> { r1, r2 ->
            r1.value - r2.value
        }
        val playingCardComparator = Comparator<PlayingCard> { pc1, pc2 ->
            rankComparator.compare(pc1.rank, pc2.rank)
        }
        val playingCards = listOf(PlayingCard.KING_OF_SPADES, PlayingCard.QUEEN_OF_DIAMONDS)
        val sortedPlayingCards = playingCards.sortedWith(playingCardComparator)

        Assertions.assertEquals(PlayingCard.QUEEN_OF_DIAMONDS, sortedPlayingCards[0])
        Assertions.assertEquals(PlayingCard.KING_OF_SPADES, sortedPlayingCards[1])
    }
}