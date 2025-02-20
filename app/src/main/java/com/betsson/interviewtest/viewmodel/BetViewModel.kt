package com.betsson.interviewtest.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betsson.interviewtest.data.IBetRepository
import com.betsson.interviewtest.mvi.BetIntent
import com.betsson.interviewtest.mvi.BetState
import com.betsson.interviewtest.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BetViewModel(
    private val repository: IBetRepository
) : ViewModel() {

    private val _state = MutableStateFlow(BetState())
    val state: StateFlow<BetState> = _state

    init {
        updateIntent(BetIntent.LoadBets)
    }

    fun updateIntent(intent: BetIntent) {
        when (intent) {
            is BetIntent.LoadBets -> loadBets()
            is BetIntent.CalculateOdds -> calculateOdds()
        }
    }

    private fun loadBets() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            val bets  = repository.getBets()
            val processedBets = postProcessBets(bets)

            _state.value = _state.value.copy(bets = processedBets, isLoading = false)
        }
    }
    private fun postProcessBets(bets: List<Bet>): List<Bet> {
        val betProcessor = BetProcessor()
        //We can add more commands to the processor
        betProcessor.addCommand(SortBySellInCommand())
        return betProcessor.process(bets)
    }

    private fun calculateOdds() {
        viewModelScope.launch {
            val updatedBets = _state.value.bets.map { bet ->
                val newOdds = calculateNewOdds(bet)
                val newSellIn = if (bet.type != BetType.FIRST_GOAL_SCORER) bet.sellIn.value - 1 else bet.sellIn.value

                bet.copy(
                    sellIn = SellInDays(newSellIn),
                    odds = Odds(newOdds)
                )
            }
            Log.d("BetViewModel", "Updated bets1: $updatedBets")
            _state.value = _state.value.copy(bets = postProcessBets(updatedBets))
            Log.d("BetViewModel", "Updated bets: ${_state.value.bets}")

        }
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