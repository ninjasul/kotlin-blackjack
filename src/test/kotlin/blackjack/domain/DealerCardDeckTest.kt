package blackjack.domain

import blackjack.service.CardDeckBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.Stack

internal class DealerCardDeckTest {

    @Test
    fun `비어있는 CardDeck에 getNextCard() 메소드를 호출하면 IllegalStateException이 발생한다`() {
        val cardDeck = DealerCardDeck(Stack())

        assertThrows<IllegalStateException> {
            cardDeck.getNextCard()
        }
    }

    @Test
    fun `카드가 한 장 들어있는 CardDeck에 getNextCard() 메소드를 호출하면 Deck에 들어가 있는 카드를 얻을 수 있다`() {
        val cardDeckBuilder = object : CardDeckBuilder {
            override fun build(): List<Card> {
                return listOf(
                    Card(CardSymbol.SPADE, CardNumber.ACE),
                )
            }
        }
        val dealerCardDeck = DealerCardDeck(cardDeckBuilder.build())

        assertThat(dealerCardDeck.getNextCard()).isEqualTo(Card(CardSymbol.SPADE, CardNumber.ACE))
    }

    @Test
    fun `카드가 두 장 들어있는 CardDeck에 getNextCard() 메소드를 호출하면 Deck의 마지막 카드를 얻을 수 있다`() {
        val cardDeckBuilder = object : CardDeckBuilder {
            override fun build(): List<Card> {
                return listOf(
                    Card(CardSymbol.SPADE, CardNumber.ACE),
                    Card(CardSymbol.CLUBS, CardNumber.KING),
                )
            }
        }
        val dealerCardDeck = DealerCardDeck(cardDeckBuilder.build())

        assertThat(dealerCardDeck.getNextCard()).isEqualTo(Card(CardSymbol.CLUBS, CardNumber.KING))
    }
}
