package com.betsson.interviewtest.domain.model

import junit.framework.TestCase.assertEquals
import org.junit.Test

class BetTypeTest {

    @Test
    fun `test BetType display names`() {
        assertEquals("Winning team", BetType.WINNING_TEAM.displayName)
        assertEquals("Total score", BetType.TOTAL_SCORE.displayName)
        assertEquals("Player performance", BetType.PLAYER_PERFORMANCE.displayName)
        assertEquals("First goal scorer", BetType.FIRST_GOAL_SCORER.displayName)
        assertEquals("Number of fouls", BetType.NUMBER_OF_FOULS.displayName)
        assertEquals("Corner kicks", BetType.CORNER_KICKS.displayName)
    }
}
