package com.betsson.interviewtest.data

import com.betsson.interviewtest.model.*

class BetRepository : IBetRepository {
    override fun getBets(): List<Bet> {
        return listOf(
            Bet(BetType.WINNING_TEAM, SellInDays(10), Odds(20), ImagePath("https://i.imgur.com/mx66SBD.jpeg")),
            Bet(BetType.TOTAL_SCORE, SellInDays(2), Odds(1), ImagePath("https://i.imgur.com/VnPRqcv.jpeg")),
            Bet(BetType.PLAYER_PERFORMANCE, SellInDays(5), Odds(7), ImagePath("https://i.imgur.com/Urpc00H.jpeg")),
            Bet(BetType.FIRST_GOAL_SCORER, SellInDays(0), Odds(80), ImagePath("https://i.imgur.com/Wy94Tt7.jpeg")),
            Bet(BetType.NUMBER_OF_FOULS, SellInDays(5), Odds(49), ImagePath("https://i.imgur.com/NMLpcKj.jpeg")),
            Bet(BetType.CORNER_KICKS, SellInDays(3), Odds(6), ImagePath("https://i.imgur.com/TiJ8y5l.jpeg"))
        )
    }
}
