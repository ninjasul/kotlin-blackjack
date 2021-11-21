package blackjack.domain

class PlayerCards : ParticipantCards {
    private val _cards: MutableList<Card> = mutableListOf()

    override val cards: List<Card>
        get() = _cards

    override fun getCardDisplayNames(): String = _cards.joinToString { it.toString() }

    override fun add(card: Card) {
        _cards.add(card)
    }
}
