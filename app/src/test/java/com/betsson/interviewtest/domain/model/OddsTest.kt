package com.betsson.interviewtest.domain.model
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class OddsTest {

    @Test
    fun `test valid Odds value`() {
        val odds = Odds(5)
        assertEquals(5, odds.value)
    }

    @Test
    fun `test invalid Odds value`() {
        assertThrows(IllegalArgumentException::class.java) {
            Odds(-1) // Should fail as value is negative
        }
    }
}
