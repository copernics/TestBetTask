package com.betsson.interviewtest.presentation.viewmodel
import app.cash.turbine.test
import com.betsson.interviewtest.domain.model.*
import com.betsson.interviewtest.domain.usecase.CalculateOddsUseCase
import com.betsson.interviewtest.domain.usecase.LoadBetsUseCase
import com.betsson.interviewtest.domain.utils.BetProcessor
import com.betsson.interviewtest.domain.utils.SortBySellInCommand
import com.betsson.interviewtest.presentation.intent.BetIntent
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
@OptIn(ExperimentalCoroutinesApi::class)
class BetViewModelTest {

    @get:Rule
    val coroutineRule = TestCoroutineRule()

    private lateinit var viewModel: BetViewModel
    private val loadBetsUseCase: LoadBetsUseCase = mockk()
    private val calculateOddsUseCase: CalculateOddsUseCase = mockk()
    private val betProcessor = BetProcessor().apply {
        addCommand(SortBySellInCommand()) // Added a command for sorting
    }

    private val fakeBets = listOf(
        Bet(BetType.WINNING_TEAM, SellInDays(5), Odds(10), ImagePath("https://example.com/1.jpg")),
        Bet(BetType.TOTAL_SCORE, SellInDays(3), Odds(15), ImagePath("https://example.com/2.jpg"))
    )

    private val processedBets = betProcessor.process(fakeBets) // make sorting

    @Before
    fun setup() {
        coEvery { loadBetsUseCase.execute() } returns fakeBets
        coEvery { calculateOddsUseCase.execute(any()) } answers { firstArg() }

        viewModel = BetViewModel(loadBetsUseCase, calculateOddsUseCase)
    }

    @Test
    fun `viewModel loads bets on initialization`() = coroutineRule.runTest {
        viewModel.state.test {
            val loadedState = awaitItem()
            assertEquals(processedBets, loadedState.bets) // checking loaded  bets
            assertEquals(false, loadedState.isLoading)
        }
    }

    @Test
    fun `calculateOdds updates state correctly`() = coroutineRule.runTest {
        viewModel.state.test {
            viewModel.updateIntent(BetIntent.CalculateOdds)
            val updatedState = awaitItem()
            assertEquals(processedBets, updatedState.bets) // checking calculated bets
        }
    }

    @Test
    fun `loadBets triggers use case`() = coroutineRule.runTest {
        viewModel.updateIntent(BetIntent.LoadBets)
        coVerify { loadBetsUseCase.execute() }
    }

    @Test
    fun `calculateOdds triggers use case`() = coroutineRule.runTest {
        viewModel.updateIntent(BetIntent.CalculateOdds)
        advanceUntilIdle() //wait for coroutines to finish
        coVerify(exactly = 1) { calculateOddsUseCase.execute(any()) }
    }
}
