package com.betsson.interviewtest.domain.usecase

import com.betsson.interviewtest.domain.model.Bet
import com.betsson.interviewtest.domain.model.BetType
import com.betsson.interviewtest.domain.model.Odds
import com.betsson.interviewtest.domain.model.SellInDays
import com.betsson.interviewtest.domain.utils.BetProcessor
import com.betsson.interviewtest.domain.utils.SortBySellInCommand

class CalculateOddsUseCase {
    fun execute(bets: List<Bet>): List<Bet> {
        val updatedBets = bets.map { bet ->
            val newOdds = calculateNewOdds(bet)
            val newSellIn =
                if (bet.type != BetType.FIRST_GOAL_SCORER) bet.sellIn.value - 1 else bet.sellIn.value

            bet.copy(
                sellIn = SellInDays(newSellIn),
                odds = Odds(newOdds)
            )
        }
        return updatedBets
    }

    private fun calculateNewOdds(bet: Bet): Int {
        var odds = bet.odds.value

        if (bet.type != BetType.TOTAL_SCORE && bet.type != BetType.NUMBER_OF_FOULS) {
            if (odds > 0 && bet.type != BetType.FIRST_GOAL_SCORER) {
                odds -= 1
            }
        } else {
            if (odds < 50) {
                odds += 1
                if (bet.type == BetType.NUMBER_OF_FOULS) {
                    if (bet.sellIn.value < 11 && odds < 50) odds += 1
                    if (bet.sellIn.value < 6 && odds < 50) odds += 1
                }
            }
        }
        if (bet.sellIn.value < 0) {
            odds = when (bet.type) {
                BetType.TOTAL_SCORE -> if (odds < 50) odds + 1 else odds
                BetType.NUMBER_OF_FOULS -> 0
                else -> if (odds > 0) odds - 1 else odds
            }
        }

        return odds
    }
}