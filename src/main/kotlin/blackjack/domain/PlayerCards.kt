package blackjack.domain

class PlayerCards : ParticipantCards {
    private val _cards: MutableList<Card> = mutableListOf()

    override val cards: List<Card>
        get() = _cards

    override fun getDisplayNames(): String = _cards.joinToString { it.getDisplayName() }

    override fun add(card: Card) {
        _cards.add(card)
    }
}
