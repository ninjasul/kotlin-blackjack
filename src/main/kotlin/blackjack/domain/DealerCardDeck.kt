package blackjack.domain

import java.util.Stack

data class DealerCardDeck(private val _cards: List<Card>) {
    private val cards: Stack<Card> = Stack<Card>().also { it.addAll(_cards) }

    fun getNextCard(): Card {
        check(cards.isNotEmpty()) { NO_CARD_LEFT_MESSAGE }
        return cards.pop()!!
    }

    companion object {
        private const val NO_CARD_LEFT_MESSAGE = "덱에 카드가 남아있지 않습니다."
    }
}
