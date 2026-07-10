package com.example.bigtiz.ui.screen.ticket_selection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bigtiz.ui.screen.purchase_success.ViewModel.PurchaseSuccessViewModel
import com.example.bigtiz.ui.screen.ticket_selection.domain.TicketPrices
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.ticket_selection.presentation.TicketViewModel
import ru.kpfu.itis.summerlab.team5.racersapp.R
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.common.uikit.HamburgerMenuButton

@Composable
fun TicketSelectionScreen(
    viewModel: TicketViewModel,
    selectedPlace: String,
    onMenuClick: () -> Unit,
    purchaseSuccessViewModel: PurchaseSuccessViewModel,
    onPurchaseSuccess: () -> Unit
) {

    val state = viewModel.uiState

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {

        DrawSurface()

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                DrawUpperOval(
                    money = state.money,
                    onClick = onMenuClick
                )

                DrawOvalBelowUpper(
                    place = selectedPlace
                )
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Column(
                modifier = Modifier.padding(vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {

                DrawZone(
                    title = "Fan Zone",
                    ticketsLeft = state.fanAvailable,
                    price = TicketPrices.FAN,
                    selectedValue = state.fanSelected,
                    onIncrease = {
                        viewModel.increaseFan()
                    },
                    onDecrease = {
                        viewModel.decreaseFan()
                    }
                )

                DrawZone(
                    title = "VIP Zone",
                    ticketsLeft = state.vipAvailable,
                    price = TicketPrices.VIP,
                    selectedValue = state.vipSelected,
                    onIncrease = {
                        viewModel.increaseVip()
                    },
                    onDecrease = {
                        viewModel.decreaseVip()
                    }
                )

                DrawZone(
                    title = "Premium Zone",
                    ticketsLeft = state.premiumAvailable,
                    price = TicketPrices.PREMIUM,
                    selectedValue = state.premiumSelected,
                    onIncrease = {
                        viewModel.increasePremium()
                    },
                    onDecrease = {
                        viewModel.decreasePremium()
                    }
                )
            }

            DrawTotalRow(
                fanSelected = state.fanSelected,
                vipSelected = state.vipSelected,
                premiumSelected = state.premiumSelected
            )

            DrawGrandTotal(
                total = state.fanSelected * TicketPrices.FAN +
                        state.vipSelected * TicketPrices.VIP +
                        state.premiumSelected * TicketPrices.PREMIUM
            )

            DrawPayButton(
                viewModel = viewModel,
                purchaseSuccessViewModel = purchaseSuccessViewModel,
                onPurchaseSuccess = onPurchaseSuccess
            )
        }
    }
}

@Composable
private fun DrawSurface() {

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.wp6),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun DrawUpperOval(
    money: Int,
    onClick: () -> Unit
) {

    val list = listOf(
        Color.Gray,
        Color.Gray,
        Color.White
    )

    Box(
        modifier = Modifier
            .padding(top = 18.dp)
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(50))
            .background(Color(0xFF1C1C1C).copy(alpha = 0.75f))
            .padding(horizontal = 16.dp)
    ) {

        Text(
            text = stringResource(R.string.nfs),
            fontSize = 24.sp,
            style = TextStyle(
                brush = Brush.linearGradient(list)
            ),
            modifier = Modifier.align(Alignment.Center)
        )

        DrawHamburgerMenu(
            money = money,
            onClick = onClick
        )
    }
}

@Composable
private fun DrawHamburgerMenu(
    money: Int,
    onClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        HamburgerMenuButton(onClick)
        MoneyIcon(money)
    }
}

@Composable
private fun MoneyIcon(
    money: Int
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 60.dp)
            .padding(vertical = 20.dp),

        contentAlignment = Alignment.TopEnd
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(RoundedCornerShape(100))
                    .background(Color.Black.copy(alpha = 0.58f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.rubblik),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(10.dp)
                )
            }

            Text(
                text = "$money",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun DrawOvalBelowUpper(
    place: String
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(50))
            .background(Color.Black.copy(alpha = 0.58f))
    ) {

        Text(
            text = place,

            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,

            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 20.dp)
        )
    }
}

@Composable
private fun DrawZone(
    title: String,
    ticketsLeft: Int,
    price: Int,
    selectedValue: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
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
            Text(title, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text("Осталось: $ticketsLeft", color = Color(0xFFFF8A80), fontSize = 14.sp)
            Text("$price ₽", color = Color.LightGray, fontSize = 16.sp)
        }
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF3A3A3A))
                .clickable(onClick = onDecrease),
            contentAlignment = Alignment.Center
        ) {
            Text("−", fontSize = 16.sp, color = Color.White)
        }
        Text(selectedValue.toString(), color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF3A3A3A))
                .clickable(onClick = onIncrease),
            contentAlignment = Alignment.Center
        ) {
            Text("+", fontSize = 16.sp, color = Color.White)
        }
    }
}

@Composable
private fun DrawPayButton(
    viewModel: TicketViewModel,
    purchaseSuccessViewModel: PurchaseSuccessViewModel,
    onPurchaseSuccess: () -> Unit
) {
    val state = viewModel.uiState

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                val success = viewModel.purchase()
                if (success) {
                    purchaseSuccessViewModel.setPurchaseInfo(
                        fan = state.fanSelected,
                        vip = state.vipSelected,
                        premium = state.premiumSelected,
                        total = state.total,
                    )
                    onPurchaseSuccess()
                }
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .width(250.dp)
                .height(64.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black.copy(alpha = 0.58f)
            ),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Купить",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun DrawTotalRow(
    fanSelected: Int,
    vipSelected: Int,
    premiumSelected: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DrawTotalItem(
            title = "Fan Zone",
            amount = fanSelected * TicketPrices.FAN,
            modifier = Modifier.weight(1f)
        )
        DrawTotalItem(
            title = "VIP Zone",
            amount = vipSelected * TicketPrices.VIP,
            modifier = Modifier.weight(1f)
        )
        DrawTotalItem(
            title = "Premium",
            amount = premiumSelected * TicketPrices.PREMIUM,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun DrawGrandTotal(
    total: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color.Black.copy(alpha = 0.58f))
            .padding(vertical = 12.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Итого:",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.LightGray
            )
            Text(
                text = "$total ₽",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
private fun DrawTotalItem(
    title: String,
    amount: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(Color.Black.copy(alpha = 0.58f))
            .padding(vertical = 8.dp, horizontal = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = title,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.LightGray,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "$amount ₽",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}
