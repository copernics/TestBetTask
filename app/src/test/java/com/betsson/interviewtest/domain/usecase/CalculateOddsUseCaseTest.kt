package com.betsson.interviewtest.domain.usecase

import com.betsson.interviewtest.domain.model.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CalculateOddsUseCaseTest {

    private lateinit var useCase: CalculateOddsUseCase

    @Before
    fun setUp() {
        useCase = CalculateOddsUseCase()
    }

    @Test
    fun `test execute reduces odds and sellIn correctly`() {
        val bets = listOf(
            Bet(BetType.WINNING_TEAM, SellInDays(10), Odds(5), ImagePath("path1")),
            Bet(BetType.TOTAL_SCORE, SellInDays(5), Odds(3), ImagePath("path2")),
            Bet(BetType.FIRST_GOAL_SCORER, SellInDays(3), Odds(8), ImagePath("path3"))
        )

        val updatedBets = useCase.execute(bets)

        assertEquals(9, updatedBets[0].sellIn.value) // WINNING_TEAM sellIn should decrease by 1
        assertEquals(4, updatedBets[1].sellIn.value) // TOTAL_SCORE sellIn should decrease by 1
        assertEquals(3, updatedBets[2].sellIn.value) // FIRST_GOAL_SCORER sellIn should remain the same

        assertEquals(4, updatedBets[0].odds.value) // WINNING_TEAM odds should decrease
        assertEquals(4, updatedBets[1].odds.value) // TOTAL_SCORE odds should increase
        assertEquals(8, updatedBets[2].odds.value) // FIRST_GOAL_SCORER odds should remain unchanged
    }

    @Test
    fun `test execute sets odds to zero for NUMBER_OF_FOULS when sellIn is below zero`() {
        val bets = listOf(
            Bet(BetType.NUMBER_OF_FOULS, SellInDays(-1), Odds(10), ImagePath("path4"))
        )

        val updatedBets = useCase.execute(bets)

        assertEquals(0, updatedBets[0].odds.value) // NUMBER_OF_FOULS odds should be 0
        assertEquals(-2, updatedBets[0].sellIn.value) // SellIn should still decrease by 1
    }

    @Test
    fun `test execute keeps odds within valid range`() {
        val bets = listOf(
            Bet(BetType.TOTAL_SCORE, SellInDays(1), Odds(49), ImagePath("path5"))
        )

        val updatedBets = useCase.execute(bets)

        assertEquals(50, updatedBets[0].odds.value) // Should increase but not exceed 50
    }
}
