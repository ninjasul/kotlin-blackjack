package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerCardsTest {

    @Test
    fun `add() 메소드를 한번 호출하면 cards에 카드 한장이 들어있다`() {
        val cards = PlayerCards()
        cards.add(Card(Suit.SPADE, Denomination.ACE))

        assertThat(cards).isNotNull
        assertThat(cards.cards.size).isEqualTo(1)
    }

    @Test
    fun `CLUBS ACE 카드 한장을 추가한 후 getCardDisplayNames()를 호출하면 'A클로버' 를 리턴한다`() {
        val playerCards = PlayerCards()
        playerCards.add(Card(Suit.CLUBS, Denomination.ACE))

        val cardString = playerCards.cards[0].denomination.rank + playerCards.cards[0].suit.koreanName
        assertThat(playerCards.getDisplayNames()).isEqualTo(cardString)
    }

    @Test
    fun `SPADE KING 카드 한장과 DIAMOND TEN 한장을 추가한 후 getCardDisplayNames()를 호출하면 'K스페이드, 10다이아몬드' 를 리턴한다`() {

        val playerCards = PlayerCards()
        playerCards.add(Card(Suit.CLUBS, Denomination.ACE))
        playerCards.add(Card(Suit.DIAMOND, Denomination.TEN))

        val firstCardString = playerCards.cards[0].denomination.rank + playerCards.cards[0].suit.koreanName
        val secondCardString = playerCards.cards[1].denomination.rank + playerCards.cards[1].suit.koreanName

        assertThat(playerCards.getDisplayNames()).isEqualTo("$firstCardString, $secondCardString")
    }
}
