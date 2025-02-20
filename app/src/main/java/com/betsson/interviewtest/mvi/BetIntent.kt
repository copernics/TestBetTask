package com.betsson.interviewtest.mvi

sealed class BetIntent {
    object LoadBets : BetIntent()
    object CalculateOdds : BetIntent()
}