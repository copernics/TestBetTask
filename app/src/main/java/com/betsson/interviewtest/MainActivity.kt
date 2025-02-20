package com.betsson.interviewtest

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.betsson.interviewtest.data.BetRepository
import com.betsson.interviewtest.mvi.BetIntent
import com.betsson.interviewtest.viewmodel.BetViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    //I don't included any DI library implementation
    @Suppress("UNCHECKED_CAST")
    private val viewModel: BetViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return BetViewModel(BetRepository()) as T
            }
        }
    }

    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        adapter = ItemAdapter(emptyList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                adapter.updateItems(state.bets)
            }
        }

        findViewById<Button>(R.id.btnUpdate).setOnClickListener {
            viewModel.updateIntent(BetIntent.CalculateOdds)
        }

        viewModel.updateIntent(BetIntent.LoadBets)
    }
}
