package com.betsson.interviewtest.data.repository

import com.betsson.interviewtest.data.model.BetDto
import com.betsson.interviewtest.domain.IBetRepository
import com.betsson.interviewtest.domain.model.Bet
import com.betsson.interviewtest.domain.model.BetType
import com.betsson.interviewtest.domain.model.ImagePath
import com.betsson.interviewtest.domain.model.Odds
import com.betsson.interviewtest.domain.model.SellInDays


class BetRepository : IBetRepository {
    override fun getBets(): List<Bet> {
        return listOf(
            BetDto("Winning team", 10, 20, "https://i.imgur.com/mx66SBD.jpeg"),
            BetDto("Total score", 2, 0, "https://i.imgur.com/VnPRqcv.jpeg"),
            BetDto("Player performance", 5, 7, "https://i.imgur.com/Urpc00H.jpeg"),
            BetDto("First goal scorer", 0, 80, "https://i.imgur.com/Wy94Tt7.jpeg"),
            BetDto("Number of fouls", 5, 49, "https://i.imgur.com/NMLpcKj.jpeg"),
            BetDto("Corner kicks", 3, 6, "https://i.imgur.com/TiJ8y5l.jpeg"),
        ).map { it.toDomainModel() }
    }
}
