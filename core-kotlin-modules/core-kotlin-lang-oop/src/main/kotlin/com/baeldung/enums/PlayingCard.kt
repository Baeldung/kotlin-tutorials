package com.baeldung.enums

enum class Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES
}

enum class Rank(val value: Int) : Comparable<Rank> {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13);
}

enum class PlayingCard(val rank: Rank, val suit: Suit) {
    KING_OF_SPADES(Rank.KING, Suit.SPADES),
    QUEEN_OF_DIAMONDS(Rank.QUEEN, Suit.DIAMONDS);
    // we can define more like this
}