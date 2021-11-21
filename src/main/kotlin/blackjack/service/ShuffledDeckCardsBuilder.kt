package blackjack.service

import blackjack.domain.Card
import blackjack.domain.CardSymbol

class ShuffledDeckCardsBuilder : CardDeckBuilder {
    override fun build(): List<Card> {
        return CardSymbol.toCardPool()
            .values
            .flatten()
            .shuffled()
            .toList()
    }
}
