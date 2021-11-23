package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource

internal class DenominationTest {
    @Test
    fun `A 문자열을 isAceRank() 메소드의 인자로 입력하면 true를 리턴한다`() {
        assertThat(Denomination.isAceRank("A")).isTrue
    }

    @ParameterizedTest
    @EnumSource(value = Suit::class)
    fun `각 Suit에 대해 toCards() 메소드를 호출하면 해당 Symbol의 ACE부터 HEART KING까지의 Card list를 리턴한다`(suit: Suit) {
        val cards = Denomination.toCards(suit)

        assertThat(cards).isNotNull
        assertThat(cards).hasSize(13)

        cards.forEach {
            assertThat(it.suit).isEqualTo(suit)
            assertTrue(Denomination.values().contains(it.denomination))
        }
    }

    @ParameterizedTest
    @EnumSource(value = Denomination::class)
    fun `Denomination 들의 rank 값으로 getScore() 를 호출하면 각 rank 값에 해당하는 score를 리턴한다`(denomination: Denomination) {
        assertThat(Denomination.getScore(denomination.rank)).isEqualTo(denomination.score)
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = [" ", "a", "j", "q", "k", "11"])
    fun `존재하지 않는 rank 값으로 getScore() 를 호출하면 IllegalArgumentException 이 발생한다`(rank: String) {
        assertThrows<IllegalArgumentException> {
            Denomination.getScore(rank)
        }
    }
}
