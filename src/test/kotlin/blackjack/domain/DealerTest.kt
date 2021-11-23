package blackjack.domain

import blackjack.service.CardDeckBuilder
import blackjack.service.PlayerCardAdditionDecider
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DealerTest {
    @Test
    fun `deliverBasicCards() 메소드로 Players들에게 카드를 나눠주면 각 Player들은 카드를 2장씩 가지게 된다`() {
        val players = Players(
            listOf(
                Player("aaa", PlayerCardsHandler(PlayerCards(), PlayerCardAdditionDecider())),
                Player("bbb", PlayerCardsHandler(PlayerCards(), PlayerCardAdditionDecider()))
            )
        )

        val cardDeckBuilder = object : CardDeckBuilder {
            override fun build(): List<Card> {
                return listOf(
                    Card(Suit.SPADE, Denomination.ACE),
                    Card(Suit.HEART, Denomination.KING),
                    Card(Suit.DIAMOND, Denomination.JACK),
                    Card(Suit.CLUBS, Denomination.QUEEN)
                )
            }
        }

        val dealerCardDeck = DealerCardDeck(cardDeckBuilder.build())
        val dealer = Dealer(dealerCardDeck)

        dealer.deliverBasicCards(players)

        players.items.forEach {
            assertThat(it.cardsHandler.getCards().cards.size).isEqualTo(2)
        }
    }

    @Test
    fun `deliverBasicCards() 메소드로 Player에게 카드를 나눠주면 카드를 2장 가지게 된다`() {
        val player = Player("aaa", PlayerCardsHandler(PlayerCards(), PlayerCardAdditionDecider()))

        val cardDeckBuilder = object : CardDeckBuilder {
            override fun build(): List<Card> {
                return listOf(
                    Card(Suit.HEART, Denomination.KING),
                    Card(Suit.DIAMOND, Denomination.JACK),
                    Card(Suit.CLUBS, Denomination.QUEEN),
                )
            }
        }

        val dealerCardDeck = DealerCardDeck(cardDeckBuilder.build())
        val dealer = Dealer(dealerCardDeck)

        dealer.deliverBasicCards(player)

        assertThat(player.cardsHandler.getCards().cards.size).isEqualTo(2)
    }

    @Test
    fun `deliverCard() 메소드로 Players들에게 카드를 나눠주면 각 Player들은 카드를 한장씩 더 가지게 된다`() {
        val players = Players(
            listOf(
                Player("aaa", PlayerCardsHandler(PlayerCards(), PlayerCardAdditionDecider())),
                Player("bbb", PlayerCardsHandler(PlayerCards(), PlayerCardAdditionDecider()))
            )
        )

        val cardDeckBuilder = object : CardDeckBuilder {
            override fun build(): List<Card> {
                return listOf(
                    Card(Suit.HEART, Denomination.KING),
                    Card(Suit.DIAMOND, Denomination.JACK),
                    Card(Suit.CLUBS, Denomination.QUEEN),
                    Card(Suit.SPADE, Denomination.ACE),
                    Card(Suit.DIAMOND, Denomination.TWO),
                    Card(Suit.HEART, Denomination.SEVEN)
                )
            }
        }

        val dealer = Dealer(DealerCardDeck(cardDeckBuilder.build()))

        dealer.deliverBasicCards(players)

        players.items.forEach {
            dealer.deliverCard(it)
            assertThat(it.cardsHandler.getCards().cards.size).isEqualTo(3)
        }
    }
}
