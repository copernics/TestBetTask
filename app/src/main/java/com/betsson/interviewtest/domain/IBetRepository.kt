package com.betsson.interviewtest.domain

import com.betsson.interviewtest.domain.model.Bet

interface IBetRepository {
    fun getBets(): List<Bet>
}
