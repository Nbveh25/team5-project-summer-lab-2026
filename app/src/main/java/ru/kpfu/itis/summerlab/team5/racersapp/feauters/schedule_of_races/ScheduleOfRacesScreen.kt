package ru.kpfu.itis.summerlab.team5.racersapp.feauters.schedule_of_races

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.schedule_of_races.presentation.viewmodel.ScheduleOfRacesViewModel
import ru.kpfu.itis.summerlab.team5.racersapp.R
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.common.uikit.Header

@Composable
fun ScheduleOfRacesScreen(
    viewModel: ScheduleOfRacesViewModel,
    onMenuClick: () -> Unit = {},
    onTicketClick: (String) -> Unit = {},
) {

    Surface(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.wp6),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {

            ScheduleHeader(onMenuClick = onMenuClick)

            viewModel.races.forEach { race ->

                CityRace(
                    dayMonth = race.dayMonth,
                    year = race.year,
                    city = race.country,

                    onTicketClick = { city ->
                        onTicketClick(city)
                    }
                )
            }
        }
    }
}

@Composable
private fun ScheduleHeader(
    onMenuClick: () -> Unit
) {
    Header(onMenuClick)
}

@Composable
private fun CityRace(
    dayMonth: String,
    year: String,
    city: String,
    onTicketClick: (String) -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF0F0F0F).copy(alpha = 0.5f)),
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {

                Text(
                    text = dayMonth,
                    fontSize = 16.sp,
                    color = Color.White.copy(alpha = 0.7f),
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = year,
                    fontSize = 16.sp,
                    color = Color.White.copy(alpha = 0.7f),
                    fontWeight = FontWeight.Medium
                )
            }

            Text(
                text = city,
                fontSize = 26.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            TicketButton(
                onClick = {
                    onTicketClick(city)
                }
            )
        }
    }
}

@Composable
private fun TicketButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Button(
        onClick = onClick,
        modifier = modifier
            .height(44.dp)
            .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(22.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black.copy(alpha = 0.58f)
        ),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
    ) {

        Text(
            text = "БИЛЕТ",
            fontSize = 13.sp,
            color = Color.White.copy(alpha = 0.9f),
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
        )
    }
}

