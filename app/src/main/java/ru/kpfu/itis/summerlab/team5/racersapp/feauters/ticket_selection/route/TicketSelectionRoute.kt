package ru.kpfu.itis.summerlab.team5.racersapp.feauters.ticket_selection.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.bigtiz.ui.screen.purchase_success.ViewModel.PurchaseSuccessViewModel
import com.example.bigtiz.ui.screen.ticket_selection.TicketSelectionScreen
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.common.navigation.NavigationScreen
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.ticket_selection.domain.PurchaseTicketsUseCase
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.ticket_selection.domain.TicketRepositoryFactory
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.ticket_selection.presentation.TicketViewModel

@Composable
fun TicketSelectionRoute(
    route: NavigationScreen.TicketSelection,
    ticketRepositoryFactory: TicketRepositoryFactory,
    purchaseSuccessViewModel: PurchaseSuccessViewModel,
    onMenuClick: () -> Unit,
    onPurchaseSuccess: () -> Unit
) {
    val city = route.city
    val ticketRepository = remember(city) { ticketRepositoryFactory.createRepository(city) }
    val purchaseUseCase = remember(city) { PurchaseTicketsUseCase(ticketRepository) }
    val ticketViewModel = remember(city) {
        TicketViewModel(ticketRepository, purchaseUseCase)
    }
    TicketSelectionScreen(
        viewModel = ticketViewModel,
        selectedPlace = city,
        onMenuClick = onMenuClick,
        purchaseSuccessViewModel = purchaseSuccessViewModel,
        onPurchaseSuccess = onPurchaseSuccess
    )
}
