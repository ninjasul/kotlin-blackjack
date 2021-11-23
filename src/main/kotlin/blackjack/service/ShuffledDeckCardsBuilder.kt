package blackjack.service

import blackjack.domain.Card
import blackjack.domain.Suit

class ShuffledDeckCardsBuilder : CardDeckBuilder {
    override fun build(): List<Card> {
        return Suit.toCardPool()
            .values
            .flatten()
            .shuffled()
            .toList()
    }
}
