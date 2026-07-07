package ru.kpfu.itis.summerlab.team5.racersapp.repository

import ru.kpfu.itis.summerlab.team5.racersapp.domain.model.TicketCategory
import ru.kpfu.itis.summerlab.team5.racersapp.domain.model.TicketOffer
import ru.kpfu.itis.summerlab.team5.racersapp.domain.repository.TicketRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class InMemoryTicketRepository : TicketRepository {
    private val ticketOffers = MutableStateFlow(
        listOf(
            TicketOffer(
                category = TicketCategory.FAN_ZONE,
                title = "Fan Zone",
                priceRub = 100,
                ticketsLeft = 100,
            ),
            TicketOffer(
                category = TicketCategory.VIP_ZONE,
                title = "VIP Zone",
                priceRub = 1400,
                ticketsLeft = 107,
            ),
            TicketOffer(
                category = TicketCategory.PREMIUM_ZONE,
                title = "Premium Zone",
                priceRub = 2500,
                ticketsLeft = 107,
            ),
        ),
    )

    override fun observeTicketOffers(): StateFlow<List<TicketOffer>> = ticketOffers

    override fun purchaseTickets(selection: Map<TicketCategory, Int>) {
        ticketOffers.value = ticketOffers.value.map { offer ->
            val purchased = selection[offer.category].orZero()
            offer.copy(ticketsLeft = offer.ticketsLeft - purchased)
        }
    }

    private fun Int?.orZero(): Int = this ?: 0
}
