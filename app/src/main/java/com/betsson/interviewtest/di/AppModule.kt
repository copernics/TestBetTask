package com.betsson.interviewtest.di

import com.betsson.interviewtest.data.repository.BetRepository
import com.betsson.interviewtest.domain.IBetRepository
import com.betsson.interviewtest.domain.usecase.CalculateOddsUseCase
import com.betsson.interviewtest.domain.usecase.LoadBetsUseCase
import com.betsson.interviewtest.presentation.viewmodel.BetViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<IBetRepository> { BetRepository() }
    single { LoadBetsUseCase(get()) }
    single { CalculateOddsUseCase() }
    viewModel { BetViewModel(get(), get()) }
}
