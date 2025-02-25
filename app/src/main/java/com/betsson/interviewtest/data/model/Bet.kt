package com.betsson.interviewtest.data.model

import com.betsson.interviewtest.domain.model.Bet
import com.betsson.interviewtest.domain.model.BetType
import com.betsson.interviewtest.domain.model.ImagePath
import com.betsson.interviewtest.domain.model.Odds
import com.betsson.interviewtest.domain.model.SellInDays
import java.util.UUID

data class BetDto(
    val type: String,
    val odds: Int,
    val sellIn: Int,
    val imageUrl: String,
    val id: String = UUID.randomUUID().toString(), // Probably we should have it as a primary key
) {
    fun toDomainModel(): Bet {
        return Bet(
            type = BetType.findByName(type),
            odds = Odds(odds),
            sellIn = SellInDays(sellIn),
            image = ImagePath(imageUrl)
        )
    }
}