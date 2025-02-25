package com.betsson.interviewtest.presentation.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.betsson.interviewtest.BetItem
import com.betsson.interviewtest.domain.model.*
import org.junit.Rule
import org.junit.Test

class BetItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun betItemDisplaysCorrectly() {
        // Given a sample bet
        val bet = Bet(
            type = BetType.WINNING_TEAM,
            sellIn = SellInDays(5),
            odds = Odds(10),
            image = ImagePath("https://example.com/image.jpg")
        )

        // Launch the Composable
        composeTestRule.setContent {
            BetItem(bet = bet)
        }

        // Check if the elements are displayed
        composeTestRule.onNodeWithText("Winning team").assertExists()
        composeTestRule.onNodeWithText("Odds: 10").assertExists()
        composeTestRule.onNodeWithText("Sell In: 5").assertExists()
    }
}
