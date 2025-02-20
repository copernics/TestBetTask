package com.betsson.interviewtest.mvi


import com.betsson.interviewtest.model.Bet

data class BetState(
    val bets: List<Bet> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
