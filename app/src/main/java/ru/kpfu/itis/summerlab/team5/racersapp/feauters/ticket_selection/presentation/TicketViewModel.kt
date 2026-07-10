package ru.kpfu.itis.summerlab.team5.racersapp.feauters.ticket_selection.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.ticket_selection.domain.PurchaseTicketsUseCase
import com.example.bigtiz.ui.screen.ticket_selection.domain.TicketPrices
import com.example.bigtiz.ui.screen.ticket_selection.presentation.TicketUiState
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.ticket_selection.domain.TicketRepository
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.ticket_selection.domain.Tickets
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.common.uikit.AppConstants

class TicketViewModel(
    private val repository: TicketRepository,
    private val purchaseUseCase: PurchaseTicketsUseCase
) {

    var uiState by mutableStateOf(TicketUiState())
        private set

    init {
        loadTickets()
    }

    private fun loadTickets() {

        val tickets = repository.getTickets()

        uiState = uiState.copy(
            fanAvailable = tickets.FanZone,
            vipAvailable = tickets.VipZone,
            premiumAvailable = tickets.PremiumZone,
            money = AppConstants.balance,
            total = 0
        )
    }


    fun increaseFan() {

        if (uiState.fanSelected < uiState.fanAvailable) {

            uiState = uiState.copy(
                fanSelected = uiState.fanSelected + 1,
                total = uiState.total + TicketPrices.FAN
            )
        }
    }

    fun decreaseFan() {

        if (uiState.fanSelected > 0) {

            uiState = uiState.copy(
                fanSelected = uiState.fanSelected - 1,
                total = uiState.total - TicketPrices.FAN
            )
        }
    }

    fun increaseVip() {

        if (uiState.vipSelected < uiState.vipAvailable) {

            uiState = uiState.copy(
                vipSelected = uiState.vipSelected + 1,
                total = uiState.total + TicketPrices.VIP
            )
        }
    }

    fun decreaseVip() {

        if (uiState.vipSelected > 0) {

            uiState = uiState.copy(
                vipSelected = uiState.vipSelected - 1,
                total = uiState.total - TicketPrices.VIP
            )
        }
    }

    fun increasePremium() {

        if (uiState.premiumSelected < uiState.premiumAvailable) {

            uiState = uiState.copy(
                premiumSelected = uiState.premiumSelected + 1,
                total = uiState.total + TicketPrices.PREMIUM
            )
        }
    }

    fun decreasePremium() {

        if (uiState.premiumSelected > 0) {

            uiState = uiState.copy(
                premiumSelected = uiState.premiumSelected - 1,
                total = uiState.total - TicketPrices.PREMIUM
            )
        }
    }

    fun purchase(): Boolean {

        if (uiState.total > uiState.money) {
            return false
        }

        val currentTickets = Tickets(
            FanZone = uiState.fanAvailable,
            VipZone = uiState.vipAvailable,
            PremiumZone = uiState.premiumAvailable
        )

        val updated = purchaseUseCase(
            currentTickets,
            uiState.fanSelected,
            uiState.vipSelected,
            uiState.premiumSelected
        )

        AppConstants.balance -= uiState.total

        uiState = uiState.copy(
            fanAvailable = updated.FanZone,
            vipAvailable = updated.VipZone,
            premiumAvailable = updated.PremiumZone,

            fanSelected = 0,
            vipSelected = 0,
            premiumSelected = 0,
            total = 0,

            money = AppConstants.balance
        )

        return true
    }
}