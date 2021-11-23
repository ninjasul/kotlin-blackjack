package blackjack.domain

import blackjack.service.PlayerCardAdditionDecider
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerCardsHandlerTest {
    @Test
    fun `addCard() 메소드를 호출하면 카드를 추가할 수 있다`() {
        val cardsHandler = PlayerCardsHandler(PlayerCards(), PlayerCardAdditionDecider())
        val firstCard = Card(Suit.SPADE, Denomination.TEN)
        val secondCard = Card(Suit.DIAMOND, Denomination.KING)

        cardsHandler.addCard(firstCard)
        cardsHandler.addCard(secondCard)

        assertThat(cardsHandler.getCards().cards[0]).isEqualTo(firstCard)
        assertThat(cardsHandler.getCards().cards[1]).isEqualTo(secondCard)
    }

    @Test
    fun `ACE 카드 한장과 9 한장의 카드를 추가한 후 canReceiveAdditionalCard()를 호출하면 true를 리턴한다`() {
        val cardsHandler = PlayerCardsHandler(PlayerCards(), PlayerCardAdditionDecider())
        val firstCard = Card(Suit.SPADE, Denomination.ACE)
        val secondCard = Card(Suit.DIAMOND, Denomination.NINE)

        cardsHandler.addCard(firstCard)
        cardsHandler.addCard(secondCard)

        assertThat(cardsHandler.canReceiveAdditionalCard()).isEqualTo(true)
    }

    @Test
    fun `JACK 카드 한장과 QUEEN 한장의 카드를 추가한 후 canReceiveAdditionalCard()를 호출하면 true를 리턴한다`() {
        val cardsHandler = PlayerCardsHandler(PlayerCards(), PlayerCardAdditionDecider())
        val firstCard = Card(Suit.SPADE, Denomination.JACK)
        val secondCard = Card(Suit.DIAMOND, Denomination.QUEEN)

        cardsHandler.addCard(firstCard)
        cardsHandler.addCard(secondCard)

        assertThat(cardsHandler.canReceiveAdditionalCard()).isEqualTo(true)
    }

    @Test
    fun `ACE 카드 한장과 10 한장의 카드를 추가한 후 canReceiveAdditionalCard()를 호출하면 true를 리턴한다`() {
        val cardsHandler = PlayerCardsHandler(PlayerCards(), PlayerCardAdditionDecider())
        val firstCard = Card(Suit.SPADE, Denomination.ACE)
        val secondCard = Card(Suit.DIAMOND, Denomination.TEN)

        cardsHandler.addCard(firstCard)
        cardsHandler.addCard(secondCard)

        assertThat(cardsHandler.canReceiveAdditionalCard()).isEqualTo(true)
    }

    @Test
    fun `KING 카드 한장과 10 한장, TWO 한장의 카드를 추가한 후 canReceiveAdditionalCard()를 호출하면 false를 리턴한다`() {
        val cardsHandler = PlayerCardsHandler(PlayerCards(), PlayerCardAdditionDecider())
        val firstCard = Card(Suit.SPADE, Denomination.KING)
        val secondCard = Card(Suit.DIAMOND, Denomination.TEN)
        val thirdCard = Card(Suit.CLUBS, Denomination.TWO)

        cardsHandler.addCard(firstCard)
        cardsHandler.addCard(secondCard)
        cardsHandler.addCard(thirdCard)

        assertThat(cardsHandler.canReceiveAdditionalCard()).isEqualTo(false)
    }

    @Test
    fun `CLUBS ACE 카드 한장을 추가한 후 getCardDisplayNames()를 호출하면 'A클로버' 를 리턴한다`() {
        val cardsHandler = PlayerCardsHandler(PlayerCards(), PlayerCardAdditionDecider())
        val firstCard = Card(Suit.CLUBS, Denomination.ACE)
        cardsHandler.addCard(firstCard)

        val firstCardString = firstCard.denomination.rank + firstCard.suit.koreanName
        assertThat(cardsHandler.getCardDisplayNames()).isEqualTo(firstCardString)
    }

    @Test
    fun `SPADE KING 카드 한장과 DIAMOND TEN 한장을 추가한 후 getCardDisplayNames()를 호출하면 'K스페이드, 10다이아몬드' 를 리턴한다`() {
        val cardsHandler = PlayerCardsHandler(PlayerCards(), PlayerCardAdditionDecider())
        val firstCard = Card(Suit.SPADE, Denomination.KING)
        val secondCard = Card(Suit.DIAMOND, Denomination.TEN)

        cardsHandler.addCard(firstCard)
        cardsHandler.addCard(secondCard)

        val firstCardString = firstCard.denomination.rank + firstCard.suit.koreanName
        val secondCardString = secondCard.denomination.rank + secondCard.suit.koreanName

        assertThat(cardsHandler.getCardDisplayNames()).isEqualTo("$firstCardString, $secondCardString")
    }
}
