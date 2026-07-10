package ru.kpfu.itis.summerlab.team5.racersapp.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.common.theme.SummerPractiseTheme
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.purchase_success.presentation.PurchaseSuccessScreen
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.purchase_success.presentation.PurchaseSummary
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.presentation.route.RaceInfoRoute
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.schedule_of_races.presentation.ScheduleOfRacesScreen
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.ticket_selection.presentation.TicketSelectionScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SummerPractiseTheme {
                RacersApp()
            }
        }
    }
}

private enum class AppScreen {
    RACE_INFO,
    SCHEDULE,
    TICKET_SELECTION,
    PURCHASE_SUCCESS,
}

@androidx.compose.runtime.Composable
private fun RacersApp() {
    var screen by remember { mutableStateOf(AppScreen.RACE_INFO) }
    var selectedRace by remember { mutableStateOf("Ближайшая гонка") }
    var purchaseSummary by remember { mutableStateOf<PurchaseSummary?>(null) }

    when (screen) {
        AppScreen.RACE_INFO -> RaceInfoRoute(
            onMenuClick = { screen = AppScreen.SCHEDULE },
            onBuyTicketClick = { screen = AppScreen.TICKET_SELECTION },
        )

        AppScreen.SCHEDULE -> ScheduleOfRacesScreen(
            onMenuClick = { screen = AppScreen.RACE_INFO },
            onTicketClick = { race ->
                selectedRace = race
                screen = AppScreen.TICKET_SELECTION
            },
        )

        AppScreen.TICKET_SELECTION -> TicketSelectionScreen(
            raceName = selectedRace,
            onMenuClick = { screen = AppScreen.SCHEDULE },
            onPurchaseSuccess = { summary ->
                purchaseSummary = summary
                screen = AppScreen.PURCHASE_SUCCESS
            },
        )

        AppScreen.PURCHASE_SUCCESS -> PurchaseSuccessScreen(
            summary = purchaseSummary ?: PurchaseSummary.empty(),
            onBackClick = { screen = AppScreen.SCHEDULE },
        )
    }
}
