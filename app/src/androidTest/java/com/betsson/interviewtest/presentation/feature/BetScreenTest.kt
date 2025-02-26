package com.betsson.interviewtest.presentation.feature

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.betsson.interviewtest.MainActivity
import com.betsson.interviewtest.presentation.intent.BetIntent
import com.betsson.interviewtest.presentation.state.BetState
import com.betsson.interviewtest.presentation.viewmodel.BetViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BetScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val mockViewModel = mockk<BetViewModel>(relaxed = true)

    @Test
    fun betScreen_displaysUpdateOddsButtonAndList() = runTest {
        coEvery { mockViewModel.state } returns MutableStateFlow(BetState())

        composeTestRule.setContent {
            BetScreen(viewModel = mockViewModel)
        }

        composeTestRule.onNodeWithText("Update Odds").assertExists()
        composeTestRule.onNodeWithTag("betList").assertExists()
    }

    @Test
    fun betScreen_updatesOddsOnClick() = runTest {
        coEvery { mockViewModel.state } returns MutableStateFlow(BetState())

        composeTestRule.setContent {
            BetScreen(viewModel = mockViewModel)
        }

        composeTestRule.onNodeWithText("Update Odds").performClick()
        coVerify { mockViewModel.updateIntent(BetIntent.CalculateOdds) }
    }
}
