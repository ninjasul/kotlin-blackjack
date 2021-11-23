package blackjack.domain

import blackjack.service.ParticipantCardAdditionDecider

class PlayerCardsHandler(
    private val cards: ParticipantCards,
    private val cardAdditionDecider: ParticipantCardAdditionDecider
) : ParticipantCardsHandler {
    override fun addCard(card: Card) {
        cards.add(card)
    }

    override fun canReceiveAdditionalCard(): Boolean {
        return cardAdditionDecider.canReceiveAdditionalCard(cards.getSumOfCardScore())
    }

    override fun getCards(): ParticipantCards {
        return cards
    }

    override fun getCardDisplayNames(): String {
        return cards.getDisplayNames()
    }
}
