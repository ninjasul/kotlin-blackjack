package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTest {

    @Test
    fun `Card 객체를 생성하고 getDisplayName() 메소드의 결과값을 확인`() {
        val suits = Suit.values().toList()
        val denominations = Denomination.values().toList()

        for (suit in suits) {
            for (denomination in denominations) {
                val card = Card(suit, denomination)

                assertThat(card).isNotNull
                assertThat(card.getDisplayName()).isEqualTo(denomination.rank + suit.koreanName)
            }
        }
    }

    @Test
    fun `Denomination이 ACE인 Card 객체에 대해 hasAce() 메소드를 호출하면 true를 리턴한다`() {
        val suits = Suit.values().toList()
        val denominations = Denomination.values().filter { it == Denomination.ACE }.toList()

        for (suit in suits) {
            for (denomination in denominations) {
                val card = Card(suit, denomination)

                assertThat(card).isNotNull
                assertThat(card.hasAce()).isEqualTo(true)
            }
        }
    }
}
