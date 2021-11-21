package blackjack.domain

/**
 * 카드 무늬
 */
enum class CardSymbol(val koreanName: String) {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLUBS("클로버"),
    ;

    companion object {
        fun toCardPool(): Map<CardSymbol, List<Card>> {
            return values()
                .associateWith { CardNumber.toCards(it) }
        }
    }
}
