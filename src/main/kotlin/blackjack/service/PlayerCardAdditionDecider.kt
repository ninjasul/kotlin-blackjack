package blackjack.service

import blackjack.domain.ParticipantCards.Companion.MAXIMUM_SUM_OF_CARD_NUMBERS

class PlayerCardAdditionDecider : ParticipantCardAdditionDecider {
    override fun canReceiveAdditionalCard(sum: Int): Boolean {
        return sum < MAXIMUM_SUM_OF_CARD_NUMBERS
    }
}
