package ru.kpfu.itis.summerlab.team5.racersapp.feauters.schedule_of_races.presentation

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import ru.kpfu.itis.summerlab.team5.racersapp.R
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.common.uikit.Header

private data class RaceEvent(
    val date: String,
    val year: String,
    val country: String,
)

private val races = listOf(
    RaceEvent("04.03", "2026", "Австралия"),
    RaceEvent("05.04", "2026", "Германия"),
    RaceEvent("04.05", "2026", "Турция"),
)

@Composable
fun ScheduleOfRacesScreen(
    onMenuClick: () -> Unit,
    onTicketClick: (String) -> Unit,
) {
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
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Header(onMenuClick = onMenuClick)
            Text(
                text = "Расписание гонок",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 12.dp),
            )
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(races, key = { it.country }) { race ->
                    RaceCard(race = race, onTicketClick = { onTicketClick(race.country) })
                }
            }
        }
    }
}

@Composable
private fun RaceCard(race: RaceEvent, onTicketClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF0F0F0F).copy(alpha = 0.65f))
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        Column(modifier = Modifier.weight(0.7f)) {
            Text(race.date, fontSize = 17.sp, color = Color.White.copy(alpha = 0.75f))
            Text(race.year, fontSize = 17.sp, color = Color.White.copy(alpha = 0.75f))
        }
        Text(
            text = race.country,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier = Modifier.weight(1.5f),
        )
        Button(
            onClick = onTicketClick,
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.2f)),
            contentPadding = PaddingValues(horizontal = 12.dp),
        ) {
            Text("БИЛЕТ", fontSize = 13.sp, color = Color.White)
        }
    }
}
