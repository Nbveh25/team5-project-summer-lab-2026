package ru.kpfu.itis.summerlab.team5.racersapp.feauters.common.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationScreen {
    @Serializable
    data object RaceInfo : NavigationScreen()

    @Serializable
    data class TicketSelection(val city: String) : NavigationScreen()
    @Serializable
        data object ScheduleOfRacesViewModel : NavigationScreen()

    @Serializable
    data object PurchaseSuccess : NavigationScreen()

    @Serializable
    data class PilotDetails(val pilotId: String) : NavigationScreen()
}