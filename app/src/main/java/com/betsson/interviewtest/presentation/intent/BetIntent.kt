package com.betsson.interviewtest.presentation.intent

sealed class BetIntent {
    object LoadBets : BetIntent()
    object CalculateOdds : BetIntent()
}