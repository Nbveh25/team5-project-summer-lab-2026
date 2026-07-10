package ru.kpfu.itis.summerlab.team5.racersapp.feauters.ticket_selection.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.common.uikit.AppConstants
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.common.uikit.Header
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.purchase_success.presentation.PurchaseSummary

private const val fanPrice = 100
private const val vipPrice = 1_400
private const val premiumPrice = 2_500

@Composable
fun TicketSelectionScreen(
    raceName: String,
    onMenuClick: () -> Unit,
    onPurchaseSuccess: (PurchaseSummary) -> Unit,
) {
    var fanAvailable by remember(raceName) { mutableIntStateOf(30) }
    var vipAvailable by remember(raceName) { mutableIntStateOf(10) }
    var premiumAvailable by remember(raceName) { mutableIntStateOf(5) }
    var fanSelected by remember(raceName) { mutableIntStateOf(0) }
    var vipSelected by remember(raceName) { mutableIntStateOf(0) }
    var premiumSelected by remember(raceName) { mutableIntStateOf(0) }
    var errorMessage by remember(raceName) { mutableStateOf<String?>(null) }

    val total = fanSelected * fanPrice + vipSelected * vipPrice + premiumSelected * premiumPrice

    Surface(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.wp6),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Header(onMenuClick = onMenuClick)
            Text(
                text = raceName,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(28.dp))
                    .background(Color.Black.copy(alpha = 0.55f))
                    .padding(vertical = 15.dp, horizontal = 20.dp),
                color = Color.White,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            TicketZone(
                name = "Fan Zone",
                available = fanAvailable,
                selected = fanSelected,
                price = fanPrice,
                onDecrease = { if (fanSelected > 0) fanSelected-- },
                onIncrease = { if (fanSelected < fanAvailable) fanSelected++ },
            )
            TicketZone(
                name = "VIP Zone",
                available = vipAvailable,
                selected = vipSelected,
                price = vipPrice,
                onDecrease = { if (vipSelected > 0) vipSelected-- },
                onIncrease = { if (vipSelected < vipAvailable) vipSelected++ },
            )
            TicketZone(
                name = "Premium Zone",
                available = premiumAvailable,
                selected = premiumSelected,
                price = premiumPrice,
                onDecrease = { if (premiumSelected > 0) premiumSelected-- },
                onIncrease = { if (premiumSelected < premiumAvailable) premiumSelected++ },
            )
            Text(
                text = "Итого: $total ₽",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.End),
            )
            errorMessage?.let {
                Text(it, color = Color(0xFFFF8A80), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
            Button(
                onClick = {
                    errorMessage = when {
                        total == 0 -> "Выберите хотя бы один билет"
                        total > AppConstants.balance -> "Недостаточно средств на балансе"
                        else -> null
                    }
                    if (errorMessage == null) {
                        fanAvailable -= fanSelected
                        vipAvailable -= vipSelected
                        premiumAvailable -= premiumSelected
                        AppConstants.balance -= total
                        onPurchaseSuccess(
                            PurchaseSummary(
                                raceName = raceName,
                                fanTickets = fanSelected,
                                vipTickets = vipSelected,
                                premiumTickets = premiumSelected,
                                totalPaid = total,
                            )
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF707070)),
            ) {
                Text("Купить", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun TicketZone(
    name: String,
    available: Int,
    selected: Int,
    price: Int,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Black.copy(alpha = 0.58f))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(name, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text("Осталось: $available", color = Color(0xFFFF8A80), fontSize = 14.sp)
            Text("$price ₽", color = Color.LightGray, fontSize = 16.sp)
        }
        Button(onClick = onDecrease, modifier = Modifier.width(42.dp), contentPadding = androidx.compose.foundation.layout.PaddingValues()) {
            Text("−", fontSize = 20.sp)
        }
        Text(selected.toString(), color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Button(onClick = onIncrease, modifier = Modifier.width(42.dp), contentPadding = androidx.compose.foundation.layout.PaddingValues()) {
            Text("+", fontSize = 20.sp)
        }
    }
}
