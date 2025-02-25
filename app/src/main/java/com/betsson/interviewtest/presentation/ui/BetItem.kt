package com.betsson.interviewtest

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.betsson.interviewtest.domain.model.Bet

@Composable
fun BetItem(bet: Bet) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Log.d("BetItem$", "Image URL: ${bet.image.value}")
            AsyncImage(
                model = bet.image.value,
                contentDescription = "Bet Image",
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = bet.type.displayName, fontSize = 18.sp, color = Color.Black)
                Text(text = "Odds: ${bet.odds.value}", fontSize = 14.sp, color = Color.DarkGray)
                Text(text = "Sell In: ${bet.sellIn.value}", fontSize = 14.sp, color = Color.DarkGray)
            }
        }
    }
}
