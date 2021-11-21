package blackjack.service

import blackjack.domain.Card

interface CardDeckBuilder {
    fun build(): List<Card>
}
