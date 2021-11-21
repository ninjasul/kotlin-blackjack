package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource

internal class CardNumberTest {
    @ParameterizedTest
    @ValueSource(strings = ["J", "Q", "K"])
    fun `J, Q, K 를 isRoyaFamily() 메소드의 인자로 입력하면 true를 리턴한다`(text: String) {
        assertThat(CardNumber.isRoyalFamily(text)).isTrue
    }

    @Test
    fun `A 문자열을 isAce() 메소드의 인자로 입력하면 true를 리턴한다`() {
        assertThat(CardNumber.isAce("A")).isTrue
    }

    @ParameterizedTest
    @EnumSource(value = CardSymbol::class)
    fun `각 CardSymbol에 대해 toCards() 메소드를 호출하면 해당 Symbol의 ACE부터 HEART KING까지의 Card list를 리턴한다`(symbol: CardSymbol) {
        val cards = CardNumber.toCards(symbol)

        assertThat(cards).isNotNull
        assertThat(cards).hasSize(13)

        cards.forEach {
            assertThat(it.symbol).isEqualTo(symbol)
            assertTrue(CardNumber.values().contains(it.number))
        }
    }
}
