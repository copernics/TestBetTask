package com.betsson.interviewtest.presentation.state


import com.betsson.interviewtest.domain.model.Bet

data class BetState(
    val bets: List<Bet> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
