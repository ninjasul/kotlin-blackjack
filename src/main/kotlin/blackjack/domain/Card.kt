package blackjack.domain

data class Card(val suit: Suit, val denomination: Denomination) {
    fun getDisplayName(): String {
        return denomination.rank + suit.koreanName
    }

    fun hasAce(): Boolean {
        return Denomination.isAceRank(denomination.rank)
    }
}
