package blackjack.service

import blackjack.domain.Dealer
import blackjack.domain.DealerCardDeck
import blackjack.domain.Player
import blackjack.domain.PlayerCards
import blackjack.domain.PlayerCardsHandler
import blackjack.domain.Players
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class ShuffledDeckCardsBuilderTest {

    @Test
    fun `ShuffledDeckCardsBuilder로 DealerCardDeck을 생성한 다음 dealer가 카드를 두 장씩 나눠주면 각 Player들은 카드를 두 장씩 가지게 된다`() {
        val players = Players(
            listOf(
                Player("aaa", PlayerCardsHandler(PlayerCards(), PlayerCardAdditionDecider())),
                Player("bbb", PlayerCardsHandler(PlayerCards(), PlayerCardAdditionDecider()))
            )
        )

        val dealerCardDeck = DealerCardDeck(ShuffledDeckCardsBuilder().build())
        val dealer = Dealer(dealerCardDeck)

        dealer.deliverBasicCards(players)

        players.items.forEach {
            Assertions.assertThat(it.cardsHandler.getCards().cards).hasSize(2)
        }
    }
}
