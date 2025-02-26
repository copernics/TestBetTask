package com.betsson.interviewtest.data.repository

import com.betsson.interviewtest.domain.model.Bet
import org.junit.Assert.*
import org.junit.Test

class BetRepositoryTest {

    private val repository = BetRepository()

    @Test
    fun `getBets returns non empty list`() {
        val bets: List<Bet> = repository.getBets()
        assertNotNull("Bets list should not be null", bets)
        assertTrue("Bets list should not be empty", bets.isNotEmpty())
    }

    @Test
    fun `getBets returns exactly 6 bets`() {
        val bets: List<Bet> = repository.getBets()
        // All 6 BetDto items should be successfully converted
        assertEquals("Bets list should contain exactly 6 items", 6, bets.size)
    }

    @Test
    fun `getBets returns bets with valid properties`() {
        val bets: List<Bet> = repository.getBets()
        bets.forEach { bet ->
            // Replace these checks with any validation rules your Bet model requires.
            assertNotNull("Bet name should not be null", bet.type.displayName)
            assertTrue("Bet odds should be non-negative", bet.odds.value >= 0)
            assertNotNull("Bet imageUrl should not be null", bet.image.value)
        }
    }
}
