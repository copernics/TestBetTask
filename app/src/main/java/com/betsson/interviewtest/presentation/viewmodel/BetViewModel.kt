package com.betsson.interviewtest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betsson.interviewtest.domain.usecase.CalculateOddsUseCase
import com.betsson.interviewtest.domain.usecase.LoadBetsUseCase
import com.betsson.interviewtest.domain.utils.BetProcessor
import com.betsson.interviewtest.domain.utils.SortBySellInCommand
import com.betsson.interviewtest.presentation.intent.BetIntent
import com.betsson.interviewtest.presentation.state.BetState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BetViewModel(
    private val loadBetsUseCase: LoadBetsUseCase,
    private val calculateOddsUseCase: CalculateOddsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BetState())
    val state: StateFlow<BetState> = _state
    private val betProcessor = BetProcessor()

    init {
        updateIntent(BetIntent.LoadBets)
        betProcessor.addCommand(SortBySellInCommand())
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

            val bets = loadBetsUseCase.execute()
            val processedBets = betProcessor.process(bets)

            _state.value = _state.value.copy(bets = processedBets, isLoading = false)
        }
    }

    private fun calculateOdds() {
        viewModelScope.launch {
            val bets = calculateOddsUseCase.execute(_state.value.bets)
            val processedBets = betProcessor.process(bets)
            _state.value = _state.value.copy(bets = processedBets)
        }
    }
}