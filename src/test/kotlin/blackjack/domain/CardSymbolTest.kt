package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class CardSymbolTest {

    @ParameterizedTest
    @EnumSource(value = CardSymbol::class)
    fun `toCardPool() 메소드를 호출하면 각 CardSymbol을 key로 입력하면 Card 타입 List를 리턴하는 map을 생성한다`(symbol: CardSymbol) {
        val cardPool = CardSymbol.toCardPool()

        assertThat(cardPool).isNotNull
        assertThat(cardPool).hasSize(CardSymbol.values().size)

        assertThat(cardPool[symbol]).isNotNull

        val symbolCards = cardPool[symbol]!!

        assertThat(symbolCards).hasSize(CardNumber.values().size)

        symbolCards.forEach {
            assertThat(it.symbol).isEqualTo(symbol)
        }
    }
}
