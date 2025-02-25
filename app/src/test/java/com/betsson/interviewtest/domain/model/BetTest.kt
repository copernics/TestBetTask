package com.betsson.interviewtest.domain.model

import junit.framework.TestCase.assertEquals
import org.junit.Test

class BetTest {

    @Test
    fun `test Bet toString`() {
        val bet = Bet(
            type = BetType.WINNING_TEAM,
            sellIn = SellInDays(10),
            odds = Odds(2),
            image = ImagePath("path/to/image.jpg")
        )

        val expectedString = "Bet(type=Winning team, sellIn=10, odds=2)"
        assertEquals(expectedString, bet.toString())
    }
}