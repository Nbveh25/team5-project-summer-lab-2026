package ru.kpfu.itis.summerlab.team5.racersapp.feauters.purchase_success.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.kpfu.itis.summerlab.team5.racersapp.R

data class PurchaseSummary(
    val raceName: String,
    val fanTickets: Int,
    val vipTickets: Int,
    val premiumTickets: Int,
    val totalPaid: Int,
) {
    val totalTickets: Int
        get() = fanTickets + vipTickets + premiumTickets

    companion object {
        fun empty() = PurchaseSummary("", 0, 0, 0, 0)
    }
}

@Composable
fun PurchaseSuccessScreen(summary: PurchaseSummary, onBackClick: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.wp6),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(Color.Black.copy(alpha = 0.75f))
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                Icon(
                    painter = painterResource(R.drawable.rubblik),
                    contentDescription = null,
                    tint = Color.Green,
                    modifier = Modifier.size(72.dp),
                )
                Text("Покупка успешна", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text(summary.raceName, color = Color.LightGray, fontSize = 18.sp)
                Text("Билетов куплено: ${summary.totalTickets}", color = Color.White, fontSize = 21.sp)
                TicketLine("Fan Zone", summary.fanTickets)
                TicketLine("VIP Zone", summary.vipTickets)
                TicketLine("Premium Zone", summary.premiumTickets)
                Text("Цена: ${summary.totalPaid} ₽", color = Color.Green, fontSize = 23.sp, fontWeight = FontWeight.Bold)
                Button(
                    onClick = onBackClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                ) {
                    Text("К расписанию", fontSize = 18.sp, textAlign = TextAlign.Center)
                }
            }
        }
    }
}

@Composable
private fun TicketLine(zone: String, count: Int) {
    Text("$zone: $count", color = Color.LightGray, fontSize = 18.sp)
}
