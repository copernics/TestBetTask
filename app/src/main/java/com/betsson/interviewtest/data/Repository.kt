package com.betsson.interviewtest.data

import com.betsson.interviewtest.model.Bet

interface IBetRepository {
    fun getBets(): List<Bet>
}
