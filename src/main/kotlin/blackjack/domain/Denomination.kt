package blackjack.domain

enum class Denomination(val rank: String, val score: Int) {
    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10),
    ;

    companion object {
        const val MAXIMUM_SUM_OF_CARD_POINTS = 21
        private const val WRONG_CARD_RANK_MESSAGE = "잘못된 카드 등급입니다."

        private val scorePool = values()
            .associate { it.rank to it.score }

        fun toCards(suit: Suit): List<Card> {
            return values()
                .map { Card(suit, it) }
                .toList()
        }

        fun getScore(rank: String): Int {
            return scorePool[rank] ?: throw IllegalArgumentException(WRONG_CARD_RANK_MESSAGE)
        }

        fun isAceRank(rank: String): Boolean = ACE.rank == rank
    }
}
