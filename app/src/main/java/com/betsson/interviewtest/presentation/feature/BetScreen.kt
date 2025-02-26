package com.betsson.interviewtest.presentation.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.betsson.interviewtest.BetItem
import com.betsson.interviewtest.presentation.intent.BetIntent
import com.betsson.interviewtest.presentation.viewmodel.BetViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun BetScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    viewModel: BetViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Button(
            onClick = {
                coroutineScope.launch {
                    viewModel.updateIntent(BetIntent.CalculateOdds)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Update Odds")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(state.bets) { bet ->
                BetItem(bet)
            }
        }
    }
}