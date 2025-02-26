package com.betsson.interviewtest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
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
            AsyncImage(
                model = bet.image.value,
                contentDescription = stringResource(R.string.bet_image),
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = bet.type.displayName, fontSize = 18.sp, color = Color.Black)
                Text(text = stringResource(R.string.odds, bet.odds.value), fontSize = 14.sp, color = Color.DarkGray)
                Text(text = stringResource(R.string.sell_in, bet.sellIn.value), fontSize = 14.sp, color = Color.DarkGray)
            }
        }
    }
}
