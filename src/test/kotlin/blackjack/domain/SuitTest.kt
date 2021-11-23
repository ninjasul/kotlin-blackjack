package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class SuitTest {

    @ParameterizedTest
    @EnumSource(value = Suit::class)
    fun `toCardPool() 메소드를 호출하면 각 Suit을 key로 입력하면 Card 타입 List를 리턴하는 map을 생성한다`(suit: Suit) {
        val cardPool = Suit.toCardPool()

        assertThat(cardPool).isNotNull
        assertThat(cardPool).hasSize(Suit.values().size)

        assertThat(cardPool[suit]).isNotNull

        val suitCards = cardPool[suit]!!

        assertThat(suitCards).hasSize(Denomination.values().size)

        suitCards.forEach {
            assertThat(it.suit).isEqualTo(suit)
        }
    }
}
