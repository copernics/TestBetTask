package com.betsson.interviewtest.domain.usecase

import com.betsson.interviewtest.domain.IBetRepository
import com.betsson.interviewtest.domain.model.*
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LoadBetsUseCaseTest {

    private lateinit var loadBetsUseCase: LoadBetsUseCase
    private val repository: IBetRepository = mockk()

    @Before
    fun setup() {
        loadBetsUseCase = LoadBetsUseCase(repository)
    }

    @Test
    fun `execute returns list of bets from repository`() {
        // Given
        val fakeBets = listOf(
            Bet(BetType.WINNING_TEAM, SellInDays(5), Odds(10), ImagePath("https://example.com/1.jpg")),
            Bet(BetType.TOTAL_SCORE, SellInDays(3), Odds(15), ImagePath("https://example.com/2.jpg"))
        )
        every { repository.getBets() } returns fakeBets

        // When
        val result = loadBetsUseCase.execute()

        // Then
        assertEquals(fakeBets, result)
    }

    @Test
    fun `execute returns empty list when repository has no bets`() {
        // Given
        every { repository.getBets() } returns emptyList()

        // When
        val result = loadBetsUseCase.execute()

        // Then
        assertEquals(emptyList<Bet>(), result)
    }
}
