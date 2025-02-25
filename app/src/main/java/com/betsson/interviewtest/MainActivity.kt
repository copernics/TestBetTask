package com.betsson.interviewtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.betsson.interviewtest.presentation.intent.BetIntent
import com.betsson.interviewtest.presentation.viewmodel.BetViewModel
import com.betsson.interviewtest.ui.theme.AppTheme
import org.koin.androidx.compose.koinViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BetScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

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
