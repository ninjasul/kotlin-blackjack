package blackjack.domain

interface ParticipantCards {
    val cards: List<Card>
    fun getDisplayNames(): String = cards.joinToString { it.getDisplayName() }
    fun add(card: Card)
    fun getSumOfCardScore(): Int = cards.sumOf { Denomination.getScore(it.denomination.rank) }
    fun hasAceCard(): Boolean = cards.any { card -> card.hasAce() }
}
