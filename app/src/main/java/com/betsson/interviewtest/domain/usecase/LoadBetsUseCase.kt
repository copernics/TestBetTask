package com.betsson.interviewtest.domain.usecase

import com.betsson.interviewtest.domain.IBetRepository
import com.betsson.interviewtest.domain.model.Bet

class LoadBetsUseCase(
    private val repository: IBetRepository,
) {
    fun execute(): List<Bet> = repository.getBets()
}