package blackjack.domain

/**
 * 카드의 계산 값
 */
@JvmInline
value class CardPoint(val point: Int) {
    companion object {
        private const val MINIMUM_CARD_POINT = 1
        private const val MAXIMUM_CARD_POINT = 11
        private const val ACE_DEFAULT_POINT = 1
        private const val ROYAL_FAMILY_POINT = 10
        const val MAXIMUM_SUM_OF_CARD_POINTS = 21
        private const val WRONG_CARD_POINT_MESSAGE = "잘못된 카드 값입니다.($MINIMUM_CARD_POINT~$MAXIMUM_CARD_POINT 입력)"

        private val numberPool = (MINIMUM_CARD_POINT..MAXIMUM_CARD_POINT)
            .associateWith { CardPoint(it) }

        operator fun get(value: Int): CardPoint {
            require(value in MINIMUM_CARD_POINT..MAXIMUM_CARD_POINT) { WRONG_CARD_POINT_MESSAGE }
            return numberPool[value]!!
        }

        fun getPoint(rank: String): CardPoint {
            return when {
                CardNumber.isAce(rank) -> CardPoint[ACE_DEFAULT_POINT]
                CardNumber.isRoyalFamily(rank) -> CardPoint[ROYAL_FAMILY_POINT]
                else -> CardPoint[rank.toInt()]
            }
        }
    }
}
